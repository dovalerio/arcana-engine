package core.rule

import domain.action.GameAction
import domain.model.Game
import domain.rule.GameRule

class DefaultRuleEngine : RuleEngine {

    private val rules: MutableList<GameRule> = mutableListOf()

    override fun addRule(rule: GameRule) {
        rules.add(rule)
    }

    override fun validate(game: Game, action: GameAction): Boolean =
        rules.all { it.isSatisfiedBy(game, action) }
}
