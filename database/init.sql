GRANT ALL PRIVILEGES ON DATABASE "incluitech" TO "postgres";

create table if not exists usuario(
    id serial primary key,
    nome varchar (50) not null,
    data_criacao timestamp not null default now()
);

create table if not exists cliente(
    id serial primary key,
    nome varchar (50) not null,
    sobrenome varchar(50) not null,
    email varchar(50) not null,
    status_cadastro varchar(1) not null,
    especialidades varchar(10),
    data_criacao timestamp not null default now()
);