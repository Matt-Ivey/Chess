package net;

import io.vavr.collection.List;
import net.players.Player;

public class Game {

    private final List<GameState> history;

    private Game(final List<GameState> history) {
        this.history = history;
    }

    public static Game standard() {
        return new Game(List.of(
                GameState.of(Player.white, Board.standard())
        ));
    }

    public GameState currentGameState() {
        return history.last();
    }
}
