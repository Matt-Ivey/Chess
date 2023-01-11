package net.rulesets;

import io.vavr.collection.List;
import net.rulesets.rules.players.PlayersCanOnlyMoveTheirPieces;
import net.rulesets.rules.Rule;

public class StandardRules extends RuleSet {

    public static final List<Rule> ruleSet = List.of(
            PlayersCanOnlyMoveTheirPieces.get()
    );

    private StandardRules() {
        super(ruleSet);
    }

    public static StandardRules get() {
        return new StandardRules();
    }
}
