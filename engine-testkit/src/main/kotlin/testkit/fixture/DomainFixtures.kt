package testkit.fixture

import domain.model.Card
import domain.model.CardType
import domain.model.Game
import domain.model.GameStatus
import domain.model.Player
import testkit.builder.CardBuilder
import testkit.builder.GameBuilder
import testkit.builder.PlayerBuilder

object DomainFixtures {

    fun aCard(
        id: String = "card-1",
        name: String = "Fixture Card",
        type: CardType = CardType.CREATURE
    ): Card = CardBuilder().withId(id).withName(name).withType(type).build()

    fun aPlayer(
        id: String = "player-1",
        name: String = "Fixture Player",
        lifePoints: Int = 20
    ): Player = PlayerBuilder().withId(id).withName(name).withLifePoints(lifePoints).build()

    fun aGame(
        id: String = "game-1",
        status: GameStatus = GameStatus.IN_PROGRESS
    ): Game = GameBuilder().withId(id).withStatus(status).build()
}
