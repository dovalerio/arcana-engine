package testkit.builder

import domain.model.Card
import domain.model.CardType

class CardBuilder {
    private var id: String = "card-1"
    private var name: String = "Test Card"
    private var type: CardType = CardType.CREATURE

    fun withId(id: String) = apply { this.id = id }
    fun withName(name: String) = apply { this.name = name }
    fun withType(type: CardType) = apply { this.type = type }

    fun build(): Card = Card(id = id, name = name, type = type)
}
