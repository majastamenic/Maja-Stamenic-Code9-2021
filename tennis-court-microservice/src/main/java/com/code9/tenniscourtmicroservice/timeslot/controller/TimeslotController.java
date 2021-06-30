package com.code9.tenniscourtmicroservice.timeslot.controller;

import com.code9.tenniscourtmicroservice.timeslot.domain.Timeslot;
import com.code9.tenniscourtmicroservice.timeslot.service.impl.TimeslotServiceImpl;
import com.code9.tenniscourtmicroservice.timeslot.service.TimeslotService;
import com.code9.usermicroservice.client.UserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/timeslot")
@Api(value = "Timeslot endpoints")
@AllArgsConstructor
public class TimeslotController {

    private final TimeslotService timeslotService;
    private final UserClient userClient;

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete timeslot by id", notes = "", response = Timeslot.class)
    public ResponseEntity delete(@PathVariable Long id, @RequestHeader("admin-username") String adminUsername){
        userClient.checkIsAdmin(adminUsername);
        return new ResponseEntity(timeslotService.delete(id), HttpStatus.OK);
    }
}
