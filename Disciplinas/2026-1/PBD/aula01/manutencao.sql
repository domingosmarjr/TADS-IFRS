DROP DATABASE IF EXISTS manutencao;

CREATE DATABASE manutencao;

\c manutencao;

CREATE TABLE usuario (
    id serial PRIMARY KEY,
    email character varying(200) unique,
    nome characteCREATE OR REPLACE FUNCTION geradorSenha() RETURNS character varying(200) AS
$$
DECLARE 
    i integer := 0; -- := -> ATRIBUIÇÃO EM PROGRAMAÇÃO BANCO;
    alfabeto text[] := ARRAY ['a','b','c','d','e','f','g','h','i','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z']; 
    senha_gerada text[];
BEGIN
    while (i < 32) loop
        senha_gerada[i] := alfabeto[cast(random()*26 as integer)];
        i := i + 1;
    end loop;
    return array_to_string(senha_gerada;
END;
$$ LANGUAGE 'plpgsql';r varying(200) not null,
    senha character varying(200) not null
);

CREATE TABLE equipamento(
    id serial primary key,
    descricao text not null
);

CREATE TABLE servico (
    id serial primary key,
    descricao text not null,
    titulo text not null,
    data_hora_criacao timestamp default current_timestamp,
    finalizado timestamp,
    criador_id integer references usuario(id),
    responsavel_id integer references usuario(id)
);

CREATE TABLE status (
    id serial PRIMARY KEY,
    servico_id integer references servico(id),
    situacao text not null,
    data_hora timestamp default current_timestamp
);


INSERT INTO usuario (nome, email, senha) VALUES
('IGOR PEREIRA','igor.pereira@riogrande.br', md5('123'));

INSERT INTO servico (titulo, descricao, criador_id, responsavel_id) VALUES
('PROJETOR NÃO LIGA', 'PROJETOR NÃO LIGA DEVIDO A ALGUMA COISA', 1, 1);

INSERT INTO status (situacao, servico_id) VALUES
('TENTEI RESOLVER E NÃO DEU!', 1);


-- CRIAÇÃO DE FUNÇÃO EM BANCO
CREATE OR REPLACE FUNCTION validaEmail (email character varying(200)) RETURNS boolean AS
$$
DECLARE
BEGIN
    IF (POSITION('@' IN email) != 0) THEN
        RETURN TRUE;
    END IF
    RETURN FALSE;
END;
$$ LANGUAGE 'plpgsql';

-- CHAMAR FUNÇÃO
SELECT validaEmail('igor'); -- FALSE 
SELECT validaEMail('igor@live.com'); -- TRUE

-- Adicionar ela como constraint da tabela 
-- check validaEmail() IS true;

CREATE OR REPLACE FUNCTION geradorSenha() RETURNS character varying(200) AS
$$
DECLARE 
    i integer := 0; -- := -> ATRIBUIÇÃO EM PROGRAMAÇÃO BANCO;
    alfabeto text[] := ARRAY ['a','b','c','d','e','f','g','h','i','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z']; 
    senha_gerada text[];
    senha character varying(32);
BEGIN
    while (i < 32) loop
        senha_gerada[i] := senha || alfabeto[cast(random()*25 + 1 as integer)];
        i := i + 1;
    end loop;
    return trim(senha_gerada);
END;
$$ LANGUAGE 'plpgsql';

