package net.players;

import net.players.Player;

public interface BlackOwned extends Owned {
    default Player getPlayer() {
        return Player.black;
    }
}
