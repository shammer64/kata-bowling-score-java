package com.shammer.coaching.bowling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
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

    @ParameterizedTest
    @CsvSource({"-- -- -- -- -- -- -- -- -- --",
            "-9 18 27 36 45 54 63 72 81 9-",
            "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5",
            "X X X X X X X X X XXX"})
    public void can_create_game_with_10_frames(String frameMarkings) {
        Game game = new Game(frameMarkings);

        assertThat(game.getFrames().size()).isEqualTo(10);
    }

    @Test
    public void can_score_game_with_all_gutters() {
        Game game = new Game("-- -- -- -- -- -- -- -- -- --");

        assertThat(game.getScore()).isEqualTo(0);
    }

    @Test
    public void can_score_game_with_no_spares_or_strikes() {
        Game game = new Game("-9 18 27 36 45 54 63 72 81 9-");

        assertThat(game.getScore()).isEqualTo(90);
    }

    @Test
    public void can_score_game_with_all_spares() {
        Game game = new Game("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5");

        assertThat(game.getScore()).isEqualTo(150);
    }
}
