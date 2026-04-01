package api.builder

import api.facade.ArcanaEngine
import core.action.DefaultActionProcessor
import core.event.DefaultEventDispatcher
import core.rule.DefaultRuleEngine
import domain.rule.GameRule

class ArcanaEngineBuilder {

    private val rules: MutableList<GameRule> = mutableListOf()

    fun withRule(rule: GameRule): ArcanaEngineBuilder = apply { rules.add(rule) }

    fun build(): ArcanaEngine {
        val ruleEngine = DefaultRuleEngine().also { engine ->
            rules.forEach(engine::addRule)
        }
        return ArcanaEngine(ruleEngine, DefaultActionProcessor(), DefaultEventDispatcher())
    }
}
