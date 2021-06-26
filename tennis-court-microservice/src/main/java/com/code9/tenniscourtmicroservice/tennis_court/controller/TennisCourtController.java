package com.code9.tenniscourtmicroservice.tennis_court.controller;

import com.code9.tenniscourtmicroservice.communication.IUserCommunication;
import com.code9.tenniscourtmicroservice.tennis_court.domain.TennisCourt;
import com.code9.tenniscourtmicroservice.tennis_court.service.TennisCourtService;
import com.code9.tenniscourtmicroservice.tennis_court.service.interfaces.ITennisCourtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TennisCourtController {

    private ITennisCourtService tennisCourtService;
    private IUserCommunication userCommunication;

    public TennisCourtController(TennisCourtService tennisCourtService, IUserCommunication userCommunication){
        this.tennisCourtService = tennisCourtService;
        this.userCommunication = userCommunication;
    }

    @GetMapping
    public ResponseEntity getAll(){
        return new ResponseEntity(tennisCourtService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{email}")
    public ResponseEntity create(@RequestBody TennisCourt tennisCourt, @PathVariable String email){
        userCommunication.checkIsAdmin(email);
        return new ResponseEntity(tennisCourtService.create(tennisCourt), HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity update(@RequestBody TennisCourt tennisCourt, @PathVariable String email){
        userCommunication.checkIsAdmin(email);
        return new ResponseEntity(tennisCourtService.update(tennisCourt), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{email}")
    public ResponseEntity delete(@PathVariable Long id, @PathVariable String email){
        userCommunication.checkIsAdmin(email);
        tennisCourtService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
