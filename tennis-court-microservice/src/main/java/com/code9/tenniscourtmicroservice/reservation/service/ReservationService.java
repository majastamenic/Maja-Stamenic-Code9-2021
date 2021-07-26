package com.code9.tenniscourtmicroservice.reservation.service;

import com.code9.tenniscourtmicroservice.reservation.domain.Reservation;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

    Reservation create(Reservation reservation);

    Reservation update(Reservation reservation);

    Reservation delete(Long id);

    Reservation paid(Long id);

    Reservation findById(Long id);
}
