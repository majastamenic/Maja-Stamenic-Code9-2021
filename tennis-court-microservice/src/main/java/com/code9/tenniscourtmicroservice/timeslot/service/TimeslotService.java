package com.code9.tenniscourtmicroservice.timeslot.service;

import com.code9.tenniscourtmicroservice.timeslot.domain.Timeslot;
import org.springframework.stereotype.Service;

@Service
public interface TimeslotService {
    Timeslot create(Timeslot timeslot);
    Timeslot delete(Long id);
}
