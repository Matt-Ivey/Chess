package net.rulesets;

import io.vavr.collection.List;
import net.GameState;
import net.rulesets.rules.Rule;
import net.utils.result.Okay;
import net.utils.result.Result;

import java.util.function.BiFunction;

class RuleSet {

    private final List<Rule> ruleSet;

    public RuleSet(final List<Rule> ruleSet) {
        this.ruleSet = ruleSet;
    }

    public MoveFrom using(final GameState gameState) {
        return (originColumn, originRow) -> (destinationColumn, destinationRow) ->
                ruleSet.foldLeft(Okay.of(gameState),
                        evaluateMove(originColumn, originRow)
                                .to(destinationColumn, destinationRow));
    }

    private To evaluateMove(final int originColum, final int originRow) {
        return (destinationColumn, destinationRow) -> (gameStateResult, rule) ->
                gameStateResult.flatMap(
                        rule.moving(originColum, originRow)
                                .to(destinationColumn, destinationRow));
    }

    private interface To {
        BiFunction<Result<GameState>, Rule, Result<GameState>> to(final int destinationColumn, final int destinationRow);
    }

    @FunctionalInterface
    interface MoveFrom {
        MoveTo moveFrom(final int originColumn, final int originRow);
        @FunctionalInterface
        interface MoveTo {
            Result<GameState> to(final int destinationColumn, final int destinationRow);
        }
    }
}
