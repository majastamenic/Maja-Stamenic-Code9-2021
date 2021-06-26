package com.code9.tenniscourtmicroservice.communication;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pay-microservice")
public interface IPayCommunication {
}
