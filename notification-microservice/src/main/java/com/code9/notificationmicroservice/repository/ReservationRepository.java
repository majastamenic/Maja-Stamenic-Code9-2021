package com.code9.notificationmicroservice.repository;

import com.code9.notificationmicroservice.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
