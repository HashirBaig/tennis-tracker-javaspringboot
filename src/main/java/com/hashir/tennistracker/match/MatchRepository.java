package com.hashir.tennistracker.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends  JpaRepository<Match, Long>{
    @Query("select m from Match m join fetch m.playerOne join fetch m.playerTwo order by m.createDate desc")
    List<Match> findAllWithPlayers();
}

