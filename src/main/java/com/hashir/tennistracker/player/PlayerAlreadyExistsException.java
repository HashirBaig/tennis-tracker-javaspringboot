package com.hashir.tennistracker.player;

public class PlayerAlreadyExistsException extends RuntimeException {
    public PlayerAlreadyExistsException(String playerName) {
        super("Player already exists with name: " + playerName);
    }
}