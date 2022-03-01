package com.shammer.coaching.bowling;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
    private LinkedList<Frame> frameList;

    public Game(String frameMarkings) {
        super();
        if (frameMarkings == null || frameMarkings.isBlank())
            throw new IllegalArgumentException("Frame marking cannot be null or empty");
        loadFrames(frameMarkings);
    }

    private void loadFrames(String frameMarkings) {
        frameList = new LinkedList<Frame>();
        frameList.addAll(
                Arrays.stream(frameMarkings.split("\\s"))
                .map(Frame::new).collect(Collectors.toList())
        );
    }

    public List<Frame> getFrames() {
        return frameList;
    }

    public int getScore() {
        return IntStream.range(0, frameList.size())
                .map(this::computeFrameScore)
                .sum();
    }

    private int computeFrameScore(int index) {
        Frame currFrame = frameList.get(index);
        if (currFrame.isSpare())
            return computeSpareFrame(currFrame,
                    index >= 9 ? null: frameList.get(index + 1));
        if (currFrame.isStrike())
            return computeStrikeFrame(currFrame,
                    index >= 9 ? null: frameList.get(index + 1),
                    index >= 8 ? null: frameList.get(index + 2));
        return computeSimpleFrameScore(currFrame);
    }

    private int computeStrikeFrame(Frame currFrame, Frame nextFrame, Frame nextNextFrame) {
        int frameScore = 0;
        if (currFrame.ballsThrown() == 1) {
            frameScore += 10;
            if (nextFrame.ballsThrown() == 1)
                frameScore += (nextFrame.firstBallScore() +
                            nextNextFrame.firstBallScore());
            else
                frameScore += (nextFrame.firstBallScore() +
                            nextFrame.secondBallScore());
        } else// 10th frame
            return currFrame.firstBallScore() + currFrame.secondBallScore() + currFrame.thirdBallScore();
        return frameScore;
    }

    private int computeSpareFrame(Frame currFrame, Frame nextFrame) {
        if (nextFrame == null) // last frame?
            return 10 + currFrame.thirdBallScore();
        return 10 + nextFrame.firstBallScore();
    }

    private int computeSimpleFrameScore(Frame currFrame) {
        int frameScore = 0;
        frameScore += currFrame.firstBallScore();
        frameScore += currFrame.secondBallScore();
        if (currFrame.ballsThrown() == 3)
            frameScore += currFrame.thirdBallScore();
        return frameScore;
    }
}
