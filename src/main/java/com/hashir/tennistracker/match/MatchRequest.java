package com.hashir.tennistracker.match;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record MatchRequest(
        @NotBlank String playerOne,
        @NotBlank String playerTwo,
        @NotNull @Positive Long numberOfSets,
        @NotNull @Positive Long numberOfGames,
        @NotNull @PositiveOrZero Long gamesWonByPlayerOne,
        @NotNull @PositiveOrZero Long gamesWonByPlayerTwo
) {

}