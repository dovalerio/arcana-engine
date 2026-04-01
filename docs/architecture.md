# Arquitetura do arcana-engine

## Clean Architecture Aplicada

O projeto segue os princípios da Clean Architecture, organizando o código em camadas com dependências unidirecionais que apontam sempre para o centro (domínio).

```
[ engine-api ]
      |
[ engine-core ]
      |
[ engine-domain ]
```

O `engine-testkit` é auxiliar e depende apenas de `engine-domain` e `engine-core`.

### Regras de Dependência

| Módulo          | Pode depender de          | Não pode depender de         |
|-----------------|---------------------------|------------------------------|
| engine-domain   | Nada                      | Qualquer outro módulo        |
| engine-core     | engine-domain             | engine-api, engine-testkit   |
| engine-api      | engine-core, engine-domain| engine-testkit               |
| engine-testkit  | engine-domain, engine-core| engine-api                   |

Dependências reversas são **proibidas**. Nenhuma camada interna conhece as camadas externas.

## Isolamento do Domínio

O módulo `engine-domain` é **completamente puro**:

- Sem frameworks
- Sem bibliotecas externas
- Sem anotações de persistência ou serialização
- Apenas Kotlin puro e tipos da linguagem

Isso garante que o domínio possa ser testado de forma isolada, sem necessidade de infraestrutura.

## Uso dos Princípios SOLID

### Single Responsibility Principle (SRP)
Cada classe tem uma única responsabilidade:
- `DefaultRuleEngine`: apenas valida regras
- `DefaultActionProcessor`: apenas despacha ações para handlers
- `DefaultEventDispatcher`: apenas roteia eventos para listeners

### Open/Closed Principle (OCP)
O sistema é aberto para extensão via interfaces:
- Novas regras: implementar `GameRule`
- Novas ações: implementar `GameAction` e registrar um `ActionHandler`
- Novos eventos: implementar `GameEvent` e registrar um `EventListener`

### Liskov Substitution Principle (LSP)
Todas as implementações concretas substituem suas interfaces sem alterar comportamento esperado.

### Interface Segregation Principle (ISP)
Interfaces pequenas e focadas:
- `GameRule`: um único método `isSatisfiedBy`
- `Effect`: um único método `apply`
- `GameAction`: apenas o contrato mínimo necessário

### Dependency Inversion Principle (DIP)
As classes de alto nível (`GameEngine`) dependem de abstrações (`RuleEngine`, `ActionProcessor`, `EventDispatcher`), não de implementações concretas.

## Composição sobre Herança

O projeto usa composição em vez de herança:
- `GameEngine` recebe suas dependências via construtor (injeção de dependência manual)
- `ArcanaEngineBuilder` constrói o grafo de objetos sem hierarquias de classes
