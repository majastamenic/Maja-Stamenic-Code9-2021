package com.code9.tenniscourtmicroservice.reservation.service;

import com.code9.tenniscourtmicroservice.reservation.domain.Reservation;
import com.code9.tenniscourtmicroservice.reservation.repository.IReservationRepository;
import com.code9.tenniscourtmicroservice.reservation.service.interfaces.IReservationService;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements IReservationService {

    private IReservationRepository reservationRepository;

    public ReservationService(IReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    Reservation create(Reservation reservation) {
        return null;
    }
}
