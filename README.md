# MVP - Autorizador de Pagamentos

### Sobre o Projeto
Projeto que visa dar um exemplo de autorizador de pagamentos utlizando API-Rest, DDD, Testes uniatarios, Docker, 
Springboot-docker-compose ( para gerenciar dependências de serviços externos diretamente do  projeto)
Flyway (para versionamento do banco de dados),entre outros Partners.


## Tecnologias

- Java JDK 23     -> https://www.oracle.com/java/technologies/javase/jdk23-archive-downloads.html
- Maven           -> https://maven.apache.org/
- SpringBoot      -> https://spring.io/projects/spring-boot
- SpringData      -> https://spring.io/projects/spring-data
- Lombok          -> https://projectlombok.org/
- Hibernate       -> https://hibernate.org/
- MySql           -> https://www.mysql.com/
- Flyway          -> https://flywaydb.org/
- Docker          -> https://www.docker.com/

## Executar o Projeto
Certifica-se que o Docker ja esteja rodando em sua maquina, em seguida basta executar o projeto
em sua IDE. Ao iniciar a aplicação os conteiners ja serão criados no Docker, inclusive no banco de dados, ja será
criado a tabela necessaria para a aplicação.

## API - Cartao

* Cadastra um novo cartão <br>
```http
  POST /cartoes
```

  <b>Body</b> - Objeto de Request e Response.
```
   {
      "senha": "1234",
      "numeroCartao": "6549873025634501"
   }        
```

* Obtem saldo do cartão  <br>
```http
  GET /cartoes/{numeroCartao}
```

*  Response.
```
   {
     1000.00
   }        
``` 
