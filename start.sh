# REMOVE CONTAINERS DOCKER DA APLICAÇÃO
docker rm solucao-de-problemas-service chatbot-service incluitech-postgres
# REMOVE IMAGENS DA APLICAÇÃO
docker rmi incluitech-backend_solucao-de-problemas-service incluitech-backend_incluitech-postgres incluitech-backend_chatbot-service
# RODA O DOCKER COMPOSE COM OS DOIS SERVIÇOS SPRINGBOOT E O BANCO DE DADOS.
docker-compose up