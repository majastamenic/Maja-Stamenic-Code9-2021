package com.code9.usermicroservice.tennis_player.domain;

import com.code9.usermicroservice.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TennisPlayer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
