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
        return balls.get(1);
    }

    public Character third() {
        if (balls.size() < 3) return null;
        return balls.get(2);
    }
}
