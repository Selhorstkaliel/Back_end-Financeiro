--
-- Arquivo de migration do Flyway
-- V1__create_tables.sql: primeira versão do banco, criando as tabelas principais.
--

-- Tabela de pessoas do sistema (clientes, usuários, etc.)
CREATE TABLE pessoa (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,  -- Chave primária, auto incremento
    nome VARCHAR(255) NOT NULL,            -- Nome da pessoa
    ativo BIT NOT NULL,                    -- Indica se a pessoa está ativa (1) ou inativa (0)

    -- Campos de endereço "embutidos" na entidade Pessoa (Endereço como @Embedded)
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(50) NOT NULL,
    complemento VARCHAR(255),
    bairro VARCHAR(255) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(50) NOT NULL
);

-- Tabela de categorias de lançamento (ex: Aluguel, Salário, Lazer)
CREATE TABLE categoria (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,  -- Chave primária auto incremento
    nome VARCHAR(255) NOT NULL             -- Nome da categoria
);

-- Tabela de lançamentos financeiros (receitas e despesas)
CREATE TABLE lancamento (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,  -- Chave primária auto incremento

    descricao VARCHAR(255) NOT NULL,       -- Descrição do lançamento
    data_vencimento DATE NOT NULL,         -- Data de vencimento do lançamento
    data_pagamento DATE,                   -- Data em que foi efetivamente pago (pode ser nula)
    valor DECIMAL(10,2) NOT NULL,          -- Valor monetário do lançamento
    observacao TEXT,                       -- Campo livre para observações
    tipo VARCHAR(20) NOT NULL,             -- Tipo de lançamento (RECEITA ou DESPESA)

    id_categoria BIGINT NOT NULL,          -- FK para categoria
    id_pessoa BIGINT NOT NULL,             -- FK para pessoa

    -- Cria a foreign key para a tabela categoria
    CONSTRAINT fk_lancamento_categoria
        FOREIGN KEY (id_categoria)
        REFERENCES categoria (id),

    -- Cria a foreign key para a tabela pessoa
    CONSTRAINT fk_lancamento_pessoa
        FOREIGN KEY (id_pessoa)
        REFERENCES pessoa (id)
);
