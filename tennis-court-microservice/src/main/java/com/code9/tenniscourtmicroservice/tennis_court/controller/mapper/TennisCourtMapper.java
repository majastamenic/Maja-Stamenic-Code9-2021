package com.code9.tenniscourtmicroservice.tennis_court.controller.mapper;

import com.code9.tenniscourtmicroservice.tennis_court.controller.dto.TennisCourtDto;
import com.code9.tenniscourtmicroservice.tennis_court.domain.TennisCourt;

public class TennisCourtMapper {

    public static TennisCourt mapTennisCourtDtoToTennisCourt(TennisCourtDto tennisCourtDto){
        TennisCourt tennisCourt = new TennisCourt();
        tennisCourt.setName(tennisCourtDto.getName());
        return tennisCourt;
    }

    public static TennisCourtDto mapTennisCourtToTennisCourtDto(TennisCourt tennisCourt){
        TennisCourtDto tennisCourtDto = new TennisCourtDto();
        tennisCourtDto.setName(tennisCourt.getName());
        return tennisCourtDto;
    }
}
