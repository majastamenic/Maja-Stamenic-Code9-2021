package com.code9.tenniscourtmicroservice.timeslot.service;

import com.code9.tenniscourtmicroservice.exception.NotFoundException;
import com.code9.tenniscourtmicroservice.timeslot.domain.Timeslot;
import com.code9.tenniscourtmicroservice.timeslot.repository.ITimeslotRepository;
import com.code9.tenniscourtmicroservice.timeslot.service.interfaces.ITimeslotService;
import org.springframework.stereotype.Service;

@Service
public class TimeslotService implements ITimeslotService {

    private ITimeslotRepository timeslotRepository;

    public TimeslotService(ITimeslotRepository timeslotRepository) {
        this.timeslotRepository = timeslotRepository;
    }

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
