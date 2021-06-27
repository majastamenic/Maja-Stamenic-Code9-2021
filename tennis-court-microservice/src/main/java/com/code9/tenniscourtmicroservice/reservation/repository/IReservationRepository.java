package com.code9.tenniscourtmicroservice.reservation.repository;

import com.code9.tenniscourtmicroservice.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "select r from Reservation r where (r.firstUserId=(:id) or r.secondUserId=(:id)) " +
            "and (r.deleted is null or r.deleted=false) and (r.paid=true or r.paid is null)")
    List<Reservation> findReservationByUserId(@Param("id") Long id);

    @Query(value = "select r from Reservation r where (r.deleted is null or r.deleted=false) " +
            "and r.tennisCourt.id = (:id) and (r.tennisCourt.deleted is null or r.tennisCourt.deleted=false)" +
            "and (r.paid=true or r.paid is null)")
    List<Reservation> findReservationByTennisCourt_Id(@Param("id") Long id);

    @Query(value = "select r from Reservation r where (r.deleted is null or r.deleted=false) and r.id=(:id)")
    Reservation findReservationById(@Param("id") Long id);
}
