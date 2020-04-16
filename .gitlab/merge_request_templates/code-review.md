[] Dar contexto (breve resumo) da task desenvolvida, e explicar solução implementada
[ ] Código na mesma linguagem: português
[ ] Convenções de naming e de estrutura de diretórios da linguagem: https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html
[ ] Nomes precisos e consistentes
[ ] Remover dead code e imports não utilizados
[ ] Remover coisas “development only” hardcoded no código
[ ] Utilizar encapsulamento (modificadores de acesso)
[ ] Evitar variáveis globais, favorecer imutabilidade (https://martinfowler.com/bliki/ValueObject.html)
[ ] Não usar System.out.println, usar slf4j e uma lib de logging
[ ] Separação em camadas
    * e.g.: API -> Service -> Repository
    * Cada camada com seu objeto (criar conversores entre camadas)
[ ] Máximo 10 arquivos
    * Quebrar em PRs pequenos, incrementais
    * Mais seguro, menos chance de passar algo errado
[ ] Mínimo 10 testes unitários
    * Testar edge cases, passando null, passando valores muito grandes, etc (pensar e validar todas possibilidades)
    * Testes que façam sentido, testar regra de negócio, testar lógica
[ ] Design está complexo?
    * Muitas classes / métodos?
    * Classes / métodos rasos, com pouco valor, ou que não adicionam nenhuma abstração?
    * Código duplicado, uma mesma informação espalhada em muitos lugares?
    * Nomes vagos?
    * Código obscuro, trechos de código não óbvios? (adicionar comentários)