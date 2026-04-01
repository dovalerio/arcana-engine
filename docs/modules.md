# Módulos do arcana-engine

## engine-domain

**Responsabilidade:** Modelos de domínio puros e contratos de extensão.

### O que pode existir
- Data classes representando conceitos do domínio: `Game`, `Player`, `Card`, `Deck`, `Zone`, `Phase`, `Turn`
- Interfaces de contrato: `GameRule`, `GameAction`, `GameEvent`, `Effect`
- Enums de domínio: `CardType`, `ZoneType`, `Phase`, `GameStatus`

### O que NÃO pode existir
- Dependências externas ou de outros módulos do projeto
- Lógica de negócio ou implementações de regras
- Anotações de frameworks, serialização ou persistência
- Qualquer referência a infraestrutura

---

## engine-core

**Responsabilidade:** Orquestração e execução do motor.

### O que pode existir
- Interfaces de infraestrutura do motor: `RuleEngine`, `ActionProcessor`, `EventDispatcher`
- Implementações padrão: `DefaultRuleEngine`, `DefaultActionProcessor`, `DefaultEventDispatcher`
- Orquestrador central: `GameEngine`
- Tipos auxiliares: `ActionHandler`, `EventListener`

### O que NÃO pode existir
- Regras de jogo reais
- Referências a `engine-api` ou `engine-testkit`
- Frameworks ou bibliotecas externas além das dependências de teste

---

## engine-api

**Responsabilidade:** Interface pública do SDK.

### O que pode existir
- Fachada de entrada: `ArcanaEngine`
- Builder de configuração: `ArcanaEngineBuilder`

### O que NÃO pode existir
- Exposição de tipos internos de `engine-core`
- Lógica de negócio
- Referência a `engine-testkit`

---

## engine-testkit

**Responsabilidade:** Utilitários de suporte a testes.

### O que pode existir
- Builders de domínio: `GameBuilder`, `PlayerBuilder`, `CardBuilder`
- Fixtures: `DomainFixtures`
- Fakes: `FakeEventDispatcher`, `FakeRuleEngine`

### O que NÃO pode existir
- Código de produção ou lógica de negócio
- Referências a `engine-api`
- Dependências de frameworks pesados
