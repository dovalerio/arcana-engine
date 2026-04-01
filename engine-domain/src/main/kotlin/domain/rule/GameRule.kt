package domain.rule

import domain.action.GameAction
import domain.model.Game

fun interface GameRule {
    fun isSatisfiedBy(game: Game, action: GameAction): Boolean
}
