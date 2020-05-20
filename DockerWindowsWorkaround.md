# Workaround para dockerizar builds locais no Windows

## Instalação windows Home
Caso o problema seja a versão Home do Windows, voce precisa instalar o Docker Toolbox (legado) ao invés do Docker mais recente:
[https://docs.docker.com/toolbox/toolbox_install_windows/](https://docs.docker.com/toolbox/toolbox_install_windows/)

## Fazendo o build do aplicativo
- Rode o Docker Quickstart e espere carregar.
- Pelo terminal do docker, entre na pasta que contém o arquivo 'gradlew' (ex: /solucao-de-problemas-service/, /chatbot-service/) e execute o build do projeto:
`$ gradlew clean build`
- Verifique se o arquivo buildado aparece na pasta 'build/libs' (ex: solucao-de-problemas-service.jar)

## Alterando Dockerfile
- Altere o Dockerfile para apontar para o build local. Exemplo de arquivo docker apontando para o local:
```Docker
FROM openjdk:12-jdk-alpine
WORKDIR /usr/local/bin/
COPY ./build/libs/solucao-de-problemas-service.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","solucao-de-problemas-service.jar"]
```

## Repita
- Repita o mesmo para o outro serviço do backend (/solucao-de-problemas-service/, /chatbot-service/).

## Executando o app
- Caso haja containers rodando, execute o script 'start.sh' para limpá-los. Provavelmente o erro ainda vai acontecer.
- Na pasta raiz do projeto, execute o Docker Composer:
`$ docker-compose up`

## Acessando as APIs
- O Docker Toolbox roda em uma máquina virtual (ele mesmo instala e executa a máquina virtual para voce e cria um túnel entre seu localhost e o virtual).
- No momento que o terminal abre, ele exibe o IP do túnel. O padrão é:
`192.168.99.100`
- Ou seja, para acessar a API que está no localhost da máquina virtual, voce insere no Postman (ou navegador) fora da máquina virtual o IP `192.168.99.100`.
