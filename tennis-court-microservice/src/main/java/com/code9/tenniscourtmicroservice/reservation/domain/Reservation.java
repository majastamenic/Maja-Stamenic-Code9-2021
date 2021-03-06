package com.code9.tenniscourtmicroservice.reservation.domain;

import com.code9.tenniscourtmicroservice.timeslot.domain.Timeslot;
import com.code9.tenniscourtmicroservice.tennis_court.domain.TennisCourt;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "reservation_db")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_user_id")
    private Long firstUserId;
    @Column(name = "second_user_id")
    private Long secondUserId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="tennis_court_id")
    private TennisCourt tennisCourt;
    @OneToMany
    @JoinTable(
            name = "reservation_timeslots",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "timeslot_id")
    )
    private Set<Timeslot> timeslots;
    @Column
    private Boolean deleted;
    @Column
    private Boolean paid;

}
