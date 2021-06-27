package com.code9.paymicroservice.pay.controller;

import com.code9.paymicroservice.pay.domain.CreditCard;
import com.code9.paymicroservice.pay.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
@Api(value = "Pay endpoints")
public class PayController {

    private final PayService payService;

    @PostMapping("/credit_card/{id}")
    @ApiOperation(value = "Pay by credit card", notes = "", response = ResponseEntity.class)
    public ResponseEntity payCreditCard(@PathVariable Long id, @RequestBody CreditCard creditCard) {
        return new ResponseEntity(payService.payCreditCard(id, creditCard), HttpStatus.OK);
    }

    @PostMapping("/cash/{id}")
    @ApiOperation(value = "Pay by cash", notes = "", response = ResponseEntity.class)
    public ResponseEntity payCash(@PathVariable Long id) {
        return new ResponseEntity(payService.payCash(id), HttpStatus.OK);
    }
}
