package com.code9.tenniscourtmicroservice.tennis_court.service;

import com.code9.tenniscourtmicroservice.exception.NotFoundException;
import com.code9.tenniscourtmicroservice.tennis_court.domain.TennisCourt;
import com.code9.tenniscourtmicroservice.tennis_court.repository.ITennisCourtRepository;
import com.code9.tenniscourtmicroservice.tennis_court.service.interfaces.ITennisCourtService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TennisCourtService implements ITennisCourtService {

    private ITennisCourtRepository tennisCourtRepository;

    public TennisCourtService(ITennisCourtRepository tennisCourtRepository) {
        this.tennisCourtRepository = tennisCourtRepository;
    }

    @Override
    public List<TennisCourt> getAll() {
        List<TennisCourt> tennisCourts = tennisCourtRepository.findAll();
        tennisCourts.stream().filter(tennisCourt -> tennisCourt.getDeleted()!=Boolean.TRUE);
        if(tennisCourts.isEmpty())
            throw new NotFoundException("There is no any tennis court");
        return tennisCourts;
    }

    @Override
    public TennisCourt create(TennisCourt tennisCourt) {
        return tennisCourtRepository.save(tennisCourt);
    }

    @Override
    public TennisCourt update(TennisCourt tennisCourt) {
        TennisCourt dbCourt = findById(tennisCourt.getId());
        dbCourt.setName(tennisCourt.getName());
        return tennisCourtRepository.save(dbCourt);
    }

    @Override
    public TennisCourt delete(Long id) {
        TennisCourt tennisCourt = findById(id);
        tennisCourt.setDeleted(Boolean.TRUE);
        return tennisCourtRepository.save(tennisCourt);
    }

    @Override
    public TennisCourt findById(Long id){
        TennisCourt tennisCourt = tennisCourtRepository.findTennisCourtById(id);
        if(tennisCourt == null)
            throw new NotFoundException("There is no tennis court with id: " + id);
        return tennisCourt;
    }

    @Override
    public TennisCourt findByName(String name) {
        TennisCourt tennisCourt = tennisCourtRepository.findTennisCourtByName(name);
        if(tennisCourt == null)
            throw new NotFoundException("There is no tennis court with name: " + name);
        return tennisCourt;
    }

}
