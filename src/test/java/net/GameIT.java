package net;


import net.players.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameIT {

    @Test
    public void standardGameHasStandardBoard() {
        final Game standard = Game.standard();
        assertThat(standard.currentGameState().board()).isEqualTo(Board.standard());
    }

    @Test
    public void standardGameHasWhiteNextToPlay() {
        final Game standard = Game.standard();
        assertThat(standard.currentGameState().nextToPlay()).isEqualTo(Player.white);
    }
}