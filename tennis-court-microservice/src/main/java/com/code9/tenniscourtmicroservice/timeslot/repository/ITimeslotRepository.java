package com.code9.tenniscourtmicroservice.timeslot.repository;

import com.code9.tenniscourtmicroservice.timeslot.domain.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITimeslotRepository extends JpaRepository<Timeslot, Long> {
    @Query(value = "select t from Timeslot t where t.id=(:id) and (t.deleted=false or t.deleted is null)")
    Timeslot findTimeslotById(@Param("id") Long id);
}
