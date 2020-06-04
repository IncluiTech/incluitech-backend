GRANT ALL PRIVILEGES ON DATABASE "incluitech" TO "postgres";

CREATE TABLE IF NOT EXISTS cliente(
id SERIAL PRIMARY KEY,
nome VARCHAR (50) NOT NULL,
email VARCHAR(50),
instituicao VARCHAR(50),
funcao VARCHAR(50),
status_cadastro VARCHAR (20) NOT NULL,
telefone VARCHAR(12),
data_criacao TIMESTAMP NOT NULL DEFAULT NOW(),
facebook_id VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS solucionador(
id SERIAL PRIMARY KEY,
nome VARCHAR (50) NOT NULL,
email VARCHAR(50),
lattes VARCHAR (50),
instituicao VARCHAR(50),
funcao VARCHAR(50),
experiencia VARCHAR(500),
status_cadastro VARCHAR (20) NOT NULL,
telefone VARCHAR (12),
data_criacao TIMESTAMP NOT NULL DEFAULT NOW(),
facebook_id VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tag(
id SERIAL PRIMARY KEY,
nome VARCHAR (50) NOT NULL,
data_criacao TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS tag_solucionador(
id_tag_solucionador SERIAL PRIMARY KEY,
id_tag INTEGER,
id_solucionador INTEGER,
CONSTRAINT FK_id_tag_solucionador FOREIGN KEY (id_tag)
REFERENCES tag (id),
CONSTRAINT FK_id_solucionador FOREIGN KEY (id_solucionador)
REFERENCES solucionador (id),
data_criacao TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS tag_cliente(
id_tag_cliente SERIAL PRIMARY KEY,
id_tag INTEGER,
id_cliente INTEGER,
CONSTRAINT FK_id_tag_cliente FOREIGN KEY (id_tag)
REFERENCES tag (id),
CONSTRAINT FK_id_cliente FOREIGN KEY (id_cliente)
REFERENCES cliente (id),
data_criacao TIMESTAMP NOT NULL DEFAULT NOW()
);
