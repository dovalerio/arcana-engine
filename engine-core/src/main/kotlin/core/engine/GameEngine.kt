package core.engine

import core.action.ActionProcessor
import core.event.EventDispatcher
import core.rule.RuleEngine
import domain.action.GameAction
import domain.model.Game

class GameEngine(
    private val ruleEngine: RuleEngine,
    private val actionProcessor: ActionProcessor,
    private val eventDispatcher: EventDispatcher
) {
    fun process(game: Game, action: GameAction): Game {
        require(ruleEngine.validate(game, action)) {
            "Action ${action::class.java.simpleName} violates game rules"
        }
        return actionProcessor.process(game, action)
    }
}
