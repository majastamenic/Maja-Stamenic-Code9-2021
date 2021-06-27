package com.code9.tenniscourtmicroservice.reservation.controller.dto;

import com.code9.tenniscourtmicroservice.tennis_court.controller.dto.TennisCourtDto;
import com.code9.tenniscourtmicroservice.timeslot.controller.dto.TimeslotDto;
import lombok.Data;

import java.util.Set;

@Data
public class ReservationDto {
    private Long firstUserId;
    private Long secondUserId;
    private TennisCourtDto tennisCourt;
    private Set<TimeslotDto> timeslotDtos;
}
