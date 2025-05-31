
# Sistema RPG com Padrão Chain of Responsibility

## Visão Geral

Este projeto demonstra o uso do padrão **Chain of Responsibility** no contexto de um RPG, para tratar o dano recebido por um personagem de forma sequencial. Em vez de centralizar toda a lógica de dano em uma única classe, quebramos o processo em etapas separadas, onde cada etapa (ou *handler*) decide como lidar com parte do dano e passa o restante adiante.

Esse padrão permite:
- Extensibilidade: adicionar ou remover etapas sem alterar as outras.
- Flexibilidade: reorganizar a cadeia conforme necessário para diferentes personagens ou situações.
- Separação de responsabilidades: cada handler cuida apenas da sua parte específica.

## Estrutura do Projeto

- **Handler (abstract):** Interface base que define o método `handle()` e o link para o próximo handler.
- **DamageHandler:** Aplica uma redução inicial ao dano, simulando habilidades ou buffs.
- **ArmorHandler:** Reduz o dano com base no valor da armadura.
- **ShieldHandler:** Usa pontos de escudo para absorver dano antes de atingir a saúde.
- **FinalHealthHandler:** Aplica qualquer dano restante diretamente à vida do personagem.
- **Character:** Representa o personagem e mantém a cadeia de handlers.
- **Main:** Exemplo de uso do sistema.
- **DamageHandlerTest:** Testes unitários usando JUnit 5.

## Como Funciona

1. O personagem recebe uma quantidade de dano inicial (por exemplo, 50).
2. O `DamageHandler` reduz o dano base (por exemplo, 5 pontos).
3. O `ArmorHandler` absorve o que puder com a armadura (ex: 10 pontos).
4. O `ShieldHandler` usa pontos de escudo disponíveis para cobrir o restante.
5. O `FinalHealthHandler` aplica o que sobrar diretamente à saúde.

**Exemplo de cadeia:**  
`DamageHandler(5) → ArmorHandler(10) → ShieldHandler(20) → FinalHealthHandler`

### Fluxo ilustrado (dano de 50):

- Dano inicial: 50  
- Após habilidade (DamageHandler): 50 - 5 = 45  
- Após armadura (ArmorHandler): 45 - 10 = 35  
- Após escudo (ShieldHandler): 35 - 20 = 15  
- Vida final: 100 - 15 = 85

## Testes Unitários

Os testes cobrem vários cenários importantes para garantir a robustez do sistema:

| Teste                                | O que verifica                                              |
|--------------------------------------|------------------------------------------------------------|
| **testDamageLessThanDefenses**       | Dano menor que todas as defesas → vida intacta.            |
| **testDamageExactlyDefenses**        | Dano exatamente igual às defesas → vida intacta.           |
| **testDamageMoreThanDefenses**       | Dano maior que defesas → apenas sobra atinge a vida.       |
| **testMultipleAttacksDepletingShield** | Vários ataques pequenos que eventualmente esgotam o escudo. |
| **testOverkillDamage**               | Dano extremamente alto → vida não fica negativa.           |

Os testes usam **JUnit 5** e validam cenários normais e limites, garantindo que a cadeia funciona corretamente mesmo em casos extremos.

## Como Executar

1. Compile todos os arquivos `.java`.  
2. Execute a classe `Main` para rodar o sistema e verificar o resultado (os dados podem ser capturados para testes, pois não há `println`).  
3. Execute os testes com um runner JUnit 5 para validar todos os cenários.

## Melhorias Futuras

- Adicionar handlers temporários (ex: buffs, debuffs).  
- Permitir configuração dinâmica da cadeia por meio de arquivos de configuração ou interface gráfica.  
- Criar handlers para outros efeitos além de dano (cura, efeitos mágicos, penalidades).

