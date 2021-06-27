package com.code9.notificationmicroservice.rabbitMQ;

import com.code9.notificationmicroservice.service.ReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessagesListener {
    private final ReservationService reservationService;

    public MessagesListener(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @RabbitListener(queues = "user-update-queue")
    public void receiveUserMessage(String message) {
        NewReservationMessage newReservationMessage = null;
        try {
            newReservationMessage = new ObjectMapper().readValue(message, NewReservationMessage.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }
        reservationService.save("New reservation for " + newReservationMessage.getFirstEmail() +
                " and " + newReservationMessage.getSecondEmail() + " on tennis court " + newReservationMessage.getTennisCourtName());
    }
}
