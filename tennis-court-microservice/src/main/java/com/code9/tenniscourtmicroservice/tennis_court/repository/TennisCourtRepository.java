package com.code9.tenniscourtmicroservice.tennis_court.repository;

import com.code9.tenniscourtmicroservice.tennis_court.domain.TennisCourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TennisCourtRepository extends JpaRepository<TennisCourt, Long> {
    @Query(value = "select t from TennisCourt t where (t.deleted is null or t.deleted=false) and t.id=(:id)")
    TennisCourt findTennisCourtById(@Param("id") Long id);
    @Query(value = "select t from TennisCourt t where (t.deleted is null or t.deleted=false) and t.name=(:name)")
    TennisCourt findTennisCourtByName(@Param("name") String name);
}
