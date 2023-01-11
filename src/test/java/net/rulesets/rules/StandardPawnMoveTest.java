package net.rulesets.rules;

import net.Board;
import net.GameState;
import net.pieces.BlackPiece;
import net.pieces.WhitePiece;
import net.rulesets.rules.pieces.pawn.StandardPawnMove;
import net.utils.result.Err;
import net.utils.result.Okay;
import net.utils.result.Result;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.vavr.control.Option.some;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StandardPawnMoveTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 6, 7, 8})
    void pawnsCanMoveOneSpace(final int column,
                              @Mock final GameState gameState,
                              @Mock final Board board) {
        given(gameState.board()).willReturn(board);
        given(board.pieceAtPosition(column, 6)).willReturn(some(BlackPiece.pawn));

        final Result<GameState> actual = StandardPawnMove.get()
                .moving(column, 6)
                .to(column, 5)
                .apply(gameState);
        final Result<GameState> expected = Okay.of(gameState);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 6, 7, 8})
    void pawnsOnTheSecondToLastRankCanMoveTwoSpaces(final int column,
                                                    @Mock final GameState gameState,
                                                    @Mock final Board board) {
        given(gameState.board()).willReturn(board);
        given(board.pieceAtPosition(column, 7)).willReturn(some(BlackPiece.pawn));

        final Result<GameState> actual = StandardPawnMove.get()
                .moving(column, 7)
                .to(column, 5)
                .apply(gameState);

        final Result<GameState> expected = Okay.of(gameState);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 6, 7, 8})
    void pawnsOnTheSecondRankCanMoveTwoSpaces(final int column,
                                              @Mock final GameState gameState,
                                              @Mock final Board board) {
        given(gameState.board()).willReturn(board);
        given(board.pieceAtPosition(column, 2)).willReturn(some(WhitePiece.pawn));

        final Result<GameState> actual = StandardPawnMove.get()
                .moving(column, 2)
                .to(column, 4)
                .apply(gameState);

        final Result<GameState> expected = Okay.of(gameState);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 6, 7, 8})
    void otherwisePawnsCannotMoveMoreThanOneSpace(final int column,
                                                  @Mock final GameState gameState,
                                                  @Mock final Board board) {
        given(gameState.board()).willReturn(board);
        given(board.pieceAtPosition(column, 3)).willReturn(some(WhitePiece.pawn));

        final Result<GameState> actual = StandardPawnMove.get()
                .moving(column, 3)
                .to(column, 5)
                .apply(gameState);

        final Result<GameState> expected = Err.of("White pawn cannot move from " + column + ", 3 to " + column + ", 5");

        assertThat(actual).isEqualTo(expected);
    }
}