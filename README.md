# personalAPI
API RESTful em Spring Boot para gerenciamento de usuários, treinos, exercícios e dietas.

## Visão geral
Projeto em Java + Spring Boot organizado em:
- `com.personal.personalapi.controller` — controladores REST
- `com.personal.personalapi.dto` — DTOs (UserDTO, TreinoDTO, ExercicioDTO, DietaDTO)
- `com.personal.personalapi.model` — entidades
- `com.personal.personalapi.service` — lógica de negócio
- `com.personal.personalapi.repository` — persistência (Spring Data JPA)

## Funcionalidades principais
1. CRUD de Users (`/users`)
2. CRUD de Treinos (`/treinos`)
3. CRUD de Exercícios (`/exercicios`)
4. CRUD de Dietas (`/dietas`)

## Pré-requisitos (Windows)
1. Java 17+ instalado
2. Maven instalado (ou use wrapper `mvnw.cmd`)
3. Banco de dados configurado conforme `application.properties` (H2, PostgreSQL, etc.)

## Como executar (Windows)
1. No terminal (PowerShell ou CMD), na pasta do projeto:
   - Compilar e executar com Maven:
     `mvn spring-boot:run`
   - Ou gerar jar e executar:
     `mvn clean package`
     `java -jar target\personalapi-<versão>.jar`

2. API estará por padrão em:
   `http://localhost:8080`
