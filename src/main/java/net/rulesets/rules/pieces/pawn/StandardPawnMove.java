package net.rulesets.rules.pieces.pawn;

import net.GameState;
import net.pieces.BlackPiece;
import net.pieces.Piece;
import net.pieces.WhitePiece;
import net.rulesets.rules.Rule;
import net.utils.result.Err;
import net.utils.result.Okay;
import net.utils.result.Result;

import java.util.function.Function;
import java.util.function.Predicate;

public class StandardPawnMove implements Rule {

    private static final StandardPawnMove me = new StandardPawnMove();

    public static StandardPawnMove get() {
        return me;
    }

    @Override
    public To moving(final int originColumn, final int originRow) {
        return (destinationColumn, destinationRow) -> gameState ->
                gameState.board().pieceAtPosition(originColumn, originRow)
                        .filter(isPawn())
                        .filter(isMovingMoreThanOneRow(originRow, destinationRow))
                        .filter(isNotOnOriginalRowAndMovingTwo(originRow, destinationRow))
                        .map(toErrorMessage(originColumn, originRow, destinationColumn, destinationRow))
                        .getOrElse(Okay.of(gameState));

    }

    private static Function<Piece, Result<GameState>> toErrorMessage(final int originColumn, final int originRow, final int destinationColumn, final int destinationRow) {
        return piece -> Err.of(piece.getPlayer() + " "
                + piece + " "
                + "cannot move from "
                + originColumn + ", " + originRow + " "
                + "to " + destinationColumn + ", " + destinationRow);
    }

    private static Predicate<Piece> isNotOnOriginalRowAndMovingTwo(final int originRow, final int destinationRow) {
        return __ -> !((originRow == 2 || originRow == 7) && Math.abs(originRow - destinationRow) == 2);
    }

    private static Predicate<Piece> isMovingMoreThanOneRow(final int originRow, final int destinationRow) {
        return __ -> Math.abs(originRow - destinationRow) > 1;
    }

    private static Predicate<Piece> isPawn() {
        return piece -> piece == WhitePiece.pawn || piece == BlackPiece.pawn;
    }
}
