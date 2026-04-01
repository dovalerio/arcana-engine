package domain.model

data class Card(
    val id: String,
    val name: String,
    val type: CardType
)

enum class CardType {
    CREATURE, INSTANT, SORCERY, ENCHANTMENT, ARTIFACT, LAND
}
