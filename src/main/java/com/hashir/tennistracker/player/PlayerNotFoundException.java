package com.hashir.tennistracker.player;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String playerName) {
        super("Player not found with name: "+ playerName);
    }
}