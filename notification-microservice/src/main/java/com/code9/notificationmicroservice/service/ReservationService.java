package com.code9.notificationmicroservice.service;

import com.code9.notificationmicroservice.domain.Reservation;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {
    Reservation save(String text);
}
