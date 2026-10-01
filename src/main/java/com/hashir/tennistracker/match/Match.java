package com.hashir.tennistracker.match;

import com.hashir.tennistracker.player.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches", indexes = {
        @Index(name = "idx_matches_player_one", columnList = "player_one_id"),
        @Index(name = "idx_matches_player_two", columnList = "player_two_id")
})
@Getter
@Setter
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_one_id", nullable = false)
    private Player playerOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_two_id", nullable = false)
    private Player playerTwo;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDate;

    private Long numberOfSets;
    private Long numberOfGames;
    private Long gamesWonByPlayerOne;
    private Long gamesWonByPlayerTwo;
}