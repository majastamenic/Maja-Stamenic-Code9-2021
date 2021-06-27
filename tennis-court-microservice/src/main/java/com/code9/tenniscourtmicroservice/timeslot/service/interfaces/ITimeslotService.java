package com.code9.tenniscourtmicroservice.timeslot.service.interfaces;

import com.code9.tenniscourtmicroservice.timeslot.domain.Timeslot;
import org.springframework.stereotype.Service;

@Service
public interface ITimeslotService {
    Timeslot create(Timeslot timeslot);
    Timeslot delete(Long id);
}
