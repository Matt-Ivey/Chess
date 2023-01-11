package net;


import net.players.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameControllerIT {

    @Test
    public void standardGameHasStandardBoard() {
        final GameController standard = GameController.standard();
        assertThat(standard.currentGameState().board()).isEqualTo(Board.standard());
    }

    @Test
    public void standardGameHasWhiteNextToPlay() {
        final GameController standard = GameController.standard();
        assertThat(standard.currentGameState().nextToPlay()).isEqualTo(Player.White);
    }
}