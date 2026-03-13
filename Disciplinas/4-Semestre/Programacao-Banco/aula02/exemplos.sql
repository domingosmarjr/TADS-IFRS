-- ====================================================
-- PROCEDIMENTOS
CREATE OR REPLACE PROCEDURE cadastra_usuario(p_nome text, p_email) AS
$$
DECLARE
    senha character varying(200) := geradorSenha(); --implementar gerador
BEGIN
    RAISE NOTICE '%', p_senha; --print na tela
    INSERT INTO usuario(nome, email, senha) VALUES
    (p_nome, p_email, md5());
END
$$ LANGUAGE 'plpsql';

-- CHAMAR PROCEDIMENTO
CALL cadastra_usuario('luciano', 'luciano.vargas@riogrande.ifrs.edu.br');

-- ====================================================
-- EXEMPLO DE FUNÇÃO [LISTAR USUÁRIOS, QUE RETORNA TABELA]
CREATE OR REPLACE FUNCTION list_usuarios() RETURNS TABLE (
    p_id integer,
    p_email character varying(200),
    p_nome character varying(200),
    p_senha character varying(200)
) AS
$$
BEGIN
    RETURN QUERY select id, email, nome, senha from usuario;
END
$$ LANGUAGE 'plpsql';

-- CHAMANDO A FUNÇÃO
SELECT * FROM list_usuarios();
SELECT p_nome FROM list_usuarios();


-- ====================================================
