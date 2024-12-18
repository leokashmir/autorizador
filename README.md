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

## Autenticação
```
  Autenticação: BASIC, com login = username e senha = password
```


## API - Cartao



| Metodo | URL  | Retorno HTTP Codigos                                  | Descrição                |
|:-------|:--------|:------------------------------------------------------|--------------------------|
| `POST` | `/cartoes `  | 201  Cartão criado com Sucesso, 422  Cartão já exista | Cadastrar novo cartão |

  <b>Body</b> - Objeto de Request e Response.
```
   {
      "senha": "1234",
      "numeroCartao": "6549873025634501"
   }        
```

  <br>


| Metodo | URL  | Retorno HTTP Codigos                                  |Descrição|
|:-------|:--------|:------------------------------------------------------|------|
| `GET`  | `/cartoes `  | 200 Consulta ok, 404 Cartão não existe                | Obtem saldo do cartão |

*  Response.
```
   {
     1000.00
   }        
``` 

## API - Transação


| Metodo | URL  | Retorno HTTP Codigos                                  |Descrição |
|:-------|:--------|:------------------------------------------------------|-------|
| `POST`  | `/transacoes `  | 201 Realizada com Sucesso, 422 Não Autorizada         |Realiza Transação|
<b>Body</b> - Objeto de Request.
```
   {
      "numeroCartao": "6549873025634578",
      "senhaCartao": "1234",
      "valor": 190.94
   }        
```





