package com.code9.tenniscourtmicroservice.tennis_court.service.interfaces;

import com.code9.tenniscourtmicroservice.tennis_court.domain.TennisCourt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITennisCourtService {

    List<TennisCourt> getAll();

    TennisCourt create(TennisCourt tennisCourt);

    TennisCourt update(TennisCourt tennisCourt);

    TennisCourt delete(Long id);

    TennisCourt findById(Long id);

    TennisCourt findByName(String name);
}
