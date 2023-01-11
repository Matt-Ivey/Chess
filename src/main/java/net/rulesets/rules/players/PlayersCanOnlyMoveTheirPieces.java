package net.rulesets.rules.players;

import net.GameState;
import net.pieces.Piece;
import net.players.Player;
import net.rulesets.rules.Rule;
import net.utils.result.Err;
import net.utils.result.Okay;
import net.utils.result.Result;


public class PlayersCanOnlyMoveTheirPieces implements Rule {

    private static final PlayersCanOnlyMoveTheirPieces me = new PlayersCanOnlyMoveTheirPieces();
    private PlayersCanOnlyMoveTheirPieces() {}
    public static PlayersCanOnlyMoveTheirPieces get() {
        return me;
    }
    @Override
    public To moving(final int originColumn, final int originRow) {
        return (destinationColumn, destinationColum) -> gameState ->  {
                final Player currentPlayer = gameState.nextToPlay();
                return gameState.board().pieceAtPosition(originColumn, destinationColumn)
                        .map(Piece::getPlayer)
                        .filter(owner -> owner != currentPlayer)
                        .map(owner -> (Result<GameState>) Err.<GameState>of(currentPlayer.name() + " player cannot move " + owner.name() + " pieces"))
                        .getOrElse(() -> Okay.of(gameState));
        };
    }
}
