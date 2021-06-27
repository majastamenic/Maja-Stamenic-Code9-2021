package com.code9.tenniscourtmicroservice.reservation.controller;

import com.code9.tenniscourtmicroservice.reservation.domain.Reservation;
import com.code9.tenniscourtmicroservice.reservation.service.ReservationService;
import com.code9.tenniscourtmicroservice.reservation.service.interfaces.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private IReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Reservation reservation){
        return new ResponseEntity(reservationService.create(reservation), HttpStatus.OK);
    }

}
