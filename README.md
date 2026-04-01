# arcana-engine

SDK modular para um motor de regras de jogo de cartas inspirado em Magic: The Gathering.

## Propósito

O `arcana-engine` é um SDK em Kotlin projetado para ser a base de um motor extensível de regras de jogos de cartas. O projeto segue os princípios de Clean Architecture e SOLID, garantindo que o domínio seja puro, testável e livre de dependências externas.

## Escopo Atual

Esta é a **fase de scaffolding** do projeto. O escopo atual inclui:

- Estrutura modular completa com 4 módulos
- Modelos de domínio básicos (Game, Player, Card, Deck, Zone, Phase, Turn)
- Interfaces de extensão (GameRule, GameAction, GameEvent, Effect)
- Motor de regras, processador de ações e dispatcher de eventos
- Fachada pública da API
- Utilitários de teste (builders, fixtures, fakes)

## Estrutura dos Módulos

```
arcana-engine/
├── engine-domain/     # Modelos e contratos puros de domínio
├── engine-core/       # Orquestração e execução do motor
├── engine-api/        # Interface pública do SDK
└── engine-testkit/    # Utilitários de teste
```

Consulte [docs/modules.md](docs/modules.md) para detalhes de cada módulo.

## Como Buildar

Requisitos: JDK 21, Gradle (ou use o wrapper incluído).

```bash
./gradlew build
```

## Como Rodar os Testes

```bash
./gradlew test
```

Para rodar os testes de um módulo específico:

```bash
./gradlew :engine-domain:test
./gradlew :engine-core:test
./gradlew :engine-api:test
./gradlew :engine-testkit:test
```

## O que NÃO está implementado

- Regras reais do jogo (combate, sistema de mana, pilha de resolução)
- Sistema de combate
- Sistema de mana
- Resolução de pilha (stack)
- Interface gráfica ou CLI
- Comunicação em rede ou multiplayer
- Persistência de dados
- Integração com qualquer framework

Consulte [docs/roadmap.md](docs/roadmap.md) para os próximos passos planejados.

## Documentação Adicional

- [Arquitetura](docs/architecture.md)
- [Módulos](docs/modules.md)
- [Estratégia de Testes](docs/testing-strategy.md)
- [Roadmap](docs/roadmap.md)
