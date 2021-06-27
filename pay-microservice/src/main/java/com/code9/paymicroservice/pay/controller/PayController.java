package com.code9.paymicroservice.pay.controller;

import com.code9.paymicroservice.pay.domain.CreditCard;
import com.code9.paymicroservice.pay.service.PayService;
import com.code9.paymicroservice.pay.service.interfaces.IPayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PayController {

    private IPayService payService;

    public PayController(PayService payService) {
        this.payService = payService;
    }

    @PostMapping("/credit_card/{id}")
    public ResponseEntity payCreditCard(@PathVariable Long id, @RequestBody CreditCard creditCard) {
        return new ResponseEntity(payService.payCreditCard(id, creditCard), HttpStatus.OK);
    }

    @PostMapping("/cash/{id}")
    public ResponseEntity payCash(@PathVariable Long id){
        return new ResponseEntity(payService.payCash(id), HttpStatus.OK);
    }
}
