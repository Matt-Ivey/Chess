package net.pieces;

import net.players.Player;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {

    @ParameterizedTest
    @EnumSource(WhitePiece.class)
    public void whitePiecesAreOwnedByWhitePlayer(final Piece piece) {
        assertThat(piece.getPlayer()).isEqualTo(Player.white);
    }

    @ParameterizedTest
    @EnumSource(BlackPiece.class)
    public void blackPiecesAreOwnedByBlackPlayer(final Piece piece) {
        assertThat(piece.getPlayer()).isEqualTo(Player.black);
    }
}
