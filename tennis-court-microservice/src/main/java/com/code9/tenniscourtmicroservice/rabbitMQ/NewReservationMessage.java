package com.code9.tenniscourtmicroservice.rabbitMQ;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class NewReservationMessage extends BaseMessage{
    @JsonProperty("first_user_id")
    Long firstUserId;

    @JsonProperty("second_user_id")
    Long secondUserId;

    @JsonProperty("tennis_court_name")
    String tennisCourtName;
}
