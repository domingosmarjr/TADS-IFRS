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

CREATE OR REPLACE PROCEDURE cadastra_usuario(p_nome text, text p_email text) AS 
$$
DECLARE
    p_senha character varying(200) := geradorSenha();
BEGIN
    RAISE NOTICE '%', p_senha;
    INSERT INTO usuario(nome, email, senha) VALUES (p_nome, p_email, md5(p_senha));
END;
$$ LANGUAGE 'plpgsql';

CALL cadastra_usuario ('luciano', 'luciano@ifrs.riogrande.br');

CREATE OR REPLACE PROCEDURE cadastra_usuario(p_nome text, p_email text, p_senha character varying (200)) AS
$$
BEGIN
    RAISE NOTICE '%', p_senha;
    INSERT INTO usuario (nome, email, senha) VALUES (p_nome, p_email, md5(p_senha));
END;
$$ LANGUAGE 'plpgsql';

CALL cadastra_usuario ('luciano', 'luciano@ifrs.riogrande.br', '123');

DROP FUNCTION list_usuarios();
CREATE OR REPLACE FUNCTION list_usuarios() RETURNS TABLE (
    p_id integer,
    p_email character varying (200),
    p_nome character varying (200),
    p_senha character varying (200)
) AS
$$
BEGIN
    RETURN QUERY SELECT id, email, nome, senha FROM usuario;
END

CREATE OR REPLACE FUNCTION busque_usuario_por_email(parametro_email character varying(200)) RETURNS TABLE (
    p_id integer,
    p_email character varying(200),
    p_nome character varying(200),
    p_senha character varying(200)
) AS
$$
BEGIN
    RETURN QUERY SELECT id, email, nome, senha FROM usuario WHERE email = trim(parametro_email);
END;
$$ LANGUAGE 'plpgsql';


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
    servico_id integer references servico(id),
    situacao text not null,
    data_hora timestamp default current_timestamp
);

INSERT INTO status (situacao, servico_id) VALUES
('TENTEI RESOLVER E NÃO DEU', 1);

CREATE OR REPLACE FUNCTION retorna_usuario_nome (p_id integer) RETURNS TEXT AS
$$
DECLARE
    p_nome TEXT := NULL;
BEGIN
    SELECT nome FROM usuario where id = p_id INTO p_nome;
    RETURN p_nome;
END
$$ LANGUAGE 'plpgsql';

DROP FUNCTION alterar_senha;

CREATE OR REPLACE FUNCTION alterar_senha(p_email character varying(200), p_nova_senha character varying(200)) RETURNS BOOLEAN AS
$$
BEGIN
    BEGIN
        UPDATE usuario SET senha = md5(p_nova_senha) WHERE email = p_email;
        RETURN TRUE;
    EXCEPTION
        WHEN OTHERS THEN RAISE 'Deu xabum';
        RETURN FALSE;
    END;
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE PROCEDURE cadastrar_equipamento(p_descricao text) AS
$$
BEGIN
    INSERT INTO equipamento (descricao) VALUES (p_descricao);
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION listar_equipamentos() RETURNS TABLE (
    p_id integer;
    p_descricao text
);
$$
BEGIN
    RETURN QUERY select * from equipamentos;
END;
$$ LANGUAGE 'plpgsql';




CREATE OR REPLACE FUNCTION listar_equipamentos_default (p1 integer DEFAULT 0) RETURNS TABLE (
    p_id integer,
    p_descricao text
) AS
$$
BEGIN
    IF (p1 = 0) THEN
        RETURN QUERY SELECT * FROM equipamento;
    ELSE
        RETURN QUERY SELECT * FROM equipamento WHERE id = p1;
    END IF;
END;
$$ LANGUAGE 'plpgsql';





CREATE OR REPLACE PROCEDURE atualizar_equipamento(p_id integer, p_descricao text) AS
$$
BEGIN
    UPDATE equipamento SET descricao = p_descricao WHERE id = p_id;
END;
$$ LANGUAGE 'plpgsql';



ALTER TABLE equipamento ADD COLUMN ativo boolean DEFAULT true;


CREATE OR REPLACE PROCEDURE remover_equipamento (p_id integer) AS
$$
BEGIN
    IF (EXISTS (SELECT * FROM EQUIPAMENTO WHERE id = p_id)) THEN
        UPDATE equipamento SET ativo = FALSE where id = p_id;
    ELSE
        RAISE NOTICE 'Não existe.';
    END IF;
END;
$$ LANGUAGE 'plpgsql';



ALTER TABLE usuario ADD COLUMN ativo BOOLEAN DEFAULT TRUE;

CREATE OR REPLACE FUNCTION novo_servico(p_titulo TEXT, p_descricao TEXT, p_criador_id integer, p_responsavel_id integer, default 0) RETURNS BOOLEAN AS
$$
BEGIN
    IF (p_responsavel_id != 0) THEN
        IF (EXISTS (SELECT * FROM usuario WHERE id = p_responsavel_id) THEN)
            IF (EXISTS (SELECT * FROM usuario WHERE id = p_criador_id) THEN)
                INSERT INTO servico (titulo, descricao, criador_id, responsavel_id) VALUES (p_titulo, p_descricao, p_criador_id, p_responsavel_id);
                RETURN TRUE;
            ELSE
                RAISE NOTICE 'id criador inválido.';
                RETURN FALSE;
            END IF;
        ELSE
            RAISE NOTICE 'id responsável inválido.';
            RETURN FALSE;
        END IF;
    ELSE
        p_responsavel_id := p_criador_id;
        IF (EXISTS(SELECT * FROM usuario WHERE id = p_responsavel_id)) THEN
            IF (EXISTS(SELECT * FROM usuario WHERE id = p_criador_id)) THEN
                INSERT INTO servico (titulo, descricao, criador_id, responsavel_id) VALUES (p_titulo, p_descricao, p_criador_id, p_responsavel_id);
                RETURN TRUE;
            ELSE
                RAISE NOTICE 'id criador inválido.';
                RETURN FALSE;
            END IF;
        ELSE
            RAISE NOTICE 'id responsável inválido.';
            RETURN FALSE;
        END IF;
    END IF;
    RETURN FALSE;
END;
$$ LANGUAGE 'plpgsql';



CREATE OR REPLACE FUNCTION listar_servicos_nao_finalizados() RETURNS TABLE (
    p_id integer,
    p_descricao text
) AS
$$
BEGIN
    RETURN QUERY SELECT * FROM servico WHERE finalizado IS NULL ORDER BY id;
END;
$$ LANGUAGE 'plpgsql';

