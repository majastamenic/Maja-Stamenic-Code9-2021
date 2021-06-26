package com.code9.tenniscourtmicroservice.reservation.domain;

import com.code9.tenniscourtmicroservice.timeslot.domain.Timeslot;
import com.code9.tenniscourtmicroservice.tennis_court.domain.TennisCourt;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_user_id")
    private Long firstUserId;
    @Column(name = "second_user_id")
    private Long secondUserId;
    @ManyToOne
    @JoinColumn(name="tennis_court_id")
    private TennisCourt tennisCourt;
    @ManyToOne
    @JoinColumn(name="timeslot_id")
    private Timeslot timeslot;

}
