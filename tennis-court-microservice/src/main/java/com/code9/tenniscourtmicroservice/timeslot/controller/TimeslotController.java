package com.code9.tenniscourtmicroservice.timeslot.controller;

import com.code9.tenniscourtmicroservice.timeslot.domain.Timeslot;
import com.code9.tenniscourtmicroservice.timeslot.service.TimeslotService;
import com.code9.tenniscourtmicroservice.timeslot.service.interfaces.ITimeslotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timeslot")
@Api(value = "Timeslot endpoints")
public class TimeslotController {

    private ITimeslotService timeslotService;

    public TimeslotController(TimeslotService timeslotService){
        this.timeslotService = timeslotService;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete timeslot by id", notes = "", response = Timeslot.class)
    public ResponseEntity delete(@PathVariable Long id){
        return new ResponseEntity(timeslotService.delete(id), HttpStatus.OK);
    }
}
