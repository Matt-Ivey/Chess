package net;

import io.vavr.control.Option;
import net.pieces.BlackPiece;
import net.pieces.Piece;
import net.pieces.WhitePiece;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.vavr.control.Option.some;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BoardTest {

    final Board standardBoard = Board.builder()
            .withPiece(WhitePiece.rook).atPosition(1, 1)
            .withPiece(WhitePiece.knight).atPosition(2, 1)
            .withPiece(WhitePiece.bishop).atPosition(3, 1)
            .withPiece(WhitePiece.queen).atPosition(4, 1)
            .withPiece(WhitePiece.king).atPosition(5, 1)
            .withPiece(WhitePiece.bishop).atPosition(6, 1)
            .withPiece(WhitePiece.knight).atPosition(7, 1)
            .withPiece(WhitePiece.rook).atPosition(8, 1)
            .withPiece(WhitePiece.pawn).atPosition(1, 2)
            .withPiece(WhitePiece.pawn).atPosition(2, 2)
            .withPiece(WhitePiece.pawn).atPosition(3, 2)
            .withPiece(WhitePiece.pawn).atPosition(4, 2)
            .withPiece(WhitePiece.pawn).atPosition(5, 2)
            .withPiece(WhitePiece.pawn).atPosition(6, 2)
            .withPiece(WhitePiece.pawn).atPosition(7, 2)
            .withPiece(WhitePiece.pawn).atPosition(8, 2)
            .withPiece(BlackPiece.pawn).atPosition(1, 7)
            .withPiece(BlackPiece.pawn).atPosition(2, 7)
            .withPiece(BlackPiece.pawn).atPosition(3, 7)
            .withPiece(BlackPiece.pawn).atPosition(4, 7)
            .withPiece(BlackPiece.pawn).atPosition(5, 7)
            .withPiece(BlackPiece.pawn).atPosition(6, 7)
            .withPiece(BlackPiece.pawn).atPosition(7, 7)
            .withPiece(BlackPiece.pawn).atPosition(8, 7)
            .withPiece(BlackPiece.rook).atPosition(1, 8)
            .withPiece(BlackPiece.knight).atPosition(2, 8)
            .withPiece(BlackPiece.bishop).atPosition(3, 8)
            .withPiece(BlackPiece.queen).atPosition(4, 8)
            .withPiece(BlackPiece.king).atPosition(5, 8)
            .withPiece(BlackPiece.bishop).atPosition(6, 8)
            .withPiece(BlackPiece.knight).atPosition(7, 8)
            .withPiece(BlackPiece.rook).atPosition(8, 8)
            .build();

    @Test
    public void standardBoardHasStandardPositions() {
        final Board actual = Board.standard();
        assertThat(actual).isEqualTo(standardBoard);
    }

    @ParameterizedTest
    @CsvSource({"1, 1", "1, 2", "4, 5"})
    public void piecesAreMaintainedAtAPosition(final int col, final int row, @Mock final Piece piece) {
        final Board board = Board.builder()
                .withPiece(piece).atPosition(col, row)
                .build();

        assertThat(board.pieceAtPosition(col, row)).isEqualTo(some(piece));
    }

    @Test
    public void emptyPlacesReturnEmpty() {
        final Board emptyBoard = Board.builder().build();
        assertThat(emptyBoard.pieceAtPosition(1, 1)).isEqualTo(Option.none());
    }
}
