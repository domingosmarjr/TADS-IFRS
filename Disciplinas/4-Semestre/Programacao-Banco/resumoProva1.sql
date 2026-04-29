-- PROCEDURE
-- Não precisa retornar um valor
-- Executa ações (inserir, atualizar, deletar, lógica)
-- USO: CALL minha_procedure(10);
-- NÃO PODE USAR SELECT!
-- Pode controlar transações (begin, commit, rollback)
CREATE OR REPLACE PROCEDURE aumentar_salario(id_funcionario int, aumento numeric) AS
$$
BEGIN
    UPDATE funcionarios SET salario = salario + aumento WHERE id = id_funcionario;
END;
$$ LANGUAGE 'plpgsql';

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


X -> SELECT aumentar_salario(1, 300);
O -> CALL aumentar_salario(1, 300);

-- ===========================================================================
-- FUNCTION
-- Sempre retorna um valor
-- Retorna valor simples(text, integer)
-- Retorna tabela (RETURNS TABLE)
-- Retorna RECORD
-- USO: SELECT nome, minha_funcao(10) FROM funcionario;
CREATE FUNCTION soma(a int, b int)
RETURNS int
$$
BEGIN
    RETURN a + b;
END;
$$ LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION retorna_usuario_nome (p_id integer) RETURNS TEXT AS
$$
DECLARE
    p_nome TEXT := NULL;
BEGIN
    SELECT nome FROM usuario where id = p_id INTO p_nome;
    RETURN p_nome;
END
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


-- =====================================================
-- %TYPE
-- Pega o tipo de uma coluna
DECLARE v_salario func.salarios%TYPE;
-- Se func.salarios for numeric, v_salario será numeric

CREATE FUNCTION buscar_salario (p_id int) RETURNS numeric
AS
$$
DECLARE
    v_salario func.salarios%TYPE;
BEGIN
    SELECT salario INTO v_salario FROM funcionarios WHERE id = p_id;
    RETURN v_salario;
END;
$$ LANGUAGE 'plpgsql';

-- ====================================================
-- %ROWTYPE
-- Pega a estrutura inteira da tabela
DECLARE v_funcionario func%ROWTYPE;
-- v_funcionario contem id, nome, salario, ...
-- Ele é um objeto com vários campos!
-- Select é diferente = SELECT * INTO v_func FROM funcionarios;

CREATE FUNCTION buscar_funcionario (p_id int) RETURNS text AS
$$
DECLARE
    v_func funcionario%ROWTYPE;
BEGIN
    SELECT * INTO v_func FROM funcionarios WHERE id = p_id;
    RETURN v_func.nome;
END;
$$ LANGUAGE 'plpgsql';


-- =================================================
-- LOOP
CREATE FUNCTION contar_ate_5() RETURNS VOID AS
$$
DECLARE
    i int := 1;
BEGIN
    LOOP
        RAISE NOTICE 'Valor: %',i;
        i := i + 1;

        EXIT WHEN i > 5;
    END LOOP;
END;
$$ LANGUAGE 'plpgsql';

CREATE FUNCTION exemplo_while() RETURNS VOID AS
$$
DECLARE
    i int := 1;
BEGIN
    WHILE i <= 5 LOOP
        RAISE NOTICE 'Número %', i;
        i := i + 1;
    END LOOP;
END;
$$ LANGUAGE 'plpgsql';

CREATE FUNCTION exemplo_for() RETURNS VOID AS
$$
BEGIN
    FOR i IN 1..5 LOOP
        RAISE NOTICE 'Número %', i;
    END LOOP;
END;
$$ LANGUAGE 'plpgsql';
-- i já é criado automaticamente


CREATE FUNCTION listar_funcionarios() RETURNS VOID AS 
$$
DECLARE
    v_func funcionarios%ROWTYPE;
BEGIN
    FOR v_func IN SELECT * FROM funcionarios LOOP
        RAISE NOTICE 'Nome: %', v_func.nome;
    END LOOP;
END;
$$ LANGUAGE plpgsql;