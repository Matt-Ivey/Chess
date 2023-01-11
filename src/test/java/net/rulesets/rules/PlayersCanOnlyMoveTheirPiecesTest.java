package net.rulesets.rules;

import net.Board;
import net.GameState;
import net.pieces.BlackPiece;
import net.pieces.Piece;
import net.pieces.WhitePiece;
import net.players.Player;
import net.rulesets.rules.players.PlayersCanOnlyMoveTheirPieces;
import net.utils.result.Result;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.vavr.control.Option.some;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PlayersCanOnlyMoveTheirPiecesTest {

    @ParameterizedTest
    @EnumSource(BlackPiece.class)
    public void blackPlayersCanMoveBlackPieces(final Piece piece,
                                                  @Mock final Board board,
                                                  @Mock final GameState gameState) {

        given(gameState.nextToPlay()).willReturn(Player.Black);
        given(gameState.board()).willReturn(board);
        given(board.pieceAtPosition(1, 1)).willReturn(some(piece));

        final Result<GameState> actual = PlayersCanOnlyMoveTheirPieces.get()
                .moving(1, 1)
                .to(1, 2)
                .apply(gameState);

        assertThat(actual.isOkay()).isTrue();
        assertThat(actual.value()).isEqualTo(gameState);
    }

    @ParameterizedTest
    @EnumSource(WhitePiece.class)
    public void blackPlayersCannotMoveWhitePieces(final Piece piece,
                                                  @Mock final Board board,
                                                  @Mock final GameState gameState) {

        given(gameState.nextToPlay()).willReturn(Player.Black);
        given(gameState.board()).willReturn(board);
        given(board.pieceAtPosition(1, 1)).willReturn(some(piece));

        final Result<GameState> actual = PlayersCanOnlyMoveTheirPieces.get()
                .moving(1, 1)
                .to(1, 2)
                .apply(gameState);

        assertThat(actual.isOkay()).isFalse();
        assertThat(actual.error()).isEqualTo("Black player cannot move White pieces");
    }

    @ParameterizedTest
    @EnumSource(WhitePiece.class)
    public void whitePlayersCanMoveWhitePieces(final Piece piece,
                                               @Mock final Board board,
                                               @Mock final GameState gameState) {

        given(gameState.nextToPlay()).willReturn(Player.White);
        given(gameState.board()).willReturn(board);
        given(board.pieceAtPosition(1, 1)).willReturn(some(piece));

        final Result<GameState> actual = PlayersCanOnlyMoveTheirPieces.get()
                .moving(1, 1)
                .to(1, 2)
                .apply(gameState);

        assertThat(actual.isOkay()).isTrue();
        assertThat(actual.value()).isEqualTo(gameState);
    }

    @ParameterizedTest
    @EnumSource(BlackPiece.class)
    public void whitePlayersCannotMoveBlackPieces(final Piece piece,
                                                  @Mock final Board board,
                                                  @Mock final GameState gameState) {

        given(gameState.nextToPlay()).willReturn(Player.White);
        given(gameState.board()).willReturn(board);
        given(board.pieceAtPosition(1, 1)).willReturn(some(piece));

        final Result<GameState> actual = PlayersCanOnlyMoveTheirPieces.get()
                .moving(1, 1)
                .to(1, 2)
                .apply(gameState);

        assertThat(actual.isOkay()).isFalse();
        assertThat(actual.error()).isEqualTo("White player cannot move Black pieces");
    }

}