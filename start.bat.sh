# REMOVE CONTAINERS DOCKER DA APLICAÇÃO
docker rm solucao-de-problemas-service chatbot-service incluitech-postgres
# REMOVE IMAGENS DA APLICAÇÃO
docker rmi incluitech-backend_solucao-de-problemas-service incluitech-backend_incluitech-postgres incluitech-backend_chatbot-service


cd chatbot-service/
gradle wrapper
./gradlew build

cd ../solucao-de-problemas-service/
gradle wrapper
./gradlew build

cd ..
docker-compose up