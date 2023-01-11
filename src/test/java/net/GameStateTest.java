package net;

import net.players.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@ExtendWith(MockitoExtension.class)
class GameStateTest {

    @Test
    public void playerMustNotBeNull(@Mock final Board board) {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> GameState.of(null, board))
                .withMessage("nextToPlay must not be null");
    }

    @Test
    public void boardMustNotBeNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> GameState.of(Player.White, null))
                .withMessage("board must not be null");
    }

    @ParameterizedTest
    @EnumSource(Player.class)
    public void nextToPlayIsMaintained(final Player nextToPlay, @Mock final Board board) {
        final GameState actual = GameState.of(nextToPlay, board);
        assertThat(actual.nextToPlay()).isEqualTo(nextToPlay);
    }

    @Test
    public void nextToPlayIsMaintained(@Mock final Board board) {
        final GameState actual = GameState.of(Player.White, board);
        assertThat(actual.board()).isEqualTo(board);
    }
}