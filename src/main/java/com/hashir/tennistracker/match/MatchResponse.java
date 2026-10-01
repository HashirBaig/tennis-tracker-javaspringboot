package com.hashir.tennistracker.match;

import com.hashir.tennistracker.player.PlayerResponse;
import java.time.LocalDateTime;

public record MatchResponse(
        Long id,
        PlayerResponse playerOne,
        PlayerResponse playerTwo,
        LocalDateTime createDate,
        Long numberOfSets,
        Long numberOfGames,
        Long gamesWonByPlayerOne,
        Long gamesWonByPlayerTwo
        ) {
        public static MatchResponse fromEntity(Match match) {
            return new MatchResponse(
                    match.getId(),
                    PlayerResponse.fromEntity(match.getPlayerOne()),
                    PlayerResponse.fromEntity(match.getPlayerTwo()),
                    match.getCreateDate(),
                    match.getNumberOfSets(),
                    match.getNumberOfGames(),
                    match.getGamesWonByPlayerOne(),
                    match.getGamesWonByPlayerTwo()
            );
        }
}