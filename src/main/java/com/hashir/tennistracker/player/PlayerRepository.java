package com.hashir.tennistracker.player;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends  JpaRepository<Player, Long>{
    Optional<Player> findByPlayerName(String playerName);
    boolean existsByPlayerName(String playerName);
}

