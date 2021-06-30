package com.code9.tenniscourtmicroservice.tennis_court.controller;

import com.code9.tenniscourtmicroservice.tennis_court.controller.dto.TennisCourtDto;
import com.code9.tenniscourtmicroservice.tennis_court.controller.mapper.TennisCourtMapper;
import com.code9.tenniscourtmicroservice.tennis_court.service.impl.TennisCourtServiceImpl;
import com.code9.tenniscourtmicroservice.tennis_court.service.TennisCourtService;
import com.code9.usermicroservice.client.UserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Api(value = "Tennis court endpoints")
public class TennisCourtController {

    private final TennisCourtService tennisCourtService;
    private final UserClient userClient;

    @GetMapping
    @ApiOperation(value = "Get all tennis courts.", notes = "", response = ResponseEntity.class)
    public ResponseEntity<List<TennisCourtDto>> getAll() {
        List<TennisCourtDto> tennisCourtDtos = tennisCourtService.getAll().stream().map(TennisCourtMapper::mapTennisCourtToTennisCourtDto).collect(Collectors.toList());
        return new ResponseEntity(tennisCourtDtos, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create tennis court.", notes = "", response = TennisCourtDto.class)
    public ResponseEntity<TennisCourtDto> create(@RequestBody TennisCourtDto tennisCourtDto, @RequestHeader("admin-username") String adminUsername) {
        userClient.checkIsAdmin(adminUsername);
        return new ResponseEntity(tennisCourtService.create(TennisCourtMapper.mapTennisCourtDtoToTennisCourt(tennisCourtDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete tennis court.", notes = "", response = TennisCourtDto.class)
    public ResponseEntity delete(@PathVariable Long id, @RequestHeader("admin-username") String adminUsername) {
        userClient.checkIsAdmin(adminUsername);
        return new ResponseEntity(tennisCourtService.delete(id), HttpStatus.OK);
    }
}
