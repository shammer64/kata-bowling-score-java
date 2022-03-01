package com.shammer.coaching.bowling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FrameTest {

    @Test()
    public void cannot_create_frame_with_null_markings() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Frame(null);
        });
    }

    @Test()
    public void cannot_create_frame_with_empty_markings() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Frame("");
        });
    }

    @Test()
    public void cannot_create_frame_with_invalid_marking_characters() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Frame("0&");
        });
    }

    @Test
    public void can_create_frame_with_1_marking() {
        Frame frame = new Frame("X");

        assertThat(frame.first()).isEqualTo('X');
        assertThat(frame.second()).isNull();
        assertThat(frame.third()).isNull();
    }

    @ParameterizedTest
    @CsvSource({"--,-,-", "-7,-,7", "-/,-,/", "9-,9,-", "81,8,1", "7/,7,/"})
    public void can_create_frame_with_2_markings(String frameMarking, Character ball1, Character ball2) {
        Frame frame = new Frame(frameMarking);

        assertThat(frame.first()).isEqualTo(ball1);
        assertThat(frame.second()).isEqualTo(ball2);
        assertThat(frame.third()).isNull();
    }

    @ParameterizedTest
    @CsvSource({"XXX,X,X,X", "XX7,X,X,7", "X7-,X,7,-", "X71,X,7,1", "X7/,X,7,/", "7/-,7,/,-", "7/9,7,/,9", "7/X,7,/,X"})
    public void can_create_frame_with_3_markings(String frameMarking, Character ball1, Character ball2, Character ball3) {
        Frame frame = new Frame(frameMarking);

        assertThat(frame.first()).isEqualTo(ball1);
        assertThat(frame.second()).isEqualTo(ball2);
        assertThat(frame.third()).isEqualTo(ball3);
    }

    @Test
    public void can_identify_frame_as_spare() {
        Frame frame = new Frame("7/");

        assertThat(frame.isSpare()).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"X", "45", "7-"})
    public void can_identify_frame_as_not_a_spare(String frameMarking) {
        Frame frame = new Frame(frameMarking);

        assertThat(frame.isSpare()).isFalse();
    }

    @Test
    public void can_identify_frame_as_strike() {
        Frame frame = new Frame("X");

        assertThat(frame.isStrike()).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"X,1", "45,2", "XX9,3"})
    public void can_report_number_of_balls_thrown_in_frame(String frameMarking, int expectedBallsThrown) {
        Frame frame = new Frame(frameMarking);

        assertThat(frame.ballsThrown()).isEqualTo(expectedBallsThrown);
    }

    @ParameterizedTest
    @CsvSource({"-7,0", "6/,6", "X,10"})
    public void can_calculate_first_ball_score(String frameMarking, int expectedScore) {
        Frame frame = new Frame(frameMarking);

        assertThat(frame.firstBallScore()).isEqualTo(expectedScore);
    }

    @ParameterizedTest
    @CsvSource({"7-,0", "63,3", "6/,4", "XXX,10"})
    public void can_calculate_second_ball_score(String frameMarking, int expectedScore) {
        Frame frame = new Frame(frameMarking);

        assertThat(frame.secondBallScore()).isEqualTo(expectedScore);
    }

    @ParameterizedTest
    @CsvSource({"X7-,0", "X63,3", "X6/,4", "XXX,10"})
    public void can_calculate_third_ball_score(String frameMarking, int expectedScore) {
        Frame frame = new Frame(frameMarking);

        assertThat(frame.thirdBallScore()).isEqualTo(expectedScore);
    }


    // cannot gutter_strike
    // cannot number_strike
    // cannot split_anything
    // cannot gutter_gutter_anything
    // cannot gutter_number_anything
    // cannot number_gutter_anything
    // cannot number_number_anything
}
