package api.facade

import api.builder.ArcanaEngineBuilder
import core.action.ActionProcessor
import core.engine.GameEngine
import core.event.EventDispatcher
import core.rule.RuleEngine
import domain.action.GameAction
import domain.model.Game

class ArcanaEngine internal constructor(
    ruleEngine: RuleEngine,
    actionProcessor: ActionProcessor,
    eventDispatcher: EventDispatcher
) {
    private val gameEngine = GameEngine(ruleEngine, actionProcessor, eventDispatcher)

    fun process(game: Game, action: GameAction): Game = gameEngine.process(game, action)

    companion object {
        fun create(): ArcanaEngineBuilder = ArcanaEngineBuilder()
    }
}
