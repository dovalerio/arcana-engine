package core.engine

import core.action.ActionProcessor
import core.event.DefaultEventDispatcher
import core.rule.DefaultRuleEngine
import domain.action.GameAction
import domain.model.Game
import domain.model.GameStatus
import domain.model.Phase
import domain.model.Player
import domain.model.Turn
import domain.rule.GameRule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertSame

class GameEngineTest {

    private data class PassAction(override val playerId: String = "p-1") : GameAction

    private val game = Game(
        id = "g-1",
        players = listOf(Player(id = "p-1", name = "Alice")),
        currentTurn = Turn(number = 1, activePlayerId = "p-1", phase = Phase.MAIN),
        status = GameStatus.IN_PROGRESS
    )

    @Test
    fun `processes action when all rules are satisfied`() {
        val ruleEngine = DefaultRuleEngine()
        val actionProcessor = object : ActionProcessor {
            override fun <T : GameAction> register(type: Class<T>, handler: core.action.ActionHandler<T>) {}
            override fun <T : GameAction> process(game: Game, action: T): Game = game.copy(id = "processed")
        }
        val engine = GameEngine(ruleEngine, actionProcessor, DefaultEventDispatcher())

        val result = engine.process(game, PassAction())

        assertSame("processed", result.id)
    }

    @Test
    fun `throws when a rule rejects the action`() {
        val ruleEngine = DefaultRuleEngine()
        ruleEngine.addRule(GameRule { _, _ -> false })
        val actionProcessor = object : ActionProcessor {
            override fun <T : GameAction> register(type: Class<T>, handler: core.action.ActionHandler<T>) {}
            override fun <T : GameAction> process(game: Game, action: T): Game = game
        }
        val engine = GameEngine(ruleEngine, actionProcessor, DefaultEventDispatcher())

        assertThrows<IllegalArgumentException> {
            engine.process(game, PassAction())
        }
    }
}
