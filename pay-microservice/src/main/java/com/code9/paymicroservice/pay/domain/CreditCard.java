package com.code9.paymicroservice.pay.domain;

import com.code9.paymicroservice.pay.validation.ValidDate;
import lombok.Data;

@Data
public class CreditCard {

    private Long number;
    @ValidDate
    private String validDate;
    private int cvcCode;
}
