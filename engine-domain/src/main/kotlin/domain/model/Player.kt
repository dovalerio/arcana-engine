package domain.model

data class Player(
    val id: String,
    val name: String,
    val lifePoints: Int = 20,
    val hand: List<Card> = emptyList()
)
