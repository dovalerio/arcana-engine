package testkit.builder

import domain.model.Card
import domain.model.Player

class PlayerBuilder {
    private var id: String = "player-1"
    private var name: String = "Test Player"
    private var lifePoints: Int = 20
    private var hand: List<Card> = emptyList()

    fun withId(id: String) = apply { this.id = id }
    fun withName(name: String) = apply { this.name = name }
    fun withLifePoints(points: Int) = apply { this.lifePoints = points }
    fun withHand(cards: List<Card>) = apply { this.hand = cards }

    fun build(): Player = Player(id = id, name = name, lifePoints = lifePoints, hand = hand)
}
