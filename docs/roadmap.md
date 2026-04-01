# Roadmap

## Fase Atual: Scaffolding

**Status:** ✅ Concluída

Objetivos desta fase:
- Estrutura modular criada (engine-domain, engine-core, engine-api, engine-testkit)
- Modelos de domínio básicos definidos
- Interfaces de extensão estabelecidas
- Motor de regras, processador de ações e dispatcher de eventos funcionando
- Fachada pública do SDK operacional
- Utilitários de teste disponíveis
- Documentação inicial em pt-BR

## Próxima Fase: Regras Básicas

**Status:** 🔜 Planejada

Objetivos:
- Implementar as primeiras `GameRule` concretas (ex: ação só pode ser executada no turno do jogador)
- Implementar `GameAction` concretas (ex: `PlayCardAction`, `PassTurnAction`)
- Implementar `GameEvent` concretos e seus handlers
- Implementar `Effect` concretos simples
- Expandir cobertura de testes com cenários de jogo básicos

## Fase Futura: Motor Extensível

**Status:** 💡 Conceitual

Objetivos:
- Sistema de fases e turnos completo
- Suporte a zonas de jogo (mão, biblioteca, cemitério, campo de batalha)
- Ciclo de vida de cartas extensível
- Mecanismo de resolução de efeitos em sequência
- DSL para definição de regras

## Fora de Escopo (Permanente)

Os itens abaixo estão **fora do escopo** deste projeto em todas as fases:

- ❌ Implementação completa das regras do Magic: The Gathering
- ❌ Sistema de combate
- ❌ Sistema de mana
- ❌ Resolução de pilha (stack)
- ❌ Interface gráfica (UI)
- ❌ Interface de linha de comando (CLI)
- ❌ Comunicação em rede
- ❌ Multiplayer
- ❌ Inteligência Artificial
- ❌ Persistência de dados
- ❌ Integração com frameworks (Spring, Ktor, etc.)
