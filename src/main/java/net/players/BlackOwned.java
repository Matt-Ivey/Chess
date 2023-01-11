package net.players;

public interface BlackOwned extends Owned {
    default Player getPlayer() {
        return Player.Black;
    }
}
