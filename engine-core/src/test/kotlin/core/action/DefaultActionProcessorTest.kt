package core.action

import domain.action.GameAction
import domain.model.Game
import domain.model.GameStatus
import domain.model.Phase
import domain.model.Player
import domain.model.Turn
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertSame

class DefaultActionProcessorTest {

    private data class PassAction(override val playerId: String = "p-1") : GameAction

    private val game = Game(
        id = "g-1",
        players = listOf(Player(id = "p-1", name = "Alice")),
        currentTurn = Turn(number = 1, activePlayerId = "p-1", phase = Phase.MAIN),
        status = GameStatus.IN_PROGRESS
    )

    @Test
    fun `processes action using registered handler`() {
        val processor = DefaultActionProcessor()
        val updatedGame = game.copy(id = "g-2")
        processor.register(PassAction::class.java) { _, _ -> updatedGame }

        val result = processor.process(game, PassAction())

        assertSame(updatedGame, result)
    }

    @Test
    fun `throws when no handler is registered for action`() {
        val processor = DefaultActionProcessor()

        assertThrows<IllegalStateException> {
            processor.process(game, PassAction())
        }
    }
}
