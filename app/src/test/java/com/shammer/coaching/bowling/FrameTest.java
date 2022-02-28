package com.shammer.coaching.bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void can_create_frame_with_2_markings() {
        Frame frame = new Frame("--");

        assertThat(frame.first()).isEqualTo('-');
        assertThat(frame.second()).isEqualTo('-');
        assertThat(frame.third()).isNull();
    }

    // can gutter_number
    // can gutter_split
    // cannot gutter_strike
    // can number_gutter
    // can number_number
    // can number_split
    // cannot number_strike
    // cannot split_anything
    // can strike
    // can strike_gutter_gutter 10th
    // can strike_gutter_number 10th
    // can strike_gutter_spare 10th
    // can strike_number_gutter 10th
    // can strike_number_number 10th
    // can strike_number_spare 10th
    // can strike_strike_gutter 10th
    // can strike_strike_number 10th
    // can strike_strike_strike 10th
    // can gutter_spare_gutter 10th
    // can gutter_spare_number 10th
    // can gutter_spare_strike 10th
    // can number_spare_gutter 10th
    // can number_spare_number 10th
    // can number_spare_strike 10th

}
