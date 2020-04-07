#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 --username "postgres" --dbname "incluitech" <<-EOSQL
    GRANT ALL PRIVILEGES ON DATABASE "incluitech" TO "postgres";
    CREATE TABLE IF NOT EXISTS usuario(
    id SERIAL PRIMARY KEY,
    nome VARCHAR (50) NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT NOW());
EOSQL