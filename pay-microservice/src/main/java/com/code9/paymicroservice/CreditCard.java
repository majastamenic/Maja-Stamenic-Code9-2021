package com.code9.paymicroservice;

import com.sun.istack.Interned;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
public class CreditCard {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long number;
    @Column(name = "valid_date")
    private Date validDate;
    @Column(name = "cvc_code")
    private int cvcCode;
}
