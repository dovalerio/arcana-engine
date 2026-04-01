package core.event

import domain.event.GameEvent

fun interface EventListener<T : GameEvent> {
    fun onEvent(event: T)
}

interface EventDispatcher {
    fun <T : GameEvent> subscribe(type: Class<T>, listener: EventListener<T>)
    fun <T : GameEvent> dispatch(event: T)
}
