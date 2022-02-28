package com.shammer.coaching.bowling;

import java.util.List;
import java.util.stream.Collectors;

public class Frame {

    private List<Character> balls;

    public Frame(String frameMarking) {
        super();
        parseFrame(frameMarking);
    }

    private void parseFrame(String frameMarking) {
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
