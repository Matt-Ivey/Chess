package net.rulesets.rules;

import net.Board;
import net.GameState;
import net.pieces.BlackPiece;
import net.pieces.Piece;
import net.pieces.WhitePiece;
import net.rulesets.rules.pieces.pawn.PawnDirectionRule;
import net.utils.result.Err;
import net.utils.result.Okay;
import net.utils.result.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.vavr.control.Option.some;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PawnDirectionRuleTest {

    @ParameterizedTest
    @EnumSource(BlackPiece.class)
    void blackNonPawnsAreUnaffected(final Piece piece,
                                    @Mock final GameState gamestate,
                                    @Mock final Board board) {

        given(gamestate.board()).willReturn(board);
        given(board.pieceAtPosition(1, 1)).willReturn(some(piece));

        final Result<GameState> actual = PawnDirectionRule.get()
                .moving(1, 1)
                .to(1, 2)
                .apply(gamestate);

        final Result<GameState> expected = piece != BlackPiece.pawn ? Okay.of(gamestate) : Err.of("Black pawn cannot move from 1, 1 to 1, 2");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void blackPawnsCanMoveDownTheBoard(@Mock final GameState gamestate,
                                       @Mock final Board board) {
        given(gamestate.board()).willReturn(board);
        given(board.pieceAtPosition(1, 2)).willReturn(some(BlackPiece.pawn));

        final Result<GameState> actual = PawnDirectionRule.get()
                .moving(1, 2)
                .to(1, 1)
                .apply(gamestate);

        final Result<GameState> expected = Okay.of(gamestate);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void blackPawnsCannotMoveUpTheBoard(@Mock final GameState gamestate,
                                       @Mock final Board board) {
        given(gamestate.board()).willReturn(board);
        given(board.pieceAtPosition(1, 1)).willReturn(some(BlackPiece.pawn));

        final Result<GameState> actual = PawnDirectionRule.get()
                .moving(1, 1)
                .to(1, 2)
                .apply(gamestate);

        final Result<GameState> expected = Err.of("Black pawn cannot move from 1, 1 to 1, 2");

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @EnumSource(WhitePiece.class)
    void whiteNonPawnsAreUnaffected(final Piece piece,
                                    @Mock final GameState gamestate,
                                    @Mock final Board board) {

        given(gamestate.board()).willReturn(board);
        given(board.pieceAtPosition(1, 2)).willReturn(some(piece));

        final Result<GameState> actual = PawnDirectionRule.get()
                .moving(1, 2)
                .to(1, 1)
                .apply(gamestate);

        final Result<GameState> expected = piece != WhitePiece.pawn ? Okay.of(gamestate) : Err.of("White pawn cannot move from 1, 2 to 1, 1");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whitePawnsCanMoveUpTheBoard(@Mock final GameState gamestate,
                                       @Mock final Board board) {
        given(gamestate.board()).willReturn(board);
        given(board.pieceAtPosition(1, 1)).willReturn(some(WhitePiece.pawn));

        final Result<GameState> actual = PawnDirectionRule.get()
                .moving(1, 1)
                .to(1, 2)
                .apply(gamestate);

        final Result<GameState> expected = Okay.of(gamestate);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whitePawnsCannotMoveDownTheBoard(@Mock final GameState gamestate,
                                        @Mock final Board board) {
        given(gamestate.board()).willReturn(board);
        given(board.pieceAtPosition(1, 2)).willReturn(some(WhitePiece.pawn));

        final Result<GameState> actual = PawnDirectionRule.get()
                .moving(1, 2)
                .to(1, 1)
                .apply(gamestate);

        final Result<GameState> expected = Err.of("White pawn cannot move from 1, 2 to 1, 1");

        assertThat(actual).isEqualTo(expected);
    }
}