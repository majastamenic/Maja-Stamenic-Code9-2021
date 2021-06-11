package com.code9.usermicroservice.tennis_player.repository;

import com.code9.usermicroservice.tennis_player.domain.TennisPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITennisPlayerRepository extends JpaRepository<TennisPlayer, Long> { }
