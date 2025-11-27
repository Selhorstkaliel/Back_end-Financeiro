# Projeto Financeiro (Spring Boot + MySQL + Docker)

Este projeto implementa um sistema financeiro simples com:

- Entidades: **Pessoa**, **Categoria**, **Lancamento** e enum **TipoLancamento**.
- Banco de dados MySQL rodando em container Docker.
- Migrations controladas com **Flyway**.
- API REST com Spring Boot (controllers, services, repositories).
- DTOs de entrada/saída para melhor organização.

## Como rodar com Docker

1. Certifique-se que o Docker e Docker Compose estão instalados.
2. Na raiz do projeto (onde está o `docker-compose.yml`), execute:

   ```bash
   docker compose up --build
   ```

3. A aplicação subirá em `http://localhost:8080`.
4. O MySQL estará acessível em `localhost:3307` 

## Endpoints principais

- `POST /pessoas` – cria pessoa
- `GET /pessoas` – lista pessoas
- `POST /categorias` – cria categoria
- `GET /categorias` – lista categorias
- `POST /lancamentos` – cria lançamento
- `GET /lancamentos` – lista lançamentos
- `GET /lancamentos/filtro` – filtra lançamentos por data, tipo, categoria e pessoa
