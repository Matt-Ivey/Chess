package net.players;

public interface WhiteOwned extends Owned {
    default Player getPlayer() {
        return Player.White;
    }
}
