GRANT ALL PRIVILEGES ON DATABASE "incluitech" TO "postgres";

CREATE TABLE IF NOT EXISTS usuario(
    id SERIAL PRIMARY KEY,
    nome VARCHAR (50) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    status_cadastro VARCHAR(1) NOT NULL,
    especialidades VARCHAR(10),
    data_criacao TIMESTAMP NOT NULL DEFAULT NOW()
);