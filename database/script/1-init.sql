GRANT ALL PRIVILEGES ON DATABASE "incluitech" TO "postgres";

CREATE TABLE IF NOT EXISTS cliente(
id SERIAL PRIMARY KEY,
nome VARCHAR (50) NOT NULL,
email VARCHAR(50) NOT NULL,
status_cadastro VARCHAR (20) NOT NULL,
telefone VARCHAR(12),
data_criacao TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS solucionador(
id SERIAL PRIMARY KEY,
nome VARCHAR (50) NOT NULL,
email VARCHAR(50) NOT NULL,
lattes VARCHAR (50) NOT NULL,
status_cadastro VARCHAR (20) NOT NULL,
telefone VARCHAR (12),
data_criacao TIMESTAMP NOT NULL DEFAULT NOW()

);

CREATE TABLE IF NOT EXISTS tag(
id SERIAL PRIMARY KEY,
nome VARCHAR (50) NOT NULL,
data_criacao TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS tag_solucionador(
id_tag INTEGER,
id_solucionador INTEGER,
constraint chave_primaria_tag_solucionador primary key (id_tag, id_solucionador),
CONSTRAINT FK_id_tag FOREIGN KEY (id_tag)
REFERENCES tag (id),
CONSTRAINT FK_id_solucionador FOREIGN KEY (id_solucionador)
REFERENCES tag (id),
data_criacao TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS tag_cliente(
id_tag INTEGER,
id_cliente INTEGER,
constraint chave_primaria_tag_cliente primary key (id_tag, id_cliente),
CONSTRAINT FK_id_tag FOREIGN KEY (id_tag)
REFERENCES tag (id),
CONSTRAINT FK_id_cliente FOREIGN KEY (id_cliente)
REFERENCES cliente (id),
data_criacao TIMESTAMP NOT NULL DEFAULT NOW()
);