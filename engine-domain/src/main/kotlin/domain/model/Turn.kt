package domain.model

data class Turn(
    val number: Int,
    val activePlayerId: String,
    val phase: Phase
)
