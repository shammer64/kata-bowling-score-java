package com.shammer.coaching.bowling;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Frame {

    private List<Character> balls;
    private static final String REGEX = "[123456789X/-]{1,3}";

    public Frame(String frameMarking) {
        super();
        parseFrame(frameMarking);
    }

    private void parseFrame(String frameMarking) {
        if (frameMarking == null || frameMarking.isBlank())
            throw new IllegalArgumentException("Frame marking cannot be null or empty");
        if (!Pattern.matches(REGEX, frameMarking))
            throw new IllegalArgumentException("Frame marking contains invalid characters");
        balls = frameMarking.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
    }

    public Character first() {
        return balls.get(0);
    }

    public Character second() {
        if (balls.size() < 2) return null;
        return balls.get(1);
    }

    public Character third() {
        if (balls.size() < 3) return null;
        return balls.get(2);
    }

    public boolean isSpare() {
        return second().equals('/');
    }

    public boolean isStrike() {
        return first().equals('X');
    }

    public int ballsThrown() {
        return balls.size();
    }

    public int firstBallScore() {
        if (first().equals('-')) return 0;
        if (first().equals('X')) return 10;
        return Character.getNumericValue(first());
    }

    public int secondBallScore() {
        if (second().equals('-')) return 0;
        if (second().equals('X')) return 10;
        if (second().equals('/'))
            return 10 - Character.getNumericValue(first());
        return Character.getNumericValue(second());
    }

    public int thirdBallScore() {
        if (third().equals('-')) return 0;
        if (third().equals('X')) return 10;
        if (third().equals('/'))
            return 10 - Character.getNumericValue(second());
        return Character.getNumericValue(third());
    }
}
