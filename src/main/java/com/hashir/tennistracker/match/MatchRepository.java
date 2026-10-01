package com.hashir.tennistracker.match;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchRepository extends  JpaRepository<Match, Long>{
    @Query(
            value = "select m from Match m join fetch m.playerOne join fetch m.playerTwo "
                    + "order by m.createDate desc, m.id desc",
            countQuery = "select count(m) from Match m"
    )
    Page<Match> findAllWithPlayers(Pageable pageable);
}

