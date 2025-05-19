# 🚀 API RESTful de Gerenciamento de Usuários

Este projeto é uma **API RESTful** desenvolvida com **Spring Boot**, utilizando um **banco de dados em memória** onde os dados são armazenados em um arquivo JSON localizado em:

📂 src/main/resources/data/dados.json

## 🛠️ Tecnologias Utilizadas

- **Spring Boot** - Framework para desenvolvimento Java
- **Spring Web** - Para manipulação de requisições HTTP
- **Swagger UI** - Documentação interativa da API
- **Banco de Dados em Arquivo JSON** - Simulação de persistência sem necessidade de um banco relacional

---

## 🌐 Endpoints da API

### 🔹 Criar um Novo Usuário

- **URL:** `POST /users`
- **Descrição:** Adiciona um novo usuário ao banco de dados JSON.
- **Exemplo de Requisição:**

```json
{
  "name": "Novo Usuário",
  "email": "novo.usuario@example.com"
}
```

Resposta (201 Created):

```json
{
  "id": 11,
  "name": "Novo Usuário",
  "email": "novo.usuario@example.com"
}
```

### 🔹Listar Todos os Usuários
- **URL:** `GET /users`
- **Descrição:** Retorna a lista de usuários cadastrados no sistema.

Exemplo de Resposta (200 OK):

```json
[
  {
    "id": 1,
    "name": "Alice Santos",
    "email": "alice.santos@example.com"
  },
  {
    "id": 2,
    "name": "Bruno Lima",
    "email": "bruno.lima@example.com"
  }
]
```

### 🔹 Buscar Usuário por ID
- **URL:** `GET /users/{id}`
- **Descrição:** Retorna um único usuário com base no ID informado.
Exemplo de Resposta (200 OK):

```json
{
  "id": 1,
  "name": "Alice Santos",
  "email": "alice.santos@example.com"
}
```

Caso o usuário não exista (404 Not Found):

```json
{
  "error": "Usuário com ID 99 não encontrado."
}
```

### 🔹 Atualizar Usuário
- **URL:** `PUT /users/{id}`
- **Descrição:** Atualiza os dados de um usuário existente.
Exemplo de Requisição:

```json
{
  "name": "Alice Santos Atualizado",
  "email": "alice.novoemail@example.com"
}
```

Resposta (200 OK):

```json
{
  "id": 1,
  "name": "Alice Santos Atualizado",
  "email": "alice.novoemail@example.com"
}
```

### 🔹 Excluir Usuário

- **URL:** `DELETE /users/{id}`
- **Descrição:** Remove um usuário do banco de dados JSON.

Resposta (204 No Content): O usuário foi removido com sucesso.

📖 Documentação e Testes com Swagger

Para testar e visualizar a API de forma interativa, acesse o Swagger UI através da seguinte URL:

🔗 Swagger UI - Documentação da API - localhost:8080/swagger-ui.html

🚀 Como Executar o Projeto

Clone o repositório:

```bash
  git clone https://github.com/seu-usuario/seu-repositorio.git
```

Navegue até a pasta do projeto:

```bash
  cd seu-repositorio  
```

Execute o projeto com Maven:

```bash
  mvn spring-boot:run
```