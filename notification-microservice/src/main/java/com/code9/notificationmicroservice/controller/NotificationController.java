package com.code9.notificationmicroservice.controller;

import com.code9.notificationmicroservice.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class NotificationController {
    public final ReservationService reservationService;

    @PostMapping("/text")
    public ResponseEntity saveReservation(@PathVariable String text){
        return new ResponseEntity(reservationService.save(text), HttpStatus.OK);
    }
}
