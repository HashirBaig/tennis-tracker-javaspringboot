package com.hashir.tennistracker.player;

import jakarta.validation.constraints.NotBlank;

public record PlayerRequest(
        @NotBlank String playerName
) {
}