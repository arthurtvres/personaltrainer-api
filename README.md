# Corexa API
API RESTful desenvolvida com Spring Boot para gerenciamento de usuários, treinos, exercícios e dietas

---

# Visão Geral

O projeto segue uma **arquitetura em camadas**:

* `com.personal.personalapi.controller` → endpoints REST da aplicação
* `com.personal.personalapi.service` → regras de negócio
* `com.personal.personalapi.repository` → acesso ao banco com Spring Data JPA
* `com.personal.personalapi.model` → entidades JPA
* `com.personal.personalapi.dto` → objetos de entrada para criação/atualização
* `com.personal.personalapi.config` → configurações da aplicação (Swagger/OpenAPI e Segurança)

---

# Tecnologias Utilizadas

* Java 17
* Spring Boot 3.5.11
* Spring Web
* Spring Data JPA
* Spring Security
* PostgreSQL
* Lombok
* Springdoc OpenAPI (`springdoc-openapi-starter-webmvc-ui` 2.8.6)
* Docker
* Docker Compose

---

# Configuração da Aplicação

Arquivo:

`personalapi/src/main/resources/application.properties`

Configuração atual:

* Porta da aplicação: `8080`
* Banco de dados: PostgreSQL
* URL atual:
  `jdbc:postgresql://localhost:5433/personaldb`
* Estratégia do Hibernate: `update`
* Credenciais do Spring Security (padrão): `admin` / `admin123`

Ajuste usuário, senha e URL conforme o seu ambiente.

---

# Autenticação

Atualmente os endpoints estão liberados em `SecurityConfig`, então não há login obrigatório para a API e o Swagger.

---

# Como Executar (Windows)

Dentro da pasta `personalapi`:

```powershell
cd .\personalapi
.\mvnw.cmd spring-boot:run
```

Ou gerar o JAR:

```powershell
cd .\personalapi
.\mvnw.cmd clean package
java -jar .\target\personalapi-0.0.1-SNAPSHOT.jar
```

A API estará disponível em:

```
http://localhost:8080
```

---

# Execução com Docker

Dentro da pasta `personalapi`:

```powershell
docker build -t personalapi:latest .
docker run --rm -p 8080:8080 personalapi:latest
```

O `Dockerfile` utiliza **build multi-stage** para compilar o projeto com Maven e executar em um container com JRE leve.

Caso necessário, variáveis de ambiente podem ser passadas usando `-e`.

---

# Execução com Docker Compose

O arquivo `personalapi/docker-compose.yml` sobe:

* API Spring Boot
* PostgreSQL 16

Executar:

```powershell
docker compose up --build
```

Configuração do banco:

* Host: `localhost`
* Porta: `5433`
* Usuário: `postgres`
* Senha: `1512`
* Banco: `personaldb`

Para parar e remover os containers e volumes:

```powershell
docker compose down -v
```

---

# Documentação da API

Com a aplicação rodando:

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON

```
http://localhost:8080/v3/api-docs
```

---

# Endpoints

## Users (`/users`)

| Method | Endpoint      | Descrição      |
| ------ | ------------- | -------------- |
| POST   | `/users`      | Create user    |
| GET    | `/users`      | List users     |
| GET    | `/users/{id}` | Get user by id |
| DELETE | `/users/{id}` | Delete user    |

---

## Workouts (`/workouts`)

| Method | Endpoint                  | Descrição                     |
| ------ | ------------------------- | ----------------------------- |
| POST   | `/workouts`               | Create workout                |
| GET    | `/workouts`               | List workouts                 |
| GET    | `/workouts/{id}`          | Get workout by id             |
| GET    | `/workouts/user/{userId}` | List all workouts from a user |
| DELETE | `/workouts/{id}`          | Delete workout                |

---

## Exercises (`/exercises`)

| Method | Endpoint                         | Descrição                     |
| ------ | -------------------------------- | ----------------------------- |
| POST   | `/exercises`                     | Create exercise               |
| GET    | `/exercises`                     | List exercises                |
| GET    | `/exercises/{id}`                | Get exercise by id            |
| GET    | `/exercises/workout/{workoutId}` | List exercises from a workout |
| PUT    | `/exercises/{id}`                | Update exercise               |
| DELETE | `/exercises/{id}`                | Delete exercise               |

---

## Diets (`/diets`)

| Method | Endpoint               | Descrição       |
| ------ | ---------------------- | --------------- |
| POST   | `/diets`               | Create diet     |
| GET    | `/diets`               | List diets      |
| GET    | `/diets/{id}`          | Get diet by id  |
| GET    | `/diets/user/{userId}` | Get user's diet |
| PUT    | `/diets/{id}`          | Update diet     |
| DELETE | `/diets/{id}`          | Delete diet     |

---

# Exemplos de DTO

## UserDTO

```json
{
  "name": "Joao",
  "email": "joao@email.com",
  "password": "123456",
  "role": "ALUNO"
}
```

---

## WorkoutDTO

```json
{
  "name": "Treino A",
  "description": "Treino de peito e triceps",
  "userId": 1
}
```

---

## ExerciseDTO

```json
{
  "name": "Supino Reto",
  "description": "Exercicio com barra para peitoral",
  "sets": 4,
  "reps": 10,
  "workoutId": 1
}
```

---

## DietDTO

```json
{
  "name": "Dieta Hipocalorica",
  "description": "Reducao de calorias para perda de peso",
  "goal": "WEIGHT_LOSS",
  "dailyCalories": 1800,
  "proteinGrams": 140,
  "carbGrams": 180,
  "fatGrams": 60,
  "userId": 1
}
```

---

# Observações

* A senha do usuário não é retornada nas respostas (`@JsonIgnore` em `User.password`).
* Erros de validação retornam `400` e exibem a mensagem do DTO.
* Erros de regra de negócio retornam `400` com mensagem descritiva.
* Erros de recurso não encontrado retornam `404`.
* A API segue **boas práticas REST**:

    * recursos em plural
    * uso correto dos métodos HTTP
    * endpoints organizados por recurso.
