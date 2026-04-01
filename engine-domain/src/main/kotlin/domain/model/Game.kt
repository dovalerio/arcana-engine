package domain.model

data class Game(
    val id: String,
    val players: List<Player>,
    val currentTurn: Turn,
    val status: GameStatus
)

enum class GameStatus {
    WAITING, IN_PROGRESS, FINISHED
}
