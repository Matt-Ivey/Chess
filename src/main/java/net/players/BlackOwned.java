package net.pieces;

public interface BlackPlayer {
    default Player getPlayer() {
        return Player.black;
    }
}
