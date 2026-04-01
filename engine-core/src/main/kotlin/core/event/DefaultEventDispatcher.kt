package core.event

import domain.event.GameEvent

class DefaultEventDispatcher : EventDispatcher {

    private val listeners: MutableMap<Class<*>, MutableList<EventListener<*>>> = mutableMapOf()

    override fun <T : GameEvent> subscribe(type: Class<T>, listener: EventListener<T>) {
        listeners.getOrPut(type) { mutableListOf() }.add(listener)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : GameEvent> dispatch(event: T) {
        listeners[event::class.java]?.forEach { listener ->
            (listener as EventListener<T>).onEvent(event)
        }
    }
}
