# Estratégia de Testes

## Abordagem TDD

O projeto adota Test-Driven Development (TDD) como prática central. Os testes são escritos para validar comportamento real, não para satisfazer cobertura artificial.

Princípios:
- Testes expressam comportamento, não implementação
- Nomes de testes descrevem o cenário em linguagem natural
- Não há `assertTrue(true)` ou asserts sem significado

## Tipos de Teste por Módulo

### engine-domain
**Tipo:** Testes unitários puros

Validam a criação e integridade dos modelos de domínio.

Exemplos:
- `Card` é criado com os atributos corretos
- `Player` tem 20 pontos de vida por padrão
- `Game` reflete o estado correto ao ser construído

### engine-core
**Tipo:** Testes unitários com mocks pontuais

Validam o comportamento dos componentes de orquestração:
- `DefaultRuleEngine` valida corretamente quando há regras satisfeitas/não satisfeitas
- `DefaultActionProcessor` despacha para o handler correto e lança exceção para handlers ausentes
- `DefaultEventDispatcher` roteia eventos para os listeners corretos
- `GameEngine` rejeita ações quando uma regra falha

### engine-api
**Tipo:** Testes de integração leve (sem infraestrutura externa)

Validam o contrato público do SDK:
- `ArcanaEngine.create()` retorna um builder funcional
- O builder aceita regras e produz um engine configurado
- O engine rejeita ações quando as regras não são satisfeitas

### engine-testkit
**Tipo:** Testes de usabilidade dos utilitários

Validam que os builders e fixtures são fáceis de usar e produzem objetos corretos:
- Builders com valores padrão razoáveis
- Builders suportam customização fluente
- Fakes se comportam de forma previsível e controlável

## Papel do engine-testkit

O `engine-testkit` fornece infraestrutura de teste reutilizável para projetos que consomem o SDK:

- **Builders:** constroem objetos de domínio com configuração mínima
- **DomainFixtures:** provê objetos prontos para uso em testes
- **FakeRuleEngine:** permite controlar o resultado de validação sem regras reais
- **FakeEventDispatcher:** captura eventos despachados para assertions

O testkit não polui o código de produção — ele reside em `src/main/kotlin` do módulo `engine-testkit`, que é uma dependência de teste (`testImplementation`) nos módulos consumidores.
