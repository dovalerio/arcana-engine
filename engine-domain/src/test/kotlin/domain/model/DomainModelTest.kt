package domain.model

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DomainModelTest {

    @Test
    fun `Card is created with correct properties`() {
        val card = Card(id = "c-1", name = "Dragon", type = CardType.CREATURE)

        assertEquals("c-1", card.id)
        assertEquals("Dragon", card.name)
        assertEquals(CardType.CREATURE, card.type)
    }

    @Test
    fun `Player defaults to 20 life points and empty hand`() {
        val player = Player(id = "p-1", name = "Alice")

        assertEquals(20, player.lifePoints)
        assertTrue(player.hand.isEmpty())
    }

    @Test
    fun `Player holds cards in hand`() {
        val card = Card(id = "c-1", name = "Spell", type = CardType.INSTANT)
        val player = Player(id = "p-1", name = "Alice", hand = listOf(card))

        assertEquals(1, player.hand.size)
        assertEquals(card, player.hand.first())
    }

    @Test
    fun `Deck contains cards`() {
        val cards = listOf(
            Card(id = "c-1", name = "Forest", type = CardType.LAND),
            Card(id = "c-2", name = "Bear", type = CardType.CREATURE)
        )
        val deck = Deck(id = "d-1", name = "Green Deck", cards = cards)

        assertEquals(2, deck.cards.size)
    }

    @Test
    fun `Zone starts empty by default`() {
        val zone = Zone(type = ZoneType.GRAVEYARD)

        assertEquals(ZoneType.GRAVEYARD, zone.type)
        assertTrue(zone.cards.isEmpty())
    }

    @Test
    fun `Turn holds active player and phase`() {
        val turn = Turn(number = 1, activePlayerId = "p-1", phase = Phase.MAIN)

        assertEquals(1, turn.number)
        assertEquals("p-1", turn.activePlayerId)
        assertEquals(Phase.MAIN, turn.phase)
    }

    @Test
    fun `Game is created with IN_PROGRESS status`() {
        val player1 = Player(id = "p-1", name = "Alice")
        val player2 = Player(id = "p-2", name = "Bob")
        val turn = Turn(number = 1, activePlayerId = "p-1", phase = Phase.BEGINNING)
        val game = Game(
            id = "g-1",
            players = listOf(player1, player2),
            currentTurn = turn,
            status = GameStatus.IN_PROGRESS
        )

        assertEquals("g-1", game.id)
        assertEquals(2, game.players.size)
        assertEquals(GameStatus.IN_PROGRESS, game.status)
    }
}
