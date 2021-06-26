package com.code9.tenniscourtmicroservice.timeslot.repository;

import com.code9.tenniscourtmicroservice.timeslot.domain.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITimeslotRepository extends JpaRepository<Timeslot, Long> {
}
