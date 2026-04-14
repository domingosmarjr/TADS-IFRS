DROP DATABASE IF EXISTS manutencao2;

CREATE DATABASE manutencao2;

\c manutencao2;

CREATE OR REPLACE FUNCTION validaEmail(email character varying(200)) RETURNS boolean AS
$$
    --DECLARE
    BEGIN
        --Print de Debug
        RAISE NOTICE '%', email;

        IF (POSITION('@' IN email) != 0) THEN
            RETURN TRUE;
        END IF;
        RETURN FALSE;
    END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION geradorSenha() RETURNS text AS
$$
    DECLARE
        i integer := 0;
        alfabeto text[] = ARRAY['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','r','s','t','u','v','w','x','y','z'];
        senha text := '';
    BEGIN
        WHILE (i < 32) LOOP
            senha := senha || alfabeto[cast(random() * 25 + 1 as integer)];
            RAISE NOTICE '%', senha;
            i := i + 1;
        END LOOP;
        RETURN trim(senha);
    END;
$$ LANGUAGE 'plpgsql';





CREATE TABLE usuario (
    id serial primary key,
    email character varying(200) UNIQUE CHECK (validaEmail(email) IS TRUE),
    nome character varying(200) NOT NULL,
    senha character varying(200) NOT NULL,
);

INSERT INTO usuario (nome, email, senha) VALUES
('IGOR PEREIRA', 'igor.pereira@riogrande.ifrs.edu.br', md5('123')),
('JAAZIEL FERNANDES', 'jaaziel@bol.com', md5('123'));





CREATE TABLE equipamento (
    id serial primary key,
    descricao text NOT NULL
);

INSERT INTO equipamento (descricao) VALUES
('PROJETOR MULTIMIDIA');





CREATE TABLE servico (
    id serial primary key,
    descricao text not null,
    titulo text not null,
    data_hora_criacao timestamp default current_timestamp,
    finalizado timestamp,
    criador_id integer references usuario(id),
    responsavel_id integer references usuario(id)
);

INSERT INTO servico (titulo, descricao, criador_id, responsavel_id) VALUES
('PROJETOR NÃO LIGA', 'PROJETO NÃO LIGA DEVIDA A ALGUMA COISA', 1, 1);





CREATE TABLE status (
    id serial primary key,
    servido_id integer references servico(id),
    situacao text not null,
    data_hora timestamp default current_timestamp
);

INSERT INTO status (situacao, servico_id) VALUES
('TENTEI RESOLVER E NÃO DEU', 1);
