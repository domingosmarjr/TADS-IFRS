-- =========================== CRIAÇÃO BANCO ===========================
DROP DATABASE IF EXISTS manutencao;

CREATE DATABASE manutencao;

\c manutencao;

-- =========================== FUNÇÕES BASE ===========================
CREATE OR REPLACE FUNCTION validaEmail(email character varying(200)) RETURNS BOOLEAN AS
$$
DECLARE
BEGIN
    RAISE NOTICE '%', email;
    IF (POSITION('@' IN email) != 0) THEN
        RETURN TRUE;
    END IF;
    RETURN FALSE;
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION geradorSenha() RETURNS TEXT AS
$$
DECLARE
    i integer := 0;
    alfabeto text[] := ARRAY['a','b','c','d','e','f','g','h','i','j','k','l','m',
    'n','o','p','q','r','s','t','u','v','w','x','y','z'];
    senha text := '';
BEGIN
    WHILE(i < 32) LOOP
        senha := senha || alfabeto[floor(random() * 26 + 1)];
        i := i + 1;
    END LOOP;
    RETURN trim(senha);
END;
$$ LANGUAGE 'plpgsql';

-- =========================== TABELAS ===========================
CREATE TABLE usuario (
    id serial PRIMARY KEY,
    email character varying(200) UNIQUE CHECK( validaEmail(email) IS TRUE),
    nome character varying(200) NOT NULL,
    senha character varying(200) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE equipamento (
    id serial PRIMARY KEY,
    descricao text NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE servico (
    id serial PRIMARY KEY,
    descricao text NOT NULL,
    titulo text NOT NULL,
    data_hora_criacao timestamp DEFAULT current_timestamp,
    finalizado timestamp,
    criador_id integer REFERENCES usuario(id),
    responsavel_id integer REFERENCES usuario(id),
    equipamento_id integer REFERENCES equipamento(id)
);

CREATE TABLE status (
    id serial PRIMARY KEY,
    servico_id integer REFERENCES servico(id),
    situacao text NOT NULL,
    data_hora timestamp DEFAULT current_timestamp,
    responsavel_id integer REFERENCES usuario(id),
    criador_id integer REFERENCES usuario(id)
);









-- =========================== FUNÇÕES DA LISTA ===========================
-- ============ USUÁRIO ============
-- 1) CADASTRAR NOVO USUÁRIO
CREATE OR REPLACE PROCEDURE cadastra_usuario (p_nome text, p_email text) AS
$$
DECLARE
    p_senha character varying(200) := geradorSenha();
BEGIN
    RAISE NOTICE '%', p_senha;
    INSERT INTO usuario (nome, email, senha) VALUES (p_nome, p_email, md5(p_senha));
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE PROCEDURE cadastra_usuario (p_nome text, p_email text, p_senha character varying(200)) AS
$$
BEGIN
    RAISE NOTICE '%', p_senha;
    INSERT INTO usuario (nome, email, senha) VALUES (p_nome, p_email, md5(p_senha));
END;
$$ LANGUAGE 'plpgsql';

-- 2) LISTAR TODOS USUÁRIOS CADASTRADOS
DROP FUNCTION IF EXISTS list_usuarios();
CREATE OR REPLACE FUNCTION list_usuarios() RETURNS TABLE (
    p_id integer,
    p_email character varying(200),
    p_nome character varying(200),
    p_senha character varying(200)
) AS
$$
BEGIN
    RETURN QUERY SELECT id, email, nome, senha FROM usuario;
END;
$$ LANGUAGE 'plpgsql';

-- 3) BUSCAR USUÁRIO POR EMAIL
CREATE OR REPLACE FUNCTION busque_usuario_por_email (parametro_email character varying(200)) RETURNS TABLE (
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

-- 4)RETORNA NOME DO USUÁRIO POR ID
CREATE OR REPLACE FUNCTION retorna_usuario_nome(p_id integer) RETURNS TEXT AS
$$
DECLARE
    p_nome text := NULL;
BEGIN
    SELECT nome FROM usuario WHERE id = p_id INTO p_nome;
    RETURN p_nome;
END;
$$ LANGUAGE 'plpgsql';

-- 5) ALTERAR SENHA DE USUÁRIO
CREATE OR REPLACE FUNCTION alterar_senha(p_email character varying(200), p_nova_senha character varying(200)) RETURNS BOOLEAN AS
$$
BEGIN
    BEGIN
        UPDATE usuario SET senha = md5(p_nova_senha) WHERE email = p_email;
        RETURN TRUE;
    EXCEPTION
        WHEN OTHER THEN RAISE NOTICE 'Deu xabum!';
        RETURN FALSE;
    END;
END;
$$ LANGUAGE 'plpgsql';

-- ============ EQUIPAMENTOS ============
-- 6) CADASTRAR EQUIPAMENTO
CREATE OR REPLACE PROCEDURE cadastrar_equipamento(p_descricao text) AS
$$
BEGIN
    INSERT INTO equipamento(descricao) VALUES (p_descricao);
END;
$$ LANGUAGE 'plpgsql';

-- 7) LISTAR TODOS EQUIPAMENTOS
CREATE OR REPLACE FUNCTION listar_equipamentos() RETURNS TABLE (
    p_id integer,
    p_descricao text,
    p_ativo boolean
) AS
$$
BEGIN
    RETURN QUERY SELECT id, descricao, ativo FROM equipamento;
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION listar_equipamentos_default(p1 integer DEFAULT 0) RETURNS TABLE (
    p_id integer,
    p_descricao text,
    p_ativo boolean
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

-- 8) BUSCAR UM EQUIPAMENTO PELO ID
CREATE OR REPLACE FUNCTION buscar_equipamento_por_id(p_id integer) RETURNS TABLE (
    id integer,
    descricao text,
    ativo boolean
) AS
$$
BEGIN
    RETURN QUERY SELECT id, descricao, ativo FROM equipamento WHERE id = p_id;
END;
$$ LANGUAGE 'plpgsql';

-- 9) ATUALIZAR DESCRIÇÃO DO EQUIPAMENTO
CREATE OR REPLACE PROCEDURE atualizar_equipamento(p_id integer, p_descricao text) AS
$$
BEGIN
    UPDATE equipamento SET descricao = p_descricao where id = p_id;    
END;
$$ LANGUAGE 'plpgsql';

-- 10) REMOVER UM EQUIPAMENTO
CREATE OR REPLACE PROCEDURE remover_equipamento(p_id integer) AS
$$
BEGIN
    IF (EXISTS (SELECT * FROM equipamento WHERE id = p_id)) THEN
        UPDATE equipamento SET ativo = FALSE WHERE id = p_id;
    ELSE
        RAISE NOTICE 'Não existe.';
    END IF;
END;
$$ LANGUAGE 'plpgsql';

-- ============ SERVIÇOS ============
-- 11) ABRIR NOVO SERVIÇO
CREATE OR REPLACE FUNCTION novo_servico (p_titulo text, p_descricao text, p_criador_id integer, p_responsavel_id integer default 0) RETURNS BOOLEAN AS
$$
BEGIN
    IF (p_responsavel_id != 0) THEN
        IF(EXISTS(SELECT * FROM usuario WHERE id = p_responsavel_id)) THEN
            IF(EXISTS(SELECT * FROM usuario WHERE id = p_criador_id)) THEN
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
        IF(EXISTS(SELECT * FROM usuario WHERE id = p_responsavel_id)) THEN
            IF(EXISTS(SELECT * FROM usuario WHERE id = p_criador_id)) THEN
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

-- 12) LISTAR TODOS SERVIÇOS CADASTRADOS
CREATE OR REPLACE FUNCTION listar_servicos() RETURNS TABLE (
    s_id integer,
    s_descricao text,
    s_titulo text,
    s_data_hora_criacao timestamp,
    s_finalizado timestamp,
    s_criador_id integer,
    s_responsavel_id integer
) AS
$$
BEGIN
    RETURN QUERY SELECT id, descricao, titulo, data_hora_criacao, finalizado, criador_id, responsavel_id FROM servico ORDER BY id;
END;
$$ LANGUAGE 'plpgsql';

-- 13) LISTAR APENAS SERVIÇOS NÃO FINALIZADOS
CREATE OR REPLACE FUNCTION listar_servicos_nao_finalizados() RETURNS TABLE (
    p_id integer,
    p_descricao text,
    p_finalizado boolean
) AS
$$
BEGIN
    RETURN QUERY SELECT id, descricao, finalizado IS NULL FROM servico WHERE finalizado IS NULL ORDER BY id;
END;
$$ LANGUAGE 'plpgsql';

-- 14) LISTAR APENAS SERVIÇOS FINALIZADOS
CREATE OR REPLACE FUNCTION listar_servicos_finalizados() RETURNS TABLE (p_id integer, p_descricao text) AS
$$
begin
    return query select id, descricao from servico where finalizado is not null order by id;
end;
$$ LANGUAGE 'plpgsql';

-- 15) BUSCAR UM SERVIÇO PELO ID
CREATE OR REPLACE FUNCTION obter_servico_id(integer) RETURNS TABLE(p_id integer, p_descricao text, p_data_hora_criacao timestamp) AS 
$$
begin
    return query select id, descricao, data_hora_criacao from servico where id = $1;
end;
$$ language 'plpgsql';

-- ============ ATUALIZAÇÃO DE SERVIÇOS ============
-- 16) ALTERAR RESPONSÁVEL DE UM SERVIÇO
DROP PROCEDURE alterar_responsavel;
CREATE OR REPLACE PROCEDURE alterar_responsavel(p_novo_responsavel_id integer, p_servico_id integer) AS 
$$
begin
    if (exists(select * from usuario where id = p_novo_responsavel_id)) then
    update servico set responsavel_id = p_novo_responsavel_id where id = p_servico_id;
    else
        raise notice 'deu xabum responsavel % n existe', p_novo_responsavel_id;
    end if;    
end;
$$ LANGUAGE 'plpgsql';

-- 17) FINALIZAR UM SERVIÇO
CREATE OR REPLACE PROCEDURE finalizar_servico (p_id integer) AS
$$
BEGIN
    UPDATE servico SET finalizado = current_timestamp WHERE id = p_id AND finalizado IS NULL;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'ID não encontrado.';
    END IF;
END;
$$ LANGUAGE 'plpgsql';

-- 18) ALTERAR DESCRIÇÃO DE UM SERVIÇO
CREATE OR REPLACE PROCEDURE alterar_descricao_servico (p_id integer, p_descricao text) AS
$$
BEGIN
    UPDATE servico SET descricao = p_descricao WHERE id = p_id;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'ID não encontrado.';
    END IF;
END;
$$ LANGUAGE 'plpgsql';

-- 19) ALTERAR TÍTULO DE UM SERVIÇO
CREATE OR REPLACE PROCEDURE alterar_titulo_servico (p_id integer, p_titulo text) AS
$$
BEGIN
    UPDATE servico SET titulo = p_titulo WHERE id = p_id;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'ID não encontrado.';
    END IF;
END;
$$ LANGUAGE 'plpgsql';

-- 20) QUANTIDADE DE SERVIÇOS EXISTEM
CREATE OR REPLACE FUNCTION qtde_servico() RETURNS integer AS
$$
DECLARE
    qtde integer := 0;
BEGIN
    SELECT COUNT(*) FROM servico INTO qtde;
    RETURN qtde;
END;
$$ LANGUAGE 'plpgsql';

-- ============ STATUS ============
-- 21) REGISTRAR UM NOVO STATUS PARA UMS ERVIÇO
CREATE OR REPLACE FUNCTION registrar_novo_status_criador(p_situacao text, p_servico_id integer, p_criador_id integer) RETURNS BOOLEAN AS
$$
begin
    IF (EXISTS(SELECT * FROM servico WHERE id = p_servico_id)) THEN
        IF(EXISTS(SELECT * FROM servico where id = p_servico_id and criador_id = p_criador_id)) THEN   
            INSERT INTO status (situacao, servico_id, criador_id) VALUES (p_situacao, p_servico_id, p_criador_id);
            RETURN TRUE;
        ELSE
            RAISE NOTICE 'Este servico n tem este criador';
        END IF;
    END IF;
    RETURN FALSE;
end;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION registrar_novo_status_responsavel(p_situacao text, p_servico_id integer, p_responsavel_id integer) RETURNS BOOLEAN AS
$$
begin
    IF (EXISTS(SELECT * FROM servico WHERE id = p_servico_id)) THEN
         IF(EXISTS(SELECT * FROM servico where id = p_servico_id and responsavel_id = p_responsavel_id)) THEN 
        INSERT INTO status (situacao, servico_id, responsavel_id) VALUES (p_situacao, p_servico_id,p_responsavel_id);
        RETURN TRUE;
         ELSE
            RAISE NOTICE 'Este servico n tem este reponsavel';
        END IF;
    END IF;
    RETURN FALSE;
end;
$$ LANGUAGE 'plpgsql';

-- 22) LISTAR TODOS OS STATUS DE UM SERVIÇO ESPECÍFICO
CREATE OR REPLACE FUNCTION listar_status(i integer) RETURNS TABLE(p_status text) AS
$$
BEGIN
    RETURN QUERY SELECT situacao FROM status where servico_id = i;
END;
$$ LANGUAGE 'plpgsql';

-- 23) RETORNAR O ÚLTIMO STATUS REGISTRADO DE UM SERVIÇO
CREATE OR REPLACE FUNCTION listar_ultimo_status(integer) RETURNS text AS 
$$
DECLARE
    status_retorno text;
BEGIN
    SELECT situacao FROM status WHERE servico_id = $1 ORDER BY data_hora DESC LIMIT 1 INTO status_retorno;
    RETURN status_retorno;
END;
$$ LANGUAGE 'plpgsql';

-- ============ STATUS ============
-- 24~25) LISTAR SERVIÇOS CRIADS POR UM USUÁRIO
DROP FUNCTION IF EXISTS listar_servicos_por_usuario;
CREATE OR REPLACE FUNCTION listar_servicos_por_usuario(integer) RETURNS TABLE (p_id integer, p_descricao text, p_titulo text, p_data_hora_criacao timestamp, p_finalizado timestamp, p_responsavel_nome character varying(200)) AS
$$
BEGIN
    RETURN QUERY select servico.id, servico.descricao, servico.titulo, servico.data_hora_criacao, servico.finalizado, usuario.nome as responsavel_nome FROM servico INNER JOIN usuario ON (servico.responsavel_id = usuario.id) where criador_id = $1;
END;
$$ LANGUAGE 'plpgsql';

-- 26) QUANTIDADES DE SERVIÇOS POR USUÁRIO
CREATE OR REPLACE FUNCTION qtde_servicos_por_criador() RETURNS TABLE (p_id integer, p_nome character varying(200), p_qtde integer) AS 
$$
BEGIN
    RETURN QUERY SELECT usuario.id, usuario.nome,  count(servico.criador_id)::integer as qtde from usuario inner join servico on (usuario.id = servico.criador_id) group by usuario.id, criador_id,usuario.nome;
END;
$$ LANGUAGE 'plpgsql';

-- 27) LISTAR SERVIÇOS CRIADOS EM DETERMINADA DATA
CREATE OR REPLACE FUNCTION listar_servicos_por_data(date) RETURNS TABLE (p_id integer, p_descricao text, p_titulo text, p_data_hora_criacao timestamp, p_finalizado timestamp, p_responsavel_id integer, p_criador_id integer) AS
$$
BEGIN
   RETURN QUERY SELECT id, descricao, titulo, data_hora_criacao, finalizado, responsavel_id, criador_id from servico where data_hora_criacao::date = $1 ORDER BY id;
END;
$$ LANGUAGE 'plpgsql';

select * from listar_servicos_por_data('2026-03-26');

-- 28) RETORNAR QUANTIDADE DE SERVIÇOS EM ABERTO
CREATE OR REPLACE FUNCTION qtde_servicos_aberto() RETURNS integer 
AS
$$
DECLARE
    qtde integer := 0;
BEGIN
    select count(*)::integer from servico where finalizado is null into qtde;
    RETURN qtde;
END;
$$ LANGUAGE 'plpgsql';

-- 29) RETORNAR QUANTIDADE DE SERVIÇOS FECHADOS
CREATE OR REPLACE FUNCTION qtde_servicos_finalizados() RETURNS integer 
AS
$$
DECLARE
    qtde integer := 0;
BEGIN
    select count(*)::integer from servico where finalizado is not null into qtde;
    RETURN qtde;
END;
$$ LANGUAGE 'plpgsql';

-- 30) LISTAR SERVIÇOS JUNTO COM NOME DO CRIADOR E DO RESPONSÁVEL
CREATE OR REPLACE FUNCTION listar_servicos_nome_criador_nome_responsavel() RETURNS TABLE 
(p_id integer, p_titulo text, p_descricao text, p_data_hora_criacao timestamp, p_finalizado timestamp, p_criador character varying(200), p_responsavel character varying(200)) AS
$$
BEGIN
    RETURN QUERY with tb_criador AS (
        select servico.id, titulo, data_hora_criacao, descricao, finalizado, nome as criador, responsavel_id from servico inner join usuario on (servico.criador_id = usuario.id)
    ) SELECT tb_criador.id, tb_criador.titulo, tb_criador.descricao,  tb_criador.data_hora_criacao, tb_criador.finalizado, criador, usuario.nome as responsavel from tb_criador inner join usuario on (tb_criador.responsavel_id = usuario.id);
END;
$$ LANGUAGE 'plpgsql';











-- ======================= TRABALHO PBDA 1 =======================
-- 1.1 - Função Prioridade do Serviço [0,5]
CREATE OR REPLACE FUNCTION prioridade_servico(p_id_servico integer) RETURNS TEXT AS
$$
DECLARE
    qnt_status integer;
    prioridade text;
BEGIN
    SELECT COUNT(*) INTO qnt_status FROM status WHERE servico_id = p_id_servico;

    IF (qnt_status = 1) THEN
        prioridade := 'Baixa';
    ELSIF (qnt_status > 1 AND qnt_status < 4) THEN
        prioridade := 'Média';
    ELSIF (qnt_status >= 4) THEN
        prioridade := 'Alta';
    ELSE
        prioridade := 'Sem status';
    END IF;

    RETURN prioridade;
END;
$$ LANGUAGE 'plpgsql';

-- 1.2 - Função para cálculo do tempo de atendimento [0,5]
CREATE OR REPLACE FUNCTION tempo_atendimento(p_id_servico integer) RETURNS NUMERIC AS
$$
DECLARE
    data_inicio timestamp;
    data_fim timestamp;
    tempo_horas numeric;
BEGIN
    SELECT data_hora_criacao, finalizado INTO data_inicio, data_fim FROM servico WHERE id = p_id_servico;

    IF (data_fim IS NULL) THEN
        RETURN NULL;
    END IF;

    tempo_horas := ROUND(EXTRACT(EPOCH FROM (data_fim - data_inicio)) / 3600, 2);

    RETURN tempo_horas;
END;
$$ LANGUAGE 'plpgsql';

-- 1.3 - Ranking de Técnicos [0,5]
CREATE OR REPLACE FUNCTION ranking_tecnicos() RETURNS TABLE (
    nome_usuario character varying(200),
    qnt_servicos_finalizados integer
) AS
$$
BEGIN
    RETURN QUERY SELECT usuario.nome, COUNT(servico.id)::INTEGER AS qnt_servicos_finalizados FROM servico 
    INNER JOIN usuario ON usuario.id = servico.responsavel_id 
    WHERE servico.finalizado IS NOT NULL 
    GROUP BY usuario.nome ORDER BY qnt_servicos_finalizados DESC;
END;
$$ LANGUAGE 'plpgsql';

-- 1.4 - Reabir Serviço [0,5]
CREATE OR REPLACE PROCEDURE reabrir_servico(p_id_servico integer) AS
$$
BEGIN
    IF EXISTS (SELECT 1 FROM servico WHERE id = p_id_servico AND finalizado IS NOT NULL) THEN
        UPDATE servico SET finalizado = NULL WHERE id = p_id_servico;
        INSERT INTO STATUS (situacao, servico_id) VALUES ('Serviço Reaberto', p_id_servico);
    ELSE
        RAISE NOTICE 'Serviço não encontrado ou já aberto.';
    END IF;
END;
$$ LANGUAGE 'plpgsql';

-- 1.5 - Registrar status inteligente [0,5]
CREATE OR REPLACE PROCEDURE registrar_status_inteli(p_servico_id integer, p_mensagem text) AS
$$
BEGIN
    IF EXISTS (SELECT 1 FROM servico WHERE id = p_servico_id) THEN
        INSERT INTO status(situacao, servico_id) VALUES (p_mensagem, p_servico_id);

        IF (LOWER(p_mensagem) LIKE '%resolvido%') THEN
            UPDATE servico SET finalizado = current_timestamp WHERE id = p_servico_id AND finalizado is NULL;
        END IF;
    ELSE
        RAISE NOTICE 'Serviço não encontrado.';
    END IF;
END;
$$ LANGUAGE 'plpgsql';

-- 1.6 - Dashboard de Dados [0,5]
CREATE OR REPLACE FUNCTION dashboard_sistema() RETURNS TABLE (
    total_servicos integer,
    servicos_abertos integer,
    servicos_finalizados integer,
    tempo_medio_atendimento_horas numeric
) AS
$$
BEGIN
    RETURN QUERY 
    SELECT 
        COUNT(*)::INTEGER AS total_servicos,
        COUNT(*) FILTER (WHERE finalizado IS NULL)::INTEGER AS servicos_abertos,
        COUNT(*) FILTER (WHERE finalizado IS NOT NULL)::INTEGER AS servicos_finalizados,
        ROUND (AVG(EXTRACT(EPOCH FROM (finalizado - data_hora_criacao)) / 3600) FILTER (WHERE finalizado IS NOT NULL), 2) AS tempo_medio_atendimento_horas
    FROM servico;
END;
$$ LANGUAGE 'plpgsql';
