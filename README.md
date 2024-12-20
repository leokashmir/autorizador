# MVP - Autorizador de Pagamentos

### Sobre o Projeto
Projeto que visa dar um exemplo de autorizador de pagamentos utlizando API-Rest, DDD, Testes uniatarios, Docker, 
Springboot-docker-compose ( para gerenciar dependências de serviços externos diretamente do  projeto)
Flyway (para versionamento do banco de dados),entre outros Partners.


## Tecnologias

- Java JDK 21     -> https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html
- Maven           -> https://maven.apache.org/
- SpringBoot      -> https://spring.io/projects/spring-boot
- SpringData      -> https://spring.io/projects/spring-data
- Lombok          -> https://projectlombok.org/
- MySql           -> https://www.mysql.com/
- Flyway          -> https://flywaydb.org/
- Docker          -> https://www.docker.com/

## Executar o Projeto
1 - Certifica-se que o Docker ja esteja instalado e rodando em sua maquina executando este comando: `docker -v`  <br><br>
![verifica_versao_docker.png](https://github.com/leokashmir/autorizador/blob/main/img/verifica_versao_docker.png?raw=true)<br><br>

2 - Execute o comando   `docker-compose up --build`, aguarde finalizar a criação do container<br><br>
![docker_compose_1.png](https://github.com/leokashmir/autorizador/blob/main/img/docker_compose_1.png?raw=true)<br><br>

3 - Execute ocomando `docker-compose ps` para verificar se o container foi criado<br><br>
![docker_Compose_PS.png](https://github.com/leokashmir/autorizador/blob/main/img/docker_Compose_PS.png?raw=true)<br><br>

4 - Execute o projeto em sua IDE.<br><br><hr>


## Autenticação
```
  Autenticação para acessar as API's: BASIC AUTH, com login = username e senha = password
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


## Teste Unitario - Cobertura (IDE Intellij)

1 - Plugin Code coverage for Java <br>
1-1 Menu-> Settings -> Plugins, Procure o Code Coverage for java e clique em install<br>


![ImgPluginCoverage.png](https://github.com/leokashmir/autorizador/blob/main/img/ImgPluginCoverage.png?raw=true)<br><br>
2 - Clique com o botão direito no arquivo/pasta de teste ou na classe que deseja executar<br><br>
2.2 - No menu que apareceu, cliquem em run/debug -> Run  with Coverage  <br><br>
![executarTestes.png](https://github.com/leokashmir/autorizador/blob/main/img/executarTestes.png?raw=true)<br><br>
3 - A cobertura será exibida diretamente na IDE, basta clicar no icone a direta que é um escudo.<br><br>
![CoberturaDeTestes.png](https://github.com/leokashmir/autorizador/blob/main/img/CoberturaDeTestes.png?raw=true)<br><br>
