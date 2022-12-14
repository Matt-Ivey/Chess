package net.players;

import net.players.Player;

public interface WhiteOwned extends Owned {
    default Player getPlayer() {
        return Player.white;
    }
}
