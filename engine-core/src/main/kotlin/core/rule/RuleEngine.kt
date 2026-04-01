package core.rule

import domain.action.GameAction
import domain.model.Game
import domain.rule.GameRule

interface RuleEngine {
    fun addRule(rule: GameRule)
    fun validate(game: Game, action: GameAction): Boolean
}
