-- =========================
-- USUARIOS (TECNICOS - DIFERENCIACAO DE RANKING)
-- =========================
INSERT INTO usuario (nome, email, senha) VALUES
('Carlos Silva', 'carlos@escola.com', md5('123')),   -- TOP (mais finalizacoes)
('Ana Souza', 'ana@escola.com', md5('123')),         -- MEDIO-ALTO
('Bruno Oliveira', 'bruno@escola.com', md5('123')),  -- MEDIO
('Mariana Costa', 'mariana@escola.com', md5('123')), -- BAIXO
('Joao Pereira', 'joao@escola.com', md5('123'));     -- MUITO BAIXO

-- =========================
-- EQUIPAMENTOS
-- =========================
INSERT INTO equipamento (descricao) VALUES
('Projetor Epson PowerLite'),
('Computador Dell OptiPlex'),
('Impressora HP LaserJet'),
('Ar Condicionado LG Dual Inverter'),
('Roteador TP-Link Archer');

-- =========================
-- SERVICOS (COM PRIORIDADE IMPLICITA + RANKING REAL)
-- =========================
INSERT INTO servico (titulo, descricao, criador_id, responsavel_id, equipamento_id) VALUES
-- Carlos (ALTA PERFORMANCE - muitos finalizados)
('Projetor nao liga', 'Sem energia ao ligar', 1, 1, 1),
('Computador travando forte', 'Sistema extremamente lento', 1, 1, 2),
('Switch sem rede', 'Falha total em portas', 1, 1, 5),

-- Ana (BOM DESEMPENHO)
('Impressora sem tinta', 'Cartucho vazio', 2, 2, 3),
('Internet instavel', 'Quedas frequentes', 2, 2, 5),
('Notebook reiniciando', 'Instabilidade de sistema', 2, 2, 2),

-- Bruno (MEDIO)
('Ar condicionado nao gela', 'Sem refrigeracao', 3, 3, 4),
('PC nao liga', 'Possivel fonte queimada', 3, 3, 2),

-- Mariana (BAIXO DESEMPENHO)
('Projetor imagem fraca', 'Lâmpada fraca', 4, 4, 1),
('Impressora falhando', 'Linhas na impressão', 4, 4, 3),

-- Joao (QUASE SEM RESOLUCAO)
('Rede caindo sala 2', 'Instabilidade constante', 5, 5, 5),
('Notebook nao inicia', 'Erro critico de boot', 5, 5, 2);

-- =========================
-- FINALIZACOES (DESIGUALDADE DE RANKING)
-- =========================
UPDATE servico
SET finalizado = current_timestamp
WHERE titulo IN (
    -- Carlos (3 finalizados -> TOP)
    'Projetor nao liga',
    'Computador travando forte',
    'Switch sem rede',

    -- Ana (2 finalizados)
    'Impressora sem tinta',
    'Internet instavel',

    -- Bruno (1 finalizado)
    'Ar condicionado nao gela'
);

-- =========================
-- STATUS (HISTORICO MAIS RICO + PRIORIDADE IMPLICITA)
-- =========================

-- CARLOS (MUITOS STATUS -> ALTA PRIORIDADE)
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Diagnostico inicial', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Projetor nao liga';
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Fonte substituida', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Projetor nao liga';
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Resolvido', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Projetor nao liga';

INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Analise profunda', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Computador travando forte';
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Limpeza sistema', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Computador travando forte';
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Resolvido', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Computador travando forte';

-- ANA (MEDIO-ALTO)
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Cartucho solicitado', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Impressora sem tinta';
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Cartucho instalado', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Impressora sem tinta';
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Resolvido', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Impressora sem tinta';

INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Verificacao rede', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Internet instavel';
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Teste estabilidade', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Internet instavel';

-- BRUNO (MEDIO)
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Diagnostico tecnico', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Ar condicionado nao gela';
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Gas reabastecido', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Ar condicionado nao gela';
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Resolvido', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Ar condicionado nao gela';

INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Fonte testada', id, responsavel_id, criador_id FROM servico WHERE titulo = 'PC nao liga';

-- MARIANA (BAIXO DESEMPENHO)
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Analise inicial', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Projetor imagem fraca';

-- JOAO (QUASE SEM RESOLUCAO)
INSERT INTO status (situacao, servico_id, responsavel_id, criador_id)
SELECT 'Tentativa de diagnostico', id, responsavel_id, criador_id FROM servico WHERE titulo = 'Rede caindo sala 2';