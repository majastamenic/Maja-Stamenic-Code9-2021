package com.code9.tenniscourtmicroservice.tennis_court.repository;

import com.code9.tenniscourtmicroservice.tennis_court.domain.TennisCourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITennisCourtRepository extends JpaRepository<TennisCourt, Long> {
    TennisCourt findTennisCourtById(Long id);
}
