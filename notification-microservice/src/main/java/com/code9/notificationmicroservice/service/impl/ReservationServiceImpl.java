package com.code9.notificationmicroservice.service.impl;

import com.code9.notificationmicroservice.domain.Reservation;
import com.code9.notificationmicroservice.repository.ReservationRepository;
import com.code9.notificationmicroservice.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public Reservation save(String text) {
        Reservation reservation = new Reservation();
        reservation.setText(text);
        return reservationRepository.save(reservation);
    }
}
