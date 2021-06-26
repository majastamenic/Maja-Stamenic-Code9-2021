package com.code9.tenniscourtmicroservice.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.code9.tenniscourtmicroservice.reservation.domain.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}
