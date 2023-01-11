package net.rulesets;

import io.vavr.collection.List;
import net.GameState;
import net.rulesets.rules.Rule;
import net.utils.result.Okay;
import net.utils.result.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RuleSetTest {

    @Test
    void rulesAreApplied(@Mock final Rule rule1,
                         @Mock final Rule.To to1,
                         @Mock final Function<GameState, Result<GameState>> fun1,
                         @Mock final Rule rule3,
                         @Mock final GameState gameState,
                         @Mock final Rule rule2,
                         @Mock final Rule.To to2,
                         @Mock final Function<GameState, Result<GameState>> fun2,
                         @Mock final Rule.To to3,
                         @Mock final Function<GameState, Result<GameState>> fun3) {

        given(rule1.moving(1, 1)).willReturn(to1);
        given(to1.to(1, 2)).willReturn(fun1);
        given(fun1.apply(gameState)).willReturn(Okay.of(gameState));
        given(rule2.moving(1, 1)).willReturn(to2);
        given(to2.to(1, 2)).willReturn(fun2);
        given(fun2.apply(gameState)).willReturn(Okay.of(gameState));
        given(rule3.moving(1, 1)).willReturn(to3);
        given(to3.to(1, 2)).willReturn(fun3);
        given(fun3.apply(gameState)).willReturn(Okay.of(gameState));

        final RuleSet ruleSet = new RuleSet(List.of(rule1, rule2, rule3));
        final Result<GameState> actual = ruleSet.using(gameState)
                .moveFrom(1, 1)
                .to(1, 2);

        final Result<GameState> expected = Okay.of(gameState);

        assertThat(actual).isEqualTo(expected);
    }
}