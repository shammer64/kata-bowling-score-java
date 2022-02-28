package com.shammer.coaching.bowling;

public class Game {
    public Game(String frameMarkings) {
        super();
        if (frameMarkings == null || frameMarkings.isBlank())
            throw new IllegalArgumentException("Frame marking cannot be null or empty");

    }
}
