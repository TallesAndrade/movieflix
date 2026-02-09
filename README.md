# 🎬 MovieFlix API

API REST para gerenciamento de filmes, categorias e plataformas de streaming, desenvolvida com **Spring Boot 3**. Este projeto foi criado com o objetivo de colocar em prática os estudos de **Spring Security** e autenticação com **token JWT**.

## 📋 Índice

- [Tecnologias](#-tecnologias)
- [Arquitetura](#-arquitetura)
- [Pré-requisitos](#-pré-requisitos)
- [Configuração do Ambiente](#-configuração-do-ambiente)
- [Executando o Projeto](#-executando-o-projeto)
- [Documentação da API (Swagger)](#-documentação-da-api-swagger)
- [Autenticação](#-autenticação)
- [Endpoints](#-endpoints)
  - [Autenticação](#autenticação-auth)
  - [Filmes](#filmes-movieflixmovie)
  - [Categorias](#categorias-movieflixcategory)
  - [Streamings](#streamings-movieflixstreaming)
- [Banco de Dados](#-banco-de-dados)

---

## 🚀 Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 17 |
| Spring Boot | 3.5.4 |
| Spring Security | JWT (Auth0 java-jwt 4.4.0) |
| PostgreSQL | 13 |
| Flyway | Migrations automáticas |
| Lombok | Redução de boilerplate |
| Springdoc OpenAPI | 2.8.9 (Swagger UI) |
| Maven | Build e gerenciamento de dependências |
| Docker Compose | Containerização do banco de dados |

---

## 🏗 Arquitetura

O projeto segue o padrão **MVC** (Model-View-Controller) com as seguintes camadas:

```
src/main/java/com/movieflix/movieflix/
├── config/          # Configurações de segurança, JWT e Swagger
├── controller/      # Endpoints REST e DTOs (Request/Response)
├── entity/          # Entidades JPA
├── service/         # Regras de negócio
├── repository/      # Acesso a dados (Spring Data JPA)
├── mapper/          # Conversores DTO ↔ Entidade
├── exceptions/      # Exceções personalizadas e tratamento global
```

---

## 📦 Pré-requisitos

- **Java 17** ou superior
- **Maven** 3.8+
- **Docker** e **Docker Compose** (para o banco de dados PostgreSQL)

---

## ⚙ Configuração do Ambiente

### 1. Variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto ou configure as seguintes variáveis de ambiente:

| Variável | Descrição | Exemplo |
|---|---|---|
| `DATABASE_URL` | URL de conexão JDBC do PostgreSQL | `jdbc:postgresql://localhost:5432/movieflix_db` |
| `DATABASE_USERNAME` | Usuário do banco de dados | `postgres` |
| `DATABASE_PASSWORD` | Senha do banco de dados | `sua_senha` |
| `SECRET_HASH` | Chave secreta para assinatura dos tokens JWT | `minha-chave-secreta` |

### 2. Banco de dados com Docker

Suba o container do PostgreSQL usando Docker Compose:

```bash
docker compose up -d
```

Isso criará um container com PostgreSQL 13 e o banco `movieflix_db`.

---

## ▶ Executando o Projeto

```bash
# Compilar o projeto
./mvnw clean install -DskipTests

# Executar a aplicação
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

## 📖 Documentação da API (Swagger)

Após iniciar a aplicação, acesse a documentação interativa da API:

- **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🔐 Autenticação

A API utiliza **JWT (JSON Web Token)** para autenticação.

### Fluxo de autenticação:

1. **Registre um usuário** via `POST /auth`
2. **Faça login** via `POST /auth/login` para obter o token JWT
3. **Inclua o token** no header `Authorization` das requisições protegidas:

```
Authorization: Bearer <seu_token_jwt>
```

### Endpoints públicos (não exigem autenticação):

- `POST /auth` — Registro de usuário
- `POST /auth/login` — Login
- `/swagger-ui/**` — Documentação Swagger
- `/v3/api-docs/**` — Especificação OpenAPI

Todos os demais endpoints exigem autenticação via token JWT.

---

## 📡 Endpoints

### Autenticação (`/auth`)

#### Registrar Usuário

```
POST /auth
```

**Request Body:**

```json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "password": "senha123"
}
```

**Response (200):**

```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@email.com"
}
```

#### Login

```
POST /auth/login
```

**Request Body:**

```json
{
  "email": "joao@email.com",
  "password": "senha123"
}
```

**Response (200):**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### Filmes (`/movieflix/movie`)

> 🔒 Todos os endpoints de filmes exigem autenticação.

#### Listar todos os filmes

```
GET /movieflix/movie
```

**Response (200):**

```json
[
  {
    "id": 1,
    "title": "O Poderoso Chefão",
    "description": "A saga da família Corleone.",
    "releaseDate": "24/03/1972",
    "rating": 9.2,
    "categories": [
      { "id": 1, "name": "Drama" }
    ],
    "streamings": [
      { "id": 1, "name": "Netflix" }
    ]
  }
]
```

#### Buscar filme por ID

```
GET /movieflix/movie/{id}
```

#### Criar filme

```
POST /movieflix/movie
```

**Request Body:**

```json
{
  "title": "O Poderoso Chefão",
  "description": "A saga da família Corleone.",
  "releaseDate": "24/03/1972",
  "rating": 9.2,
  "categories": [1],
  "streamings": [1]
}
```

#### Atualizar filme

```
PUT /movieflix/movie/{id}
```

**Request Body:** Mesmo formato da criação.

#### Deletar filme

```
DELETE /movieflix/movie/{id}
```

#### Buscar filmes por categoria

```
GET /movieflix/movie/search?category={categoryId}
```

---

### Categorias (`/movieflix/category`)

> 🔒 Todos os endpoints de categorias exigem autenticação.

#### Listar todas as categorias

```
GET /movieflix/category
```

**Response (200):**

```json
[
  { "id": 1, "name": "Ação" },
  { "id": 2, "name": "Drama" }
]
```

#### Criar categoria

```
POST /movieflix/category
```

**Request Body:**

```json
{
  "name": "Comédia"
}
```

#### Buscar categoria por ID

```
GET /movieflix/category/{id}
```

#### Deletar categoria

```
DELETE /movieflix/category/{id}
```

---

### Streamings (`/movieflix/streaming`)

> 🔒 Todos os endpoints de streamings exigem autenticação.

#### Listar todos os streamings

```
GET /movieflix/streaming
```

**Response (200):**

```json
[
  { "id": 1, "name": "Netflix" },
  { "id": 2, "name": "Amazon Prime Video" }
]
```

#### Criar streaming

```
POST /movieflix/streaming
```

**Request Body:**

```json
{
  "name": "Disney+"
}
```

#### Buscar streaming por ID

```
GET /movieflix/streaming/{id}
```

#### Deletar streaming

```
DELETE /movieflix/streaming/{id}
```

---

## 🗄 Banco de Dados

### Diagrama de Entidades

```
┌──────────┐       ┌────────────────┐       ┌────────────┐
│ category │       │ movie_category │       │   movie    │
├──────────┤       ├────────────────┤       ├────────────┤
│ id (PK)  │◄──────│ category_id(FK)│       │ id (PK)    │
│ name     │       │ movie_id (FK)  │──────►│ title      │
└──────────┘       └────────────────┘       │ description│
                                            │ release_date│
┌───────────┐      ┌─────────────────┐      │ rating     │
│ streaming │      │ movie_streaming │      │ created_at │
├───────────┤      ├─────────────────┤      │ updated_at │
│ id (PK)   │◄─────│ streaming_id(FK)│      └────────────┘
│ name      │      │ movie_id (FK)   │──────────┘
└───────────┘      └─────────────────┘

┌──────────┐
│  users   │
├──────────┤
│ id (PK)  │
│ name     │
│ email    │ (UNIQUE)
│ password │ (BCrypt)
└──────────┘
```

### Migrações

As migrações do banco de dados são gerenciadas automaticamente pelo **Flyway** e executadas na inicialização da aplicação. Os scripts SQL ficam em `src/main/resources/db/migration/`.
