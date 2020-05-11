-- CLIENTE
INSERT INTO cliente (nome, email, status_cadastro, telefone, facebook_id) VALUES ('João das Neves', 'joao@email.com', 'A', '051999992222', '1');
INSERT INTO cliente (nome, email, status_cadastro, telefone, facebook_id) VALUES ('Pedro Pedroca', 'pedro@email.com', 'A', '062999993333', '2');
INSERT INTO cliente (nome, email, status_cadastro, telefone, facebook_id) VALUES ('Isabela a Bela', 'isabela@email.com', 'P', '051988884444', '3');
INSERT INTO cliente (nome, email, status_cadastro, telefone, facebook_id) VALUES ('Letícia de la Notícia', 'leticia@email.com', 'P', '062977775555', '4');
INSERT INTO cliente (nome, email, status_cadastro, telefone, facebook_id) VALUES ('Paulo Aristóteles', 'paulo@email.com', 'R', '051966667777', '5');

-- SOLUCIONADOR
INSERT INTO solucionador (nome, email, lattes, status_cadastro, telefone, facebook_id) VALUES ('Alan Turing', 'alanturing@email.com', 'http://lattes.cnpq.br/', 'A', '051999999999', '1');
INSERT INTO solucionador (nome, email, lattes, status_cadastro, telefone, facebook_id) VALUES ('Ada Lovelace', 'adalovelace@email.com', 'http://lattes.cnpq.br/', 'A', '062999999999', '2');
INSERT INTO solucionador (nome, email, lattes, status_cadastro, telefone, facebook_id) VALUES ('Margaret Hamilton', 'margarethamilton@email.com', 'http://lattes.cnpq.br/', 'P', '051988888888', '3');
INSERT INTO solucionador (nome, email, lattes, status_cadastro, telefone, facebook_id) VALUES ('Grady Booch', 'gradybooch@email.com', 'http://lattes.cnpq.br/', 'P', '062977777777', '4');
INSERT INTO solucionador (nome, email, lattes, status_cadastro, telefone, facebook_id) VALUES ('Darth Vader', 'darthvader@email.com', 'http://lattes.cnpq.br/', 'R', '051966666666', '5');

-- TAG
INSERT INTO tag (nome) VALUES ('CRIANÇAS');
INSERT INTO tag (nome) VALUES ('TDAH');
INSERT INTO tag (nome) VALUES ('ADULTOS');
INSERT INTO tag (nome) VALUES ('EMPRESA');
INSERT INTO tag (nome) VALUES ('ESCOLA');
INSERT INTO tag (nome) VALUES ('ALFABETIZAÇÃO');
INSERT INTO tag (nome) VALUES ('ONG');
INSERT INTO tag (nome) VALUES ('DOAÇÃO');
INSERT INTO tag (nome) VALUES ('PSICOLOGIA');
INSERT INTO tag (nome) VALUES ('DEPRESSÃO');
INSERT INTO tag (nome) VALUES ('ORFANATO');
INSERT INTO tag (nome) VALUES ('ASSISTÊNCIA SOCIAL');
INSERT INTO tag (nome) VALUES ('SAÚDE');
INSERT INTO tag (nome) VALUES ('HOSPITAL');
INSERT INTO tag (nome) VALUES ('EMPREGO');
INSERT INTO tag (nome) VALUES ('MERCADO DE TRABALHO');
INSERT INTO tag (nome) VALUES ('DIREITO');
INSERT INTO tag (nome) VALUES ('APOSENTADORIA');
INSERT INTO tag (nome) VALUES ('MEDITAÇÃO');
INSERT INTO tag (nome) VALUES ('ARTES MARCIAIS');

-- ASSOCIAÇÃO TAG COM CLIENTE
INSERT INTO tag_cliente (id_tag, id_cliente) VALUES (1, 1);
INSERT INTO tag_cliente (id_tag, id_cliente) VALUES (2, 1);
INSERT INTO tag_cliente (id_tag, id_cliente) VALUES (3, 2);
INSERT INTO tag_cliente (id_tag, id_cliente) VALUES (4, 2);
INSERT INTO tag_cliente (id_tag, id_cliente) VALUES (5, 3);
INSERT INTO tag_cliente (id_tag, id_cliente) VALUES (6, 3);
INSERT INTO tag_cliente (id_tag, id_cliente) VALUES (7, 4);
INSERT INTO tag_cliente (id_tag, id_cliente) VALUES (8, 4);
INSERT INTO tag_cliente (id_tag, id_cliente) VALUES (9, 5);
INSERT INTO tag_cliente (id_tag, id_cliente) VALUES (10, 5);

-- ASSOCIAÇÃO TAG COM SOLUCIONADOR
INSERT INTO tag_solucionador (id_tag, tag_solucionador) VALUES (11, 1);
INSERT INTO tag_solucionador (id_tag, tag_solucionador) VALUES (12, 1);
INSERT INTO tag_solucionador (id_tag, tag_solucionador) VALUES (13, 2);
INSERT INTO tag_solucionador (id_tag, tag_solucionador) VALUES (14, 2);
INSERT INTO tag_solucionador (id_tag, tag_solucionador) VALUES (15, 3);
INSERT INTO tag_solucionador (id_tag, tag_solucionador) VALUES (16, 3);
INSERT INTO tag_solucionador (id_tag, tag_solucionador) VALUES (17, 4);
INSERT INTO tag_solucionador (id_tag, tag_solucionador) VALUES (18, 4);
INSERT INTO tag_solucionador (id_tag, tag_solucionador) VALUES (19, 5);
INSERT INTO tag_solucionador (id_tag, tag_solucionador) VALUES (20, 5);



