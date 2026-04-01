package core.rule

import domain.action.GameAction
import domain.model.Card
import domain.model.CardType
import domain.model.Game
import domain.model.GameStatus
import domain.model.Phase
import domain.model.Player
import domain.model.Turn
import domain.rule.GameRule
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultRuleEngineTest {

    private val game = Game(
        id = "g-1",
        players = listOf(Player(id = "p-1", name = "Alice")),
        currentTurn = Turn(number = 1, activePlayerId = "p-1", phase = Phase.MAIN),
        status = GameStatus.IN_PROGRESS
    )

    private val action = object : GameAction {
        override val playerId = "p-1"
    }

    @Test
    fun `validates successfully when no rules are registered`() {
        val engine = DefaultRuleEngine()

        assertTrue(engine.validate(game, action))
    }

    @Test
    fun `validates successfully when all rules are satisfied`() {
        val engine = DefaultRuleEngine()
        engine.addRule(GameRule { _, _ -> true })
        engine.addRule(GameRule { _, _ -> true })

        assertTrue(engine.validate(game, action))
    }

    @Test
    fun `fails validation when any rule is not satisfied`() {
        val engine = DefaultRuleEngine()
        engine.addRule(GameRule { _, _ -> true })
        engine.addRule(GameRule { _, _ -> false })

        assertFalse(engine.validate(game, action))
    }

    @Test
    fun `added rule receives game and action`() {
        val engine = DefaultRuleEngine()
        var receivedGame: Game? = null
        var receivedAction: GameAction? = null

        engine.addRule(GameRule { g, a ->
            receivedGame = g
            receivedAction = a
            true
        })
        engine.validate(game, action)

        assertTrue(receivedGame === game)
        assertTrue(receivedAction === action)
    }
}
