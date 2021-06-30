package com.code9.notificationmicroservice.rabbitMQ;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewReservationMessage extends BaseMessage {

    @JsonProperty("first_user_id")
    Long firstUserId;

    @JsonProperty("second_user_id")
    Long secondUserId;

    @JsonProperty("tennis_court_name")
    String tennisCourtName;
}
