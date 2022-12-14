package net;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableTable;
import net.pieces.Piece;
import net.pieces.BlackPiece;
import net.pieces.WhitePiece;

public class Board {
    private final ImmutableTable<Integer, Integer, Piece> representation;

    public Board(final ImmutableTable<Integer, Integer, Piece> representation) {
        this.representation = representation;
    }

    public Piece pieceAtPosition(final int col, final int row) {
        return representation.get(col, row);
    }

    public static Board standard() {
        return builder()
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
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private final ImmutableTable.Builder<Integer, Integer, Piece> table;

        private Builder() {
            this.table = ImmutableTable.builder();
        }

        public WithPosition withPiece(final Piece piece) {
            return (col, row) -> {
                table.put(col, row, piece);
                return this;
            };
        }

        public Board build() {
            return new Board(table.build());
        }

        public interface WithPosition {
            Builder atPosition(final Integer col, final Integer row);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Board board = (Board) o;
        return Objects.equal(representation, board.representation);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(representation);
    }
}
