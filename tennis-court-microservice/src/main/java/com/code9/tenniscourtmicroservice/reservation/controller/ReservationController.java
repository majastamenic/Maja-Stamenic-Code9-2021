package com.code9.tenniscourtmicroservice.reservation.controller;

import com.code9.tenniscourtmicroservice.exception.BadRequestException;
import com.code9.tenniscourtmicroservice.reservation.controller.dto.ReservationDto;
import com.code9.tenniscourtmicroservice.reservation.controller.mapping.ReservationMapper;
import com.code9.tenniscourtmicroservice.reservation.domain.Reservation;
import com.code9.tenniscourtmicroservice.reservation.service.ReservationService;
import com.code9.tenniscourtmicroservice.reservation.service.interfaces.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private IReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        return new ResponseEntity(reservationService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ReservationDto reservationDto){
        Reservation reservation = reservationService.create(
                ReservationMapper.mapReservationDtoToReservation(reservationDto));

        if(Boolean.FALSE.equals(reservation.getDeleted()))
            throw new BadRequestException("You need to pay 10 euros. Reservation id is: " + reservation.getId());

        return new ResponseEntity(reservation, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ReservationDto reservationDto){
        return new ResponseEntity(
                reservationService.update(ReservationMapper.mapReservationDtoToReservation(reservationDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return new ResponseEntity(reservationService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/paid/{id}")
    public ResponseEntity paid(@PathVariable Long id){
        return new ResponseEntity(reservationService.paid(id), HttpStatus.OK);
    }
}
