package net.rulesets.rules.pieces.knight;

import net.rulesets.rules.Rule;

public class StandardKnightMove implements Rule {
    @Override
    public To moving(final int originColumn, final int originRow) {
        return (destinationColumn, destinationColum) -> gameState -> {
            return null;
        };
    }
}
