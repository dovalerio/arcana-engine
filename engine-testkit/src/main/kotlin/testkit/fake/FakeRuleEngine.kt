package testkit.fake

import core.rule.RuleEngine
import domain.action.GameAction
import domain.model.Game
import domain.rule.GameRule

class FakeRuleEngine(var shouldValidate: Boolean = true) : RuleEngine {

    override fun addRule(rule: GameRule) {
        // intentionally empty: fake implementation ignores rules
    }

    override fun validate(game: Game, action: GameAction): Boolean = shouldValidate
}
