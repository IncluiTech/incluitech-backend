# Incluitech Backend
O repositório está organizado em 2 serviços e uma configuração inicial/temporária pro database de desenvolvimento.

# Rodando Localmente
Para rodar localmente os serviços listados e o banco de dados correspondente, pode ser utilizado o docker-compose.yml
que está configurado para rodar os 3 contâineres Docker.

### Requisitos:
- Ter o [Docker](https://docs.docker.com/install/) e o [Docker Compose](https://docs.docker.com/compose/install/) instalados na máquina.  
    - Se você utiliza Linux, use o comando `sudo apt install docker docker-compose -y`.
- Ter o [Gradle](https://gradle.org/install/) instalado na máquina.  
    

### Rodando
- Execute o arquivo start.sh como administrador:
    - primeiro é necessário dar permissão de execução ao script: `chmod +x start.sh`
    - Execute o script: `sudo ./start.sh`
- Note que o arquivo start.sh faz o build de cada projeto e depois inicia o contâineres docker.'
    - O solucao-de-problemas-service estará disponível em http://localhost:8080/
    - O chatbot-service estará disponível em http://localhost:8081/
