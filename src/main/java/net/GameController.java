package net;

import io.vavr.collection.List;
import net.players.Player;

public class GameController {

    private final List<GameState> history;

    private GameController(final List<GameState> history) {
        this.history = history;
    }

    public static GameController standard() {
        return new GameController(List.of(
                GameState.of(Player.White, Board.standard())
        ));
    }

    public GameState currentGameState() {
        return history.last();
    }


}
