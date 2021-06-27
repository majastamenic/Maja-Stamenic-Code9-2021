package com.code9.tenniscourtmicroservice.timeslot.controller.mapping;

import com.code9.tenniscourtmicroservice.timeslot.controller.dto.TimeslotDto;
import com.code9.tenniscourtmicroservice.timeslot.domain.Timeslot;

public class TimeslotMapper {

    public static Timeslot mapTimeslotDtoToTimeslot(TimeslotDto timeslotDto) {
        Timeslot timeslot = new Timeslot();
        timeslot.setStartTime(timeslotDto.getStartTime());
        timeslot.setEndTime(timeslotDto.getEndTime());
        return timeslot;
    }

    public static TimeslotDto mapTimeslotToTimeslotDto(Timeslot timeslot) {
        TimeslotDto timeslotDto = new TimeslotDto();
        timeslotDto.setStartTime(timeslot.getStartTime());
        timeslotDto.setEndTime(timeslot.getEndTime());
        return timeslotDto;
    }
}
