# Incluitech Backend
O repositório está organizado em 2 serviços e uma configuração inicial/temporária pro database de desenvolvimento.

# Rodando Localmente
Para rodar localmente os serviços listados e o banco de dados correspondente, pode ser utilizado o docker-compose.yml
que está configurado para rodar os 3 contâineres Docker.

### Requisitos:
   - Ter o [Docker](https://docs.docker.com/install/) e o [Docker Compose](https://docs.docker.com/compose/install/) instalados na máquina.  
        - Se vc utiliza Linux, use o comando `sudo apt install docker docker-compose -y`.
### Rodando
- Para cada projeto, execute './gradlew build'
- Na pasta raíz do repositório, execute 'sudo docker-compose up'
    - O solucao-de-problemas-service estará disponível em http://localhost:8080/
    - O chatbot-service estará disponível em http://localhost:8081/ 
