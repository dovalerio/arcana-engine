package testkit.builder

import domain.model.CardType
import domain.model.GameStatus
import domain.model.Phase
import domain.model.Turn
import org.junit.jupiter.api.Test
import testkit.fixture.DomainFixtures
import testkit.fake.FakeEventDispatcher
import testkit.fake.FakeRuleEngine
import domain.event.GameEvent
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class BuildersAndFixturesTest {

    @Test
    fun `CardBuilder creates card with default values`() {
        val card = CardBuilder().build()

        assertEquals("card-1", card.id)
        assertEquals("Test Card", card.name)
        assertEquals(CardType.CREATURE, card.type)
    }

    @Test
    fun `CardBuilder supports customisation`() {
        val card = CardBuilder()
            .withId("c-42")
            .withName("Lightning")
            .withType(CardType.INSTANT)
            .build()

        assertEquals("c-42", card.id)
        assertEquals("Lightning", card.name)
        assertEquals(CardType.INSTANT, card.type)
    }

    @Test
    fun `PlayerBuilder creates player with default 20 life points`() {
        val player = PlayerBuilder().build()

        assertEquals(20, player.lifePoints)
        assertTrue(player.hand.isEmpty())
    }

    @Test
    fun `PlayerBuilder supports customisation`() {
        val card = CardBuilder().withId("c-1").build()
        val player = PlayerBuilder()
            .withId("p-99")
            .withName("Bob")
            .withLifePoints(15)
            .withHand(listOf(card))
            .build()

        assertEquals("p-99", player.id)
        assertEquals(15, player.lifePoints)
        assertEquals(1, player.hand.size)
    }

    @Test
    fun `GameBuilder creates game with two default players`() {
        val game = GameBuilder().build()

        assertNotNull(game)
        assertEquals(2, game.players.size)
        assertEquals(GameStatus.IN_PROGRESS, game.status)
    }

    @Test
    fun `GameBuilder supports turn customisation`() {
        val turn = Turn(number = 3, activePlayerId = "p-1", phase = Phase.COMBAT)
        val game = GameBuilder().withTurn(turn).build()

        assertEquals(3, game.currentTurn.number)
        assertEquals(Phase.COMBAT, game.currentTurn.phase)
    }

    @Test
    fun `DomainFixtures provides ready-made domain objects`() {
        val card = DomainFixtures.aCard()
        val player = DomainFixtures.aPlayer()
        val game = DomainFixtures.aGame()

        assertNotNull(card)
        assertNotNull(player)
        assertNotNull(game)
    }

    @Test
    fun `FakeEventDispatcher records dispatched events`() {
        val dispatcher = FakeEventDispatcher()
        val event = object : GameEvent { override val type = "TEST" }

        dispatcher.dispatch(event)

        assertEquals(1, dispatcher.dispatchedEvents.size)
        assertEquals(event, dispatcher.dispatchedEvents.first())
    }

    @Test
    fun `FakeRuleEngine allows controlling validation result`() {
        val fakeEngine = FakeRuleEngine(shouldValidate = false)
        val game = DomainFixtures.aGame()
        val action = object : domain.action.GameAction { override val playerId = "p-1" }

        assertEquals(false, fakeEngine.validate(game, action))

        fakeEngine.shouldValidate = true
        assertEquals(true, fakeEngine.validate(game, action))
    }
}
