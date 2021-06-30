package com.code9.tenniscourtmicroservice.reservation.service.impl;

import com.code9.tenniscourtmicroservice.exception.NotFoundException;
import com.code9.tenniscourtmicroservice.rabbitMQ.MessageFactory;
import com.code9.tenniscourtmicroservice.rabbitMQ.MessageService;
import com.code9.tenniscourtmicroservice.rabbitMQ.NewReservationMessage;
import com.code9.tenniscourtmicroservice.reservation.controller.dto.UserDto;
import com.code9.tenniscourtmicroservice.reservation.domain.Reservation;
import com.code9.tenniscourtmicroservice.reservation.repository.ReservationRepository;
import com.code9.tenniscourtmicroservice.reservation.service.ReservationService;
import com.code9.tenniscourtmicroservice.tennis_court.service.TennisCourtService;
import com.code9.tenniscourtmicroservice.timeslot.service.TimeslotService;
import com.code9.tenniscourtmicroservice.validation.TimeslotValidation;
import com.code9.usermicroservice.client.UserClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final TimeslotService timeslotService;
    private final TennisCourtService tennisCourtService;
    private final MessageService messageService;
    private final UserClient userClient;

    @Override
    public Reservation create(Reservation reservation) {

        userClient.getUser(reservation.getFirstUserId());
        userClient.getUser(reservation.getSecondUserId());

        checkTimeslots(reservation);
        reservation.setTennisCourt(tennisCourtService.findByName(reservation.getTennisCourt().getName()));
        reservation.getTimeslots().forEach(timeslot -> timeslotService.create(timeslot));
        reservation.getTimeslots().stream().map(timeslot -> timeslotService.create(timeslot));

        if (reservation.getTimeslots().size() > 5)
            reservation.setPaid(Boolean.FALSE);
        else
            publishMessage(reservation);

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
        publishMessage(reservation);
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

    private void publishMessage(Reservation reservation) {
        NewReservationMessage newReservationMessage = MessageFactory.createNewReservationMessage(reservation);
        messageService.sendMessageToNewReservationTopic(newReservationMessage);
    }

}
