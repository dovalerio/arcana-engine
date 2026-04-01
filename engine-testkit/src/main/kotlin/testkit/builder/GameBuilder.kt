package testkit.builder

import domain.model.Game
import domain.model.GameStatus
import domain.model.Phase
import domain.model.Player
import domain.model.Turn

class GameBuilder {
    private var id: String = "game-1"
    private var players: List<Player> = listOf(
        PlayerBuilder().build(),
        PlayerBuilder().withId("player-2").withName("Test Player 2").build()
    )
    private var status: GameStatus = GameStatus.IN_PROGRESS
    private var turn: Turn = Turn(number = 1, activePlayerId = "player-1", phase = Phase.MAIN)

    fun withId(id: String) = apply { this.id = id }
    fun withPlayers(players: List<Player>) = apply { this.players = players }
    fun withStatus(status: GameStatus) = apply { this.status = status }
    fun withTurn(turn: Turn) = apply { this.turn = turn }

    fun build(): Game = Game(id = id, players = players, currentTurn = turn, status = status)
}
