package domain.model

data class Zone(
    val type: ZoneType,
    val cards: List<Card> = emptyList()
)

enum class ZoneType {
    HAND, LIBRARY, GRAVEYARD, BATTLEFIELD, EXILE
}
