package domain.effect

import domain.model.Game

interface Effect {
    fun apply(game: Game): Game
}
