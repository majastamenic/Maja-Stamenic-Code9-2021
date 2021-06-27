package com.code9.notificationmicroservice.rabbitMQ;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewReservationMessage extends BaseMessage {

    @JsonProperty("first_email")
    String firstEmail;

    @JsonProperty("second_email")
    String secondEmail;

    @JsonProperty("tennis_court_name")
    String tennisCourtName;
}
