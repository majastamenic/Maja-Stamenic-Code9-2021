package com.code9.tenniscourtmicroservice.rabbitMQ;

import com.code9.tenniscourtmicroservice.reservation.domain.Reservation;
import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;

@UtilityClass
public class MessageFactory {
    public static NewReservationMessage createNewReservationMessage(Reservation reservation) {
        return NewReservationMessage
                .builder()
                .firstUserId(reservation.getFirstUserId())
                .secondUserId(reservation.getSecondUserId())
                .tennisCourtName(reservation.getTennisCourt().getName())
                .version(1)
                .sendingTime(OffsetDateTime.now().toString())
                .build();
    }
}
