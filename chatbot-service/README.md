# Incluitec-Backend: Chatbot-service

### Projeto que possui e roda o bot e seus possíveis fluxos.

É constituido pelos seguintes componentes e conceitos:
- 
- _UserMessage_: Mensagem vinda do facebook.
- _Contexto_: Memória do bot por entre mensagens.
- _UsuarioDaMensagem_: Abstração contendo o id do usuário atual + em que grupo ele se encontra (`SOLUCIONADOR, CLIENTE`)
    - em caso de não possuir nenhum tipo, ele é classificado como `DESCOHECIDO`
- _MensagemInterna_: Mensagem de um Usuário (`UserMessage`) simplificada para facilidade no seu tratamento
    - Possui qual o tipo da mensagem `BOTAO, TEXTO, ANEXO` e seu conteúdo
    - Possui um UsuarioDaMensagem
    - Possui o contexto atual da conversa 
- _UserService_: Classe responsável por pegar as informações do usuário.
- _FacebookSendService_: Classe reponsável por enviar as mensagem geradas de volta ao facebook.
- _RegraDoBot_: Interface principal do fluxo, representa uma "Regra" do bot. Suas Implementações geram uma BotMessage
e alteram o contexto da conversa.
- _ConjuntoDeRegras_: Classe responsável por representar as regras dos fluxos macros, (`SOLUCIONADOR, CLIENTE, DESCONHECIDO`)
Nela está contido as regras de cada fluxo.
- _BotMessage_: Classe representada pela mensagem que um bot irá enviar. É gerada pela RegraDoBot.
- _BotService_: Classe responsável por gerenciar a conversa do usuário, recuperando e salvando o contexto gerado entre mensagens.
- _BotEngine_: Classe responsável por selecionar qual o conjunto a ser seguido (`SOLUCIONADOR, CLIENTE, DESCONHECIDO`)
de acordo com a regra imposta no ConjutoDeRegras.
- _MessageMapper_: Mapper que faz a conversão entre os modelos do domínio:
    - Mensagem vinda do facebook `UserMessage` -> Mensagem interna da aplicação `MensagemInterna`. 
    - Mensagem do bot `BotMessage` -> Mensagem de envio para o facebook `FacebookMessage`.

 Como o bot Funciona?
-
O bot é representado por um conjunto de regras, em que, cada regra gera uma mensagem específica.
##### Como eu sei qual o momento certo para se enviar a mensagem na conversa?
A interface `RegraDoBot` possui dois métodos:
- Um sendo a `processa`, em que, processa a mensagem interna recebida e 
gera uma `BotMessage` que mais tarde será enviada de volta ao facebook.
- E outro sendo o `verifica`, em que, define através de uma regra booleana se aquela Regra é a mais 
adequada para a mensagem atual.
##### Quero poder salvar algo gerado na conversa, por exemplo, as tags que o usuário está selecionando, o email dele na hora do login, entre outros. Posso salvar como um atributo de uma implementação da RegraDoBot?
Não, pelo fato de ter a capacidade de várias pessoas estarem usando o sistema ao mesmo tempo, se algum estado for salvo, 
pode (e vai) acontecer dos estados de mais de uma conversa interferirem um no outro. Por esse motivo o bot foi desenhado 
para ser um sistema _stateless_, ou seja, sem a possibilidade de guardar estado.  
Mas vista a necessidade de se armazenar informações entre mensagens, existe o atributo Contexto da MensagemInterna, 
que é único para cada conversa. É uma estrutura de dados do tipo `Map<String, Object>` então todo o estado da conversa é salvo nele. 
(ele é um exemplo de uma memória do bot, sei que o usuário está falando de tags, por que no contexto tem alguma chave que me mostra isso).
O contexto é um tipo de informação que não será persistida, o que quer dizer que pode, e muitas vezes vai, 
ser zerado em qualquer ponto da conversa se necessáio. (Final de algum fluxo, O começo de outros, etc...)

## Preciso criar uma regra nova, como eu faço?
Definida de qual conjunto será criada a regra (`SOLUCIONADOR, CLIENTE, DESCONHECIDO`), vá até o pacote de regras desse 
conjunto e crie uma classe nova que implemente a interface RegraDoBot.

Então vamos dizer que queremos criar uma Regra de Saudação inicial para o fluxo de DESCONHECIDO:
no pacote `[...] business.conjunto.desconhecido.regras` vou criar uma classe como:
```java
public class SaudacaoInicialRegraBot implements RegraDoBot {
   @Override
       public boolean verifica(MensagemInterna message) {
           return false;
       }
   
       @Override
       public BotMessage processa(MensagemInterna message) {
           return null;
       }
}
```
Quero que ela só seja acionada se meu contexto estiver vazio então meu método verifica terá a seguinte implementação:
```
@Override
public boolean verifica(MensagemInterna message) {
    return message.getContexto().isEmpty();
}
```
Quero também que, a mensagem gerada por essa regra, seja uma lista de botões um pra iniciar o login, 
outro direcionado para o site da incluitec e que o contexto da mensagem seja preservado.
Então meu processa terá a seguinte cara:
```
public BotMessage processa(MensagemInterna message) {
    List<Button> botoes = Arrays.asList(
                    new PayloadButton("Iniciar Login", "INICIAR_LOGIN"),
                    new LinkButton("Site incluiTec", "incluitec.com")
    );
    return new BotMessage(message.getContexto(), Arrays.asList(
                    new ButtonComponentBotMessage("Olá, Me chamo helena, o que deseja fazer?", botoes)
    ));
}
```
Feito isso, Instanciamos essa Regra na classe DesconhecidoConjuntoRegras em seu contrutor.
```
public DesconhecidoConjuntoRegras() {
        super(Arrays.asList(new SaudacaoInicialRegraBot()));
}
```
Feito, agora é só rodar a aplicação e testar o seu novo fluxo.

###Certo... Tenho minha aplicação rodando na porta 8080, mas quero testar, como eu faço?
- Primeiramente, baixe um programa chamado Ngrok (caso conheça algum outra forma de expor sua porta 8080 para o mundo, 
sinta-se a vontede de utilizá-la).
- Feito isso, execute o comando no terminal `ngrok http 8080`, ao fazer isso sua porta está exposta pelo link gerado que estará mostando no seu terminal, por 8 horas.
- Caso já possua um app no facebookDevelopers pule os próximos passos:
    - Vá em `developers.facebook.com` e cria um app novo.
    - Dê o nome que vocề desejar.
    - Pressione o botão `set up` no card do Messenger
    - Dê uma "scrollada" para baixo até a parte de `Access Token` e crie uma página no facebook
        - Caso já possua uma página de teste, ignore esse ponto.
    - Pressione em `Add or Remove Pages`
    - No popup aberto, selecione a página de teste criada anteriormente (caso já possua uma, selecione-a)
    - Após completar os passos do popup, sua página está vinculada a sua app.
    - Logo a baixo, selecione o botão `Add Callback Url`
    - Na caixa de diálogo aberta, coloque no primerio input, o link **https** gerado no terminal + /webhook
        - ex: https://5f90600d.ngrok.io/webhook
    - No segundo input, escreva `tokenToVerify`
        - esse token está configurado no application.properties com o campo `facebook.verify.token`
    - Levante a aplicação.
    - Aperte `Verify and Save`. Se tudo ocorrer corretamente, o facebook agora está integrado com sua aplicação
    - Derrube a aplicação.
    - Então selecione `Add Subscriptions` logo abaixo e marque `messages` e `messages_postbacks`, selecione `save`
    para salvar suas configurações.
- Vá em `Access Token` e pressione `Generate Token`
- No popup aberto, leia o que está escrito e pressione `I Uderstand`
- Copie o token gerado e cole no campo `facebook.app.access.token=` 
- Levante a aplicação.
    - Na caixa de diálogo aberta, coloque no primerio input, o link **https** gerado no terminal + /webhook
        - ex: https://5f90600d.ngrok.io/webhook
    - No segundo input, escreva `tokenToVerify`
        - esse token está configurado no application.properties com o campo `facebook.verify.token`
- Vá para o `messenger.com`. Procure sua página, envie uma mensagem.
- Se tudo funcionou corretamente, as requisições estarão chegando na sua aplicação.


#### Qualquer dúvida, não tenha receio de procurar alguém.