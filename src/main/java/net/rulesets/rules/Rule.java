package net.rulesets.rules;

import net.GameState;
import net.utils.result.Result;

import java.util.function.Function;

public interface Rule {
    To moving(final int originColumn, final int originRow);
    @FunctionalInterface
    interface To {
        Function<GameState, Result<GameState>> to(final int destinationColumn, final int destinationRow);
    }
}
