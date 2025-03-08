-- Trabalho 2 PMDB
-- Domingos Martins Jr. (2023017853)

DROP DATABASE IF EXISTS parque;

CREATE DATABASE parque;

\c parque;

CREATE TABLE visitante (
	id serial PRIMARY KEY,
	nome character varying(50) NOT NULL,
	email character varying(30) UNIQUE NOT NULL,
	datanascimento date NOT NULL
);

CREATE TABLE atracao (
	id serial PRIMARY KEY,
	nome character varying(50) NOT NULL,
	tipo character  varying(20) NOT NULL,
	capacidade integer NOT NULL
	-- A capacidade, considerei o número de pessoas, por isso int.
);

CREATE TABLE funcionario (
	id serial PRIMARY KEY,
	nome character varying(50) NOT NULL,
	salario money NOT NULL,
	cargo character varying(30) NOT NULL,
	atracao_id integer NOT NULL REFERENCES atracao(id)
);

CREATE TABLE ingresso (
	id serial PRIMARY KEY,
	datavisita timestamp NOT NULL,
	visitante_id integer NOT NULL REFERENCES visitante(id),
	atracao_id integer NOT NULL REFERENCES atracao(id)
	-- datavisita pensei em colocar 'current_date', 
	-- mas deixei na escolha do insert definir a data e hora
	-- pois não há especificação de que data se quer (quando realiza a compra, ou a do evento do ingresso em si)
);

-- 1)
INSERT INTO visitante (nome, email, datanascimento) VALUES ('Batman da Silva', 'batman@gotham.com', '01/03/1960');
INSERT INTO visitante (nome, email, datanascimento) VALUES ('Coringa das Neves', 'coringa@gotham.com', '11/10/1965');
INSERT INTO visitante (nome, email, datanascimento) VALUES ('Robin José', 'robinzinho@gotham.com', '08/09/1996');
INSERT INTO visitante (nome, email, datanascimento) VALUES ('Duas Caras', 'carudo@gotham.com', '08/09/2005');

INSERT INTO atracao (nome, tipo, capacidade) VALUES ('Bate-Bate de Arkham', 'Adrenalina', '5');
INSERT INTO atracao (nome, tipo, capacidade) VALUES ('Racha em Gotham', 'Corrida', '2');
INSERT INTO atracao (nome, tipo, capacidade) VALUES ('Jardim da Hera Venenosa', 'Passeio', '10');

INSERT INTO ingresso (datavisita, visitante_id, atracao_id) VALUES ('09/12/2024 10:00:02', '1', '3');
INSERT INTO ingresso (datavisita, visitante_id, atracao_id) VALUES ('09/12/2024 10:00:01', '3', '3');
INSERT INTO ingresso (datavisita, visitante_id, atracao_id) VALUES ('10/12/2024 14:25:10', '2', '2');
INSERT INTO ingresso (datavisita, visitante_id, atracao_id) VALUES ('10/12/2024 14:25:13', '1', '2');
INSERT INTO ingresso (datavisita, visitante_id, atracao_id) VALUES ('11/12/2024 19:38:00', '2', '1');
INSERT INTO ingresso (datavisita, visitante_id, atracao_id) VALUES ('01/01/2025 12:34:22', '4', '1');

INSERT INTO funcionario (nome, salario, cargo, atracao_id) VALUES ('Alfred', '7500', 'Supervisor', '1');
INSERT INTO funcionario (nome, salario, cargo, atracao_id) VALUES ('Charada', '1500', 'Monitor', '1');
INSERT INTO funcionario (nome, salario, cargo, atracao_id) VALUES ('Bane', '2000', 'Supervisor', '2');
INSERT INTO funcionario (nome, salario, cargo, atracao_id) VALUES ('Pinguim', '3000', 'Jardineiro', '3');

-- 2)
SELECT * FROM visitante;
SELECT * FROM atracao;

-- 3)
SELECT visitante.nome AS nome_visitante FROM ingresso JOIN visitante ON ingresso.visitante_id = visitante.id WHERE ingresso.atracao_id = '1';
SELECT visitante.nome AS nome_visitante FROM ingresso JOIN visitante ON ingresso.visitante_id = visitante.id WHERE ingresso.atracao_id = '2';
SELECT visitante.nome AS nome_visitante FROM ingresso JOIN visitante ON ingresso.visitante_id = visitante.id WHERE ingresso.atracao_id = '3';
SELECT * FROM funcionario WHERE salario > '3000';

-- 4)
SELECT * FROM visitante ORDER BY nome ASC;
SELECT * FROM atracao ORDER BY capacidade DESC;

-- 5)
SELECT AVG(salario::numeric::double precision) AS mediasalarial FROM funcionario;
SELECT datavisita, visitante.nome AS nome_visitante FROM ingresso JOIN visitante ON ingresso.visitante_id = visitante.id ORDER BY datavisita DESC FETCH FIRST 1 ROW ONLY;

-- 6)
SELECT nome, EXTRACT(YEAR FROM datanascimento) AS ano_nascimento FROM visitante WHERE EXTRACT (YEAR FROM datanascimento) < '2000';
SELECT nome, age(CURRENT_DATE, datanascimento) AS idade FROM visitante;
