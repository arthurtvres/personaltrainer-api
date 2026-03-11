# personalAPI
API RESTful em Spring Boot para gerenciamento de usuarios, treinos, exercicios e dietas.

## Visao geral
O projeto esta organizado em camadas:
- `com.personal.personalapi.controller` - endpoints REST
- `com.personal.personalapi.service` - regras de negocio
- `com.personal.personalapi.repository` - acesso a dados com Spring Data JPA
- `com.personal.personalapi.model` - entidades JPA
- `com.personal.personalapi.dto` - objetos de entrada para criacao/atualizacao
- `com.personal.personalapi.config` - configuracoes (OpenAPI/Swagger)

## Stack utilizada
- Java 17
- Spring Boot 3.5.11
- Spring Web
- Spring Data JPA
- PostgreSQL (driver runtime)
- Lombok
- Springdoc OpenAPI (`springdoc-openapi-starter-webmvc-ui` 2.8.6)

## Configuracao atual
Arquivo: `personalapi/src/main/resources/application.properties`

- Porta da aplicacao: `8080`
- Banco configurado: PostgreSQL
- URL atual: `jdbc:postgresql://localhost:5433/personaldb`
- Hibernate DDL: `update`

> Ajuste URL, usuario e senha do banco no `application.properties` conforme seu ambiente.

## Como executar (Windows)
Na pasta `personalapi`:

```powershell
cd .\personalapi
.\mvnw.cmd spring-boot:run
```

Ou gerar JAR e executar:

```powershell
cd .\personalapi
.\mvnw.cmd clean package
java -jar .\target\personalapi-0.0.1-SNAPSHOT.jar
```

API disponivel em: `http://localhost:8080`

## Documentacao da API
Com a aplicacao em execucao:
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Endpoints
### Users (`/users`)
- `POST /users` - cria usuario
- `GET /users` - lista usuarios
- `GET /users/{id}` - busca usuario por id
- `DELETE /users?id={id}` - remove usuario por query param

### Treino (`/treino`)
- `POST /treino` - cria treino
- `GET /treino` - lista treinos
- `GET /treino/{id}` - busca treino por id
- `GET /treino/usuario/{userId}/todos` - lista treinos de um usuario
- `DELETE /treino?id={id}` - remove treino por query param

### Exercicios (`/exercicios`)
- `POST /exercicios` - cria exercicio
- `GET /exercicios` - lista exercicios
- `GET /exercicios/{id}` - busca exercicio por id
- `PUT /exercicios/{id}` - atualiza exercicio
- `GET /exercicios/treino/{treinoId}` - lista exercicios de um treino
- `DELETE /exercicios/{id}` - remove exercicio

### Dieta (`/dieta`)
- `POST /dieta` - cria dieta
- `GET /dieta` - lista dietas
- `GET /dieta/{id}` - busca dieta por id
- `PUT /dieta/{id}` - atualiza dieta
- `DELETE /dieta/{id}` - remove dieta
- `GET /dieta/usuario/{userId}` - busca uma dieta do usuario
- `GET /dieta/usuario/{userId}/todas` - lista todas as dietas do usuario

## DTOs de entrada
### `UserDTO`
```json
{
  "name": "Joao",
  "email": "joao@email.com",
  "password": "123456",
  "role": "ALUNO"
}
```

### `TreinoDTO`
```json
{
  "nome": "Treino A",
  "descricao": "Treino de peito e triceps",
  "userId": 1
}
```

### `ExercicioDTO`
```json
{
  "nome": "Supino reto",
  "descricao": "Barra livre",
  "series": 4,
  "repeticoes": 10,
  "treinoId": 1
}
```

### `DietaDTO`
```json
{
  "nome": "Dieta hipocalorica",
  "descricao": "Reducao de calorias",
  "objetivo": "PERDA_DE_PESO",
  "caloriasDiarias": 1800,
  "proteinasGramas": 140,
  "carboidratosGramas": 180,
  "gordurasGramas": 60,
  "userId": 1
}
```

## Observacoes
- A senha do usuario e ignorada na serializacao de resposta (`@JsonIgnore` em `User.password`).
- Alguns metodos de busca/relacao lancam `RuntimeException` quando nao encontram dados.
- Nomes de rotas estao mistos entre plural e singular por implementacao atual (`/users`, `/treino`, `/exercicios`, `/dieta`).
