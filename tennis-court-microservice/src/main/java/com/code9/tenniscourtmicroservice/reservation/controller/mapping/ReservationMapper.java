package com.code9.tenniscourtmicroservice.reservation.controller.mapping;

import com.code9.tenniscourtmicroservice.reservation.controller.dto.ReservationDto;
import com.code9.tenniscourtmicroservice.reservation.domain.Reservation;
import com.code9.tenniscourtmicroservice.tennis_court.controller.mapper.TennisCourtMapper;
import com.code9.tenniscourtmicroservice.timeslot.controller.mapping.TimeslotMapper;

import java.util.stream.Collectors;

public class ReservationMapper {

    public static Reservation mapReservationDtoToReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setFirstUserId(reservationDto.getFirstUserId());
        reservation.setSecondUserId(reservationDto.getSecondUserId());
        reservation.setTennisCourt(TennisCourtMapper.mapTennisCourtDtoToTennisCourt(reservationDto.getTennisCourt()));
        reservation.setTimeslots(reservationDto.getTimeslotDtos().stream().map(TimeslotMapper::mapTimeslotDtoToTimeslot).collect(Collectors.toSet()));
        return reservation;
    }
}
