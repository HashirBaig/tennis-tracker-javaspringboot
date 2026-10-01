package com.hashir.tennistracker.match;

public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException(Long id) {
        super("Match not found with id: " + id);
    }
}