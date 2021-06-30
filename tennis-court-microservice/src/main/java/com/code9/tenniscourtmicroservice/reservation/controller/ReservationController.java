package com.code9.tenniscourtmicroservice.reservation.controller;

import com.code9.tenniscourtmicroservice.exception.BadRequestException;
import com.code9.tenniscourtmicroservice.reservation.controller.dto.ReservationDto;
import com.code9.tenniscourtmicroservice.reservation.controller.mapping.ReservationMapper;
import com.code9.tenniscourtmicroservice.reservation.domain.Reservation;
import com.code9.tenniscourtmicroservice.reservation.service.ReservationService;
import com.code9.usermicroservice.client.UserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reservation")
@Api(value = "Reservation endpoints")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserClient userClient;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get reservation by id.", notes = "", response = Reservation.class)
    public ResponseEntity getById(@PathVariable Long id) {
        return new ResponseEntity(reservationService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create reservation.", notes = "", response = Reservation.class)
    public ResponseEntity create(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationService.create(
                ReservationMapper.mapReservationDtoToReservation(reservationDto));

        if (Boolean.FALSE.equals(reservation.getDeleted()))
            throw new BadRequestException("You need to pay 10 euros. Reservation id is: " + reservation.getId());

        return new ResponseEntity(reservation, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "Update reservation.", notes = "", response = Reservation.class)
    public ResponseEntity update(@RequestBody ReservationDto reservationDto, @RequestHeader("admin-username") String adminUsername) {
        userClient.checkIsAdmin(adminUsername);
        return new ResponseEntity(
                reservationService.update(ReservationMapper.mapReservationDtoToReservation(reservationDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete reservation by id.", notes = "", response = Reservation.class)
    public ResponseEntity delete(@PathVariable Long id, @RequestHeader("admin-username") String adminUsername) {
        userClient.checkIsAdmin(adminUsername);
        return new ResponseEntity(reservationService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/paid/{id}")
    @ApiOperation(value = "Pay reservation", notes = "", response = Reservation.class)
    public ResponseEntity paid(@PathVariable Long id) {
        return new ResponseEntity(reservationService.paid(id), HttpStatus.OK);
    }
}
