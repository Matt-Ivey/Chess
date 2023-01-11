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

public class PawnDirectionRule implements Rule {

    private static final PawnDirectionRule me = new PawnDirectionRule();

    public static PawnDirectionRule get() {
        return me;
    }

    @Override
    public To moving(final int originColumn, final int originRow) {
        return (destinationColumn, destinationRow) -> gameState ->
            gameState.board().pieceAtPosition(originColumn, originRow)
                    .filter(isPawn())
                    .filter(isNotBlackAndMovingDown(originRow, destinationRow))
                    .filter(isNotWhiteAndMovingUp(originRow, destinationRow))
                    .map(toErrorMessage(originColumn, originRow, destinationColumn, destinationRow))
                    .getOrElse(Okay.of(gameState));
    }

    private static Function<Piece, Result<GameState>> toErrorMessage(final int originColumn, final int originRow, final int destinationColumn, final int destinationRow) {
        return piece -> Err.of(
                piece.getPlayer().name() + " "
                + piece + " "
                + "cannot move from "
                + originColumn + ", " + originRow + " "
                + "to " + destinationColumn + ", " + destinationRow);
    }

    private static Predicate<Piece> isNotWhiteAndMovingUp(final int originRow, final int destinationRow) {
        return piece -> !(piece == WhitePiece.pawn && originRow < destinationRow);
    }

    private static Predicate<Piece> isNotBlackAndMovingDown(final int originRow, final int destinationRow) {
        return piece -> !(piece == BlackPiece.pawn && originRow > destinationRow);
    }

    private static Predicate<Piece> isPawn() {
        return piece -> piece == WhitePiece.pawn || piece == BlackPiece.pawn;
    }
}
