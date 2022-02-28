package com.shammer.coaching.bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class GameTest {

    @Test
    public void cannot_create_game_with_null_markings() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Game(null);
        });
    }

    @Test
    public void cannot_create_game_with_empty_markings() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Game("");
        });
    }
}
