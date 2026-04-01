package core.action

import domain.action.GameAction
import domain.model.Game

class DefaultActionProcessor : ActionProcessor {

    private val handlers: MutableMap<Class<*>, ActionHandler<*>> = mutableMapOf()

    override fun <T : GameAction> register(type: Class<T>, handler: ActionHandler<T>) {
        handlers[type] = handler
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : GameAction> process(game: Game, action: T): Game {
        val handler = handlers[action::class.java] as? ActionHandler<T>
            ?: throw IllegalStateException("No handler registered for ${action::class.java.simpleName}")
        return handler.handle(game, action)
    }
}
