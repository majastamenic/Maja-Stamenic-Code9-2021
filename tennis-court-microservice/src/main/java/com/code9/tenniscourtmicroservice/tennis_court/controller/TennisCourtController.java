package com.code9.tenniscourtmicroservice.tennis_court.controller;

import com.code9.tenniscourtmicroservice.tennis_court.controller.dto.TennisCourtDto;
import com.code9.tenniscourtmicroservice.tennis_court.controller.mapper.TennisCourtMapper;
import com.code9.tenniscourtmicroservice.tennis_court.service.TennisCourtService;
import com.code9.tenniscourtmicroservice.tennis_court.service.interfaces.ITennisCourtService;
import com.code9.usermicroservice.user.client.UserClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class TennisCourtController {

    private ITennisCourtService tennisCourtService;
    private UserClient userClient;

    public TennisCourtController(TennisCourtService tennisCourtService, UserClient userClient){
        this.tennisCourtService = tennisCourtService;
        this.userClient = userClient;
    }

    @GetMapping
    public ResponseEntity<List<TennisCourtDto>> getAll(){
        List<TennisCourtDto> tennisCourtDtos = tennisCourtService.getAll().stream().map(TennisCourtMapper:: mapTennisCourtToTennisCourtDto).collect(Collectors.toList());
        return new ResponseEntity(tennisCourtDtos, HttpStatus.OK);
    }

    @PostMapping("/{email}")
    public ResponseEntity<TennisCourtDto> create(@RequestBody TennisCourtDto tennisCourtDto, @PathVariable String email){
        userClient.checkIsAdmin(email);
        return new ResponseEntity(tennisCourtService.create(TennisCourtMapper.mapTennisCourtDtoToTennisCourt(tennisCourtDto)), HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity<TennisCourtDto> update(@RequestBody TennisCourtDto tennisCourtDto, @PathVariable String email){
        userClient.checkIsAdmin(email);
        return new ResponseEntity(tennisCourtService.update(TennisCourtMapper.mapTennisCourtDtoToTennisCourt(tennisCourtDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{email}")
    public ResponseEntity delete(@PathVariable Long id, @PathVariable String email){
        userClient.checkIsAdmin(email);
        return new ResponseEntity(tennisCourtService.delete(id), HttpStatus.OK);
    }
}
