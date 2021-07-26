package com.code9.notificationmicroservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "text")
    String text;
}
