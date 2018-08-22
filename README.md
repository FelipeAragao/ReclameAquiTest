# Teste BackEnd para o Reclame Aqui #

##Tecnologias usadas no projeto##
    - Spring boot RESTful
    - Uso do Swagger Framework
    - Teste com MockMvc
    - Banco de dados NoSQL usando MongoDB
    - Multiplos projetos com maven
    - Micro Services com Eureka e Zuul
    - Gerando build com maven e deploy com docker-compose

##Executa o projeto##
```sh
# Testando e gerando imagens no docker
mvn clean install

# Start das imagens geradas
docker-compose up - d
```

## Eureka ##
http://localhost:8761/

## Swagger Framework ##
http://localhost:8060/swagger-ui.html

