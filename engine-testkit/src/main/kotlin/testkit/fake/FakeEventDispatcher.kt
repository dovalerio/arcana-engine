package testkit.fake

import core.event.EventDispatcher
import core.event.EventListener
import domain.event.GameEvent

class FakeEventDispatcher : EventDispatcher {

    val dispatchedEvents: MutableList<GameEvent> = mutableListOf()

    override fun <T : GameEvent> subscribe(type: Class<T>, listener: EventListener<T>) {
        // intentionally empty: fake implementation does not route events
    }

    override fun <T : GameEvent> dispatch(event: T) {
        dispatchedEvents.add(event)
    }
}
