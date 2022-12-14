package net;

import com.google.common.base.Objects;
import net.players.Player;

import static java.util.Objects.requireNonNull;

public class GameState {
    private final Player nextToPlay;
    private final Board board;

    private GameState(final Player nextToPlay, final Board board) {
        this.nextToPlay = requireNonNull(nextToPlay, "nextToPlay must not be null");
        this.board = requireNonNull(board, "board must not be null");;
    }

    public static GameState of(final Player nextToPlay, final Board board) {
        return new GameState(nextToPlay, board);
    }

    public Player nextToPlay() {
        return nextToPlay;
    }

    public Board board() {
        return board;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final GameState gameState = (GameState) o;
        return nextToPlay == gameState.nextToPlay && Objects.equal(board, gameState.board);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nextToPlay, board);
    }
}