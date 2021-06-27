package com.code9.tenniscourtmicroservice.timeslot.service.impl;

import com.code9.tenniscourtmicroservice.exception.NotFoundException;
import com.code9.tenniscourtmicroservice.timeslot.domain.Timeslot;
import com.code9.tenniscourtmicroservice.timeslot.repository.TimeslotRepository;
import com.code9.tenniscourtmicroservice.timeslot.service.TimeslotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TimeslotServiceImpl implements TimeslotService {

    private final TimeslotRepository timeslotRepository;

    @Override
    public Timeslot create(Timeslot timeslot) {
        return timeslotRepository.save(timeslot);
    }

    @Override
    public Timeslot delete(Long id) {
        Timeslot timeslot = findById(id);
        timeslot.setDeleted(Boolean.TRUE);
        return timeslotRepository.save(timeslot);
    }

    private Timeslot findById(Long id) {
        Timeslot timeslot = timeslotRepository.findTimeslotById(id);
        if (timeslot == null)
            throw new NotFoundException("There is no timeslot with id: " + id);
        return timeslot;
    }
}
