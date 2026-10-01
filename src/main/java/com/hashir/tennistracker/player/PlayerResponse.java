package com.hashir.tennistracker.player;

public record PlayerResponse(
        Long id,
        String playerName
) {
    public static PlayerResponse fromEntity (Player player){
        return new PlayerResponse(
                player.getId(),
                player.getPlayerName()
        );
    }
}