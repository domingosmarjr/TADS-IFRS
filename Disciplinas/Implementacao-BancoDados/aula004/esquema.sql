-- SCHEMAS
--
-- Posso dar nome aos esquemas!
-- Funcionalidade de esquemas é organização das tabelas!
-- "Zezinho só mexe nas tabelas x, Ciclano mexe nas tabelas y"

DROP DATABASE IF EXISTS trabalho1;
CREATE DATABASE trabalho1;
\c trabalho1;

CREATE SCHEMA gerenciamento_conta;
-- Quais esquemas possíveis pro meu banco?
-- O próprio público e o esquema recem criado.
SET search_path TO public, gerenciamento_conta;

CREATE TABLE gerenciamento_conta.usuario(
    id serial PRIMARY KEY,
    nome character varying(100) NOT NULL,
    email character varying(100) NOT NULL UNIQUE,
    senha character varying(100) NOT NULL,
    data_nascimento date CHECK (extract(year from data_nascimento) >= 1900)
);

CREATE TABLE gerenciamento_conta.conta(
    id serial PRIMARY KEY,
    usuario_id integer REFERENCES usuario(id),
    data_hora_criacao timestamp DEFAULT current_timestamp,
    nome_usuario text UNIQUE NOT NULL
);

CREATE TABLE publicacao (
    id serial PRIMARY KEY,
    data_hora timestamp DEFAULT current_timestamp,
    texto text,
    arquivo_principal text NOT NULL,
    latitude real
);

CREATE TABLE conta_publicacao (
    publicacao_id integer REFERENCES publicacao(id),
    conta_id integer REFERENCES conta(id),
    PRIMARY KEY (publicacao_id, conta_id)
);


CREATE TABLE comentario (
    id serial PRIMARY KEY,
    data_hora timestamp DEFAULT current_timestamp,
    publicacao_id integer REFERENCES publicacao(id)
);

INSERT INTO gerenciamento_conta.usuario (nome, email, senha, data_nascimento) VALUES
('IGOR PEREIRA', 'igor.pereira@riogrande.ifrs.edu.br','ifrs123','1977-01-20');

INSERT INTO gerenciamento_conta.conta (usuario_id, nome_usuario) VALUES 
('1','@igoravilapereira');
INSERT INTO gerenciamento_conta.conta (usuario_id, nome_usuario) VALUES
('1','@igor_guitarhero');


INSERT INTO publicacao (texto, arquivo_principal) VALUES
('Minhas férias no Caribe','minha_foto_no_caribe.jpeg');
INSERT INTO publicacao (texto, arquivo_principal) VALUES
('Aprendendo Pentatônicas','licao1.avi');

INSERT INTO conta_publicacao VALUES
('1','1');
INSERT INTO conta_publicacao VALUES
('1','2');

-- ESQUEMAS APARECEM NO DESCRIBE-TABLES (\dt)
--                      List of relations
--        Schema        |       Name       | Type  |  Owner   
-- ---------------------+------------------+-------+----------
--  gerenciamento_conta | conta            | table | postgres
--  gerenciamento_conta | usuario          | table | postgres
--  public              | comentario       | table | postgres
--  public              | conta_publicacao | table | postgres
--  public              | publicacao       | table | postgres


-- VIEW
-- 
-- Salvar um tipo de Select como um comando presset!

-- contar contas de um usuário! usuário e conta (tabelas diferentes = inner join)
-- usar GROUP BY para agrupar
SELECT usuario.id, usuario.nome, count(*) AS qnt_contas FROM usuario
INNER JOIN conta ON usuario.id = conta.usuario_id
GROUP BY usuario.id, usuario.nome;

CREATE VIEW qtde_conta_por_usuario AS 
SELECT usuario.id, usuario.nome, count(*) AS qnt_contas FROM usuario
INNER JOIN conta ON usuario.id = conta.usuario_id
GROUP BY usuario.id, usuario.nome;

-- Ver a view (e a view é select)
SELECT * FROM qtde_conta_por_usuario;

-- Describe View (\dv)
\dv;

