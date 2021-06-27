package com.code9.tenniscourtmicroservice.timeslot.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TimeslotDto {
    private Date startTime;
    private Date endTime;
}
