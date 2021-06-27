package com.code9.tenniscourtmicroservice.reservation.service;

import com.code9.tenniscourtmicroservice.communication.IUserCommunication;
import com.code9.tenniscourtmicroservice.exception.NotFoundException;
import com.code9.tenniscourtmicroservice.reservation.domain.Reservation;
import com.code9.tenniscourtmicroservice.reservation.repository.IReservationRepository;
import com.code9.tenniscourtmicroservice.reservation.service.interfaces.IReservationService;
import com.code9.tenniscourtmicroservice.tennis_court.service.interfaces.ITennisCourtService;
import com.code9.tenniscourtmicroservice.timeslot.service.TimeslotService;
import com.code9.tenniscourtmicroservice.timeslot.service.interfaces.ITimeslotService;
import com.code9.tenniscourtmicroservice.validation.TimeslotValidation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService {

    private IReservationRepository reservationRepository;
    private ITimeslotService timeslotService;
    private ITennisCourtService tennisCourtService;
    private IUserCommunication userCommunication;

    public ReservationService(IReservationRepository reservationRepository, TimeslotService timeslotService,
                              ITennisCourtService tennisCourtService, IUserCommunication userCommunication) {
        this.reservationRepository = reservationRepository;
        this.timeslotService = timeslotService;
        this.tennisCourtService = tennisCourtService;
        this.userCommunication = userCommunication;
    }

    @Override
    public Reservation create(Reservation reservation) {

        userCommunication.getUser(reservation.getFirstUserId());
        userCommunication.getUser(reservation.getSecondUserId());

        checkTimeslots(reservation);
        reservation.setTennisCourt(tennisCourtService.findByName(reservation.getTennisCourt().getName()));
        reservation.getTimeslots().forEach(timeslot -> timeslotService.create(timeslot));

        if(reservation.getTimeslots().size() > 5)
            reservation.setPaid(Boolean.FALSE);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation update(Reservation reservation) {
        checkTimeslots(reservation);
        Reservation dbReservation = findById(reservation.getId());
        dbReservation.setFirstUserId(reservation.getFirstUserId());
        dbReservation.setSecondUserId(reservation.getSecondUserId());
        dbReservation.setTennisCourt(tennisCourtService.findByName(reservation.getTennisCourt().getName()));
        reservation.getTimeslots().forEach(timeslot -> timeslotService.create(timeslot));
        return reservationRepository.save(dbReservation);
    }

    @Override
    public Reservation delete(Long id) {
        Reservation reservation = findById(id);
        reservation.setDeleted(Boolean.TRUE);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation paid(Long id) {
        Reservation reservation = findById(id);
        reservation.setPaid(Boolean.TRUE);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation findById(Long id) {
        Reservation reservation = reservationRepository.findReservationById(id);
        if (reservation == null)
            throw new NotFoundException("There is no reservation with id: " + id);
        return reservation;
    }

    private void checkTimeslots(Reservation reservation) {
        TimeslotValidation.checkTimeslots(reservation.getTimeslots());

        List<Reservation> reservationsFirstUser = reservationRepository.findReservationByUserId(reservation.getFirstUserId());
        List<Reservation> reservationsSecondUser = reservationRepository.findReservationByUserId(reservation.getSecondUserId());

        if (!reservationsFirstUser.isEmpty())
            reservationsFirstUser.stream().forEach(
                    res -> TimeslotValidation.checkIfUserAlreadyHaveTimeslot(reservation.getTimeslots(), res.getTimeslots()));

        if (!reservationsSecondUser.isEmpty())
            reservationsSecondUser.stream().forEach(
                    res -> TimeslotValidation.checkIfUserAlreadyHaveTimeslot(reservation.getTimeslots(), res.getTimeslots()));

        List<Reservation> reservationsCourt = reservationRepository.findReservationByTennisCourt_Id(reservation.getTennisCourt().getId());

        if (!reservationsCourt.isEmpty())
            reservationsCourt.stream().forEach(
                    res -> TimeslotValidation.checkIfTennisCourtAlreadyHaveTimeslot(reservation.getTimeslots(), res.getTimeslots()));

    }

}
