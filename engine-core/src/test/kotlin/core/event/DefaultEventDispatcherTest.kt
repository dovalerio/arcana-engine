package core.event

import domain.event.GameEvent
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DefaultEventDispatcherTest {

    private data class TestEvent(override val type: String = "TEST") : GameEvent

    @Test
    fun `dispatches event to subscribed listener`() {
        val dispatcher = DefaultEventDispatcher()
        val received = mutableListOf<GameEvent>()

        dispatcher.subscribe(TestEvent::class.java) { event -> received.add(event) }
        val event = TestEvent()
        dispatcher.dispatch(event)

        assertEquals(1, received.size)
        assertEquals(event, received.first())
    }

    @Test
    fun `dispatches event to all listeners of the same type`() {
        val dispatcher = DefaultEventDispatcher()
        var count = 0

        dispatcher.subscribe(TestEvent::class.java) { count++ }
        dispatcher.subscribe(TestEvent::class.java) { count++ }
        dispatcher.dispatch(TestEvent())

        assertEquals(2, count)
    }

    @Test
    fun `does not dispatch to listeners of a different event type`() {
        val dispatcher = DefaultEventDispatcher()
        var called = false

        dispatcher.subscribe(TestEvent::class.java) { called = true }
        dispatcher.dispatch(object : GameEvent { override val type = "OTHER" })

        assertTrue(!called)
    }
}
