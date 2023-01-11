package net.rulesets;

import net.rulesets.rules.players.PlayersCanOnlyMoveTheirPieces;
import net.rulesets.rules.pieces.pawn.PawnDirectionRule;
import net.rulesets.rules.pieces.pawn.StandardPawnMove;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StandardRulesTest {

    @Test
    void containsTheFollowingRules() {
        assertThat(StandardRules.ruleSet)
                .containsExactly(
                        PlayersCanOnlyMoveTheirPieces.get()
                );
    }
}