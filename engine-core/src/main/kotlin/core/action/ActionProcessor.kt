package core.action

import domain.action.GameAction
import domain.model.Game

fun interface ActionHandler<T : GameAction> {
    fun handle(game: Game, action: T): Game
}

interface ActionProcessor {
    fun <T : GameAction> register(type: Class<T>, handler: ActionHandler<T>)
    fun <T : GameAction> process(game: Game, action: T): Game
}
