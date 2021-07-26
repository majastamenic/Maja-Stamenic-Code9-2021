package com.code9.paymicroservice.pay.service;

import com.code9.paymicroservice.pay.domain.CreditCard;
import org.springframework.stereotype.Service;

@Service
public interface PayService {

    String payCreditCard(Long reservationId, CreditCard creditCard);

    String payCash(Long id);
}
