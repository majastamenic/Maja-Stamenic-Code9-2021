package com.code9.tenniscourtmicroservice.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final RabbitTemplate rabbitTemplate;
    private final Queue reservationQueue;

    public MessageService(RabbitTemplate rabbitTemplate, Queue reservationQueue){
        this.rabbitTemplate = rabbitTemplate;
        this.reservationQueue = reservationQueue;
    }

    public void sendMessageToNewReservationTopic(NewReservationMessage message){
        String text = "{}";
        try{
            text = new ObjectMapper().writeValueAsString(message);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend(reservationQueue.getName(), text);
    }
}
