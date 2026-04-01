package api.facade

import domain.action.GameAction
import domain.model.Game
import domain.model.GameStatus
import domain.model.Phase
import domain.model.Player
import domain.model.Turn
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNotNull
import kotlin.test.assertEquals

class ArcanaEngineTest {

    private data class PassAction(override val playerId: String = "p-1") : GameAction

    private val game = Game(
        id = "g-1",
        players = listOf(Player(id = "p-1", name = "Alice")),
        currentTurn = Turn(number = 1, activePlayerId = "p-1", phase = Phase.MAIN),
        status = GameStatus.IN_PROGRESS
    )

    @Test
    fun `ArcanaEngine can be created via factory method`() {
        val engine = ArcanaEngine.create().build()

        assertNotNull(engine)
    }

    @Test
    fun `builder produces engine that accepts rules`() {
        val engine = ArcanaEngine.create()
            .withRule { _, _ -> true }
            .build()

        assertNotNull(engine)
    }

    @Test
    fun `engine rejects action when rule is not satisfied`() {
        val engine = ArcanaEngine.create()
            .withRule { _, _ -> false }
            .build()

        assertThrows<IllegalArgumentException> {
            engine.process(game, PassAction())
        }
    }

    @Test
    fun `builder chaining works fluently`() {
        val engine = ArcanaEngine.create()
            .withRule { _, _ -> true }
            .withRule { _, _ -> true }
            .build()

        assertNotNull(engine)
    }
}
