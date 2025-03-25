
DROP DATABASE IF EXISTS redesocial;

CREATE DATABASE redesocial;

\c redesocial;

CREATE TABLE usuario (
    id serial PRIMARY KEY,
    nome character varying(100) NOT NULL,
    email character varying(100) UNIQUE NOT NULL,
    senha character varying(100) NOT NULL,
    data_nascimento date
);

CREATE TABLE conta (
    id serial PRIMARY KEY,
    nome_usuario text UNIQUE NOT NULL,
    data_hora_criacao timestamp DEFAULT current_timestamp,
    usuario_id integer REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE publicacao (
    id serial PRIMARY KEY,
    texto text,
    arquivo_principal text NOT NULL,
    latitude double precision,
    longitude double precision,
    data_hora timestamp DEFAULT current_timestamp
);

CREATE TABLE arquivo (
    id serial PRIMARY KEY,
    arquivo text NOT NULL,
    publicacao_id integer REFERENCES publicacao(id) ON DELETE CASCADE
);

CREATE TABLE conta_publicacao (
    publicacao_id integer REFERENCES publicacao(id) ON DELETE CASCADE,
    conta_id integer REFERENCES conta(id) ON DELETE CASCADE,
    PRIMARY KEY (publicacao_id, conta_id)
);

CREATE TABLE comentario (
    id serial PRIMARY KEY,
    texto text NOT NULL,
    data_hora timestamp DEFAULT current_timestamp,
    publicacao_id integer REFERENCES publicacao(id) ON DELETE CASCADE,
    conta_id integer REFERENCES conta(id)
);

INSERT INTO usuario (nome, email, senha, data_nascimento) VALUES 
('Ana','ana@gmail.com','123','1950/10/01'),
('Bruno','bruno@gmail.com','321','1970/01/01'),
('Cleiton','cleiton@gmail.com','333','1960/02/01'),
('Dener','dener@gmail.com','121','1999/10/10'),
('Everton','everton@gmail.com','333','2002/10/23');

INSERT INTO conta (usuario_id, nome_usuario) VALUES
('1','Aninha90'),
('1','AinhaNOVO'),
('2','Bruninho0o0o'),
('3','Cleitinho_Rasta'),
('4','DenerzãoOP'),
('5','Evertonzzz78');

INSERT INTO publicacao (texto, arquivo_principal, latitude, longitude) VALUES 
('TextoPubli1', 'Arquivo1.png','384.299','333.300'),
('TextoPubli2','Arquivo2.png','22','390.121'),
('TextoPubli3','Arquivo3.png','222.332','92331'),
('TextoPubli4','Arquivo4.png','11911.911','299'),
('TextoPubli5','Arquivo5.png','12323','9999'),
('TextoPubli6', 'Arquivo6.png','89899','898989');

INSERT INTO conta_publicacao (publicacao_id, conta_id) VALUES
('1','1'),
('2','2'),
('3','3'),
('4','4'),
('5','5');

INSERT INTO comentario (texto, publicacao_id, conta_id) VALUES
('Coment1','1','1'),
('Coment2','2','2'),
('Coment3','3','3'),
('Coment4','4','4'),
('Coment5','5','5');

INSERT INTO arquivo (arquivo, publicacao_id) VALUES 
('Arquivão1','1'),
('Arquivão2','2'),
('Arquivão3','3'),
('Arquivão4','4'),
('Arquivão5','5'),
('Arquivão6','6');

-- 1) Listar o nome do usuário e o nomes das suas contas
SELECT usuario.nome, conta.nome_usuario FROM usuario LEFT JOIN conta ON usuario.id = conta.usuario_id;

-- 2) Listas as publicações e seus arquivos
SELECT publicacao.texto, arquivo.arquivo FROM publicacao INNER JOIN arquivo ON publicacao.id = arquivo.publicacao_id;

-- 3) Listar as publicações e seus comentários
SELECT publicacao.texto, comentario.texto FROM publicacao INNER JOIN comentario ON publicacao.id = comentario.publicacao_id;

-- 4) Listar somente publicações com comentários
UPDATE publicacao SET texto = 'TextoPubli1' WHERE id = 1;
SELECT publicacao.texto, comentario.texto AS comentario FROM publicacao LEFT JOIN comentario ON publicacao.id = comentario.publicacao_id GROUP BY publicacao.id, comentario.id HAVING count(comentario.id) >= 1;

-- 5) Retornar a quantidade de contas por usuário
SELECT usuario.nome, count(conta.usuario_id) AS quantidade
FROM usuario
LEFT JOIN conta ON usuario.id = conta.usuario_id
GROUP BY usuario.id, usuario.nome ORDER BY quantidade DESC;

-- 6) Retornar a quantidade de publicações por usuário
SELECT usuario.nome, count(publicacao.id) AS quantidade
FROM usuario 
JOIN conta ON usuario.id = conta.usuario_id
JOIN conta_publicacao ON conta_publicacao.conta_id = conta.id
JOIN publicacao ON publicacao.id = conta_publicacao.publicacao_id
GROUP BY usuario.id, usuario.nome;

-- 7) Retornar as publicações com mais comentários
INSERT INTO comentario (texto, publicacao_id, conta_id) VALUES ('Comentario12','1','1'); 

SELECT publicacao.texto, COUNT(comentario.id) AS quantidade_comentarios
FROM publicacao
LEFT JOIN comentario ON publicacao.id = comentario.publicacao_id
GROUP BY publicacao.id, publicacao.texto
ORDER BY quantidade_comentarios DESC;

-- 8) Retornar publicações que não tem comentários
SELECT publicacao.texto, COUNT(comentario.id) AS quantidade_comentarios
FROM publicacao
LEFT JOIN comentario ON publicacao.id = comentario.publicacao_id
GROUP BY publicacao.id, publicacao.texto
HAVING COUNT(comentario.id) = 0
ORDER BY quantidade_comentarios DESC;

-- 9) Retornar somente usuários que possuem um única conta
SELECT usuario.nome, count(conta.id) AS quantidade_conta
FROM usuario
LEFT JOIN conta ON usuario.id = conta.usuario_id
GROUP BY usuario.nome, usuario.id
HAVING count(conta.id) = 1;

-- 10) Retornar usuários com mais de uma conta sob sua responsabilidade
SELECT usuario.nome, count(conta.id) AS quantidade_conta
FROM usuario
LEFT JOIN conta ON usuario.id = conta.usuario_id
GROUP BY usuario.nome, usuario.id
HAVING count(conta.id) > 1;

-- 11) Retornar publicações sem arquivos adicionais (Sem registros na tabela de arquivo)
DELETE FROM arquivo WHERE id = 6;

SELECT publicacao.id, publicacao.texto, publicacao.arquivo_principal
FROM publicacao
LEFT JOIN arquivo ON publicacao.id = arquivo.publicacao_id
GROUP BY publicacao.id, publicacao.texto
HAVING count(arquivo.id) < 1;

-- 12) Retornar somente publicações compartilhadas por mais de uma conta
SELECT publicacao.texto, count(conta_publicacao.conta_id) AS quantidade_compartilhamento
FROM publicacao
JOIN conta_publicacao ON publicacao.id = conta_publicacao.publicacao_id
GROUP BY publicacao.id, publicacao.texto
HAVING COUNT(conta_publicacao.conta_id) > 1;

-- 13) Retornar usuários e suas respectivas contas que não criaram nenhuma publicação
INSERT INTO usuario (nome, email, senha, data_nascimento) VALUES ('Xavier','xavier@gmail.com','1223','1990/10/01');

SELECT usuario.nome, count(publicacao.id) as quantidade_publicacao
FROM usuario
LEFT JOIN conta ON usuario.id = conta.usuario_id
LEFT JOIN conta_publicacao ON conta_publicacao.conta_id = conta.id
LEFT JOIN publicacao ON conta_publicacao.publicacao_id = publicacao.id
GROUP BY usuario.id, usuario.nome
HAVING count(publicacao.id) = 0;

-- 14) Retornar usuários que possuem só publicações sem comentários
SELECT usuario.nome, count(comentario.id) as quantidade_comentario
FROM usuario
LEFT JOIN conta ON usuario.id = conta.usuario_id
LEFT JOIN conta_publicacao ON conta_publicacao.conta_id = conta.id
LEFT JOIN publicacao ON conta_publicacao.publicacao_id = publicacao.id
LEFT JOIN comentario ON publicacao.id = comentario.publicacao_id
GROUP BY usuario.id, usuario.nome
HAVING count(comentario.id) = 0;

-- 15) Retornar a conta que mais realizou comentários
SELECT conta.nome_usuario, count(comentario.id) as quantidade_comentario
FROM conta
LEFT JOIN conta_publicacao ON conta_publicacao.conta_id = conta.id
LEFT JOIN publicacao ON conta_publicacao.publicacao_id = publicacao.id
LEFT JOIN comentario ON publicacao.id = comentario.publicacao_id
GROUP BY conta.id, conta.nome_usuario
HAVING count(comentario.id) >= 0
LIMIT 1;

-- 16) Retornar o nome do usuário e o nome da conta da última conta criada
INSERT INTO conta (usuario_id, nome_usuario) VALUES('1','Aninha2025');

SELECT usuario.nome, conta.nome_usuario, conta.data_hora_criacao
FROM usuario
JOIN conta ON usuario.id = conta.usuario_id
ORDER BY conta.data_hora_criacao
DESC LIMIT 1;

-- 17) Retornar usuário(s) que possue(m) a maior quantidade de contas
SELECT usuario.nome, count(conta.id)
FROM usuario
LEFT JOIN conta ON usuario.id = conta.usuario_id
GROUP BY usuario.id, usuario.nome
ORDER BY count(conta.id)
DESC LIMIT 1;

-- 18) Retornar usuário(s) que possue(m) a menor quantidade de contas
SELECT usuario.nome, count(conta.id)
FROM usuario
LEFT JOIN conta ON usuario.id = conta.usuario_id
GROUP BY usuario.id, usuario.nome
ORDER BY count(conta.id)
ASC LIMIT 1;

-- 19) Retornar comentários realizados durante a última semana (últimos 7 dias)
INSERT INTO comentario (texto, publicacao_id, conta_id) VALUES ('Comentxxx','1','1');

SELECT texto, data_hora 
FROM comentario
WHERE data_hora >= NOW() - INTERVAL '7 DAYS';

-- 20) Retornar as contas do(s) usuário(s) mais velho(s)
SELECT conta.nome_usuario, usuario.nome, usuario.data_nascimento
FROM conta
JOIN usuario ON conta.usuario_id = usuario.id
WHERE usuario.data_nascimento = (SELECT MIN(data_nascimento) FROM usuario);

-- 21) Listar nos primeiros resultados usuários sem conta acima dos usuários com conta
SELECT usuario.nome, count(conta.id) AS quantidade_contas
FROM usuario
LEFT JOIN conta ON usuario.id = conta.usuario_id
GROUP BY usuario.id, usuario.nome
ORDER BY quantidade_contas ASC;

-- 22) Quantidade total de comentários dado um intervalo de datas
SELECT count(comentario.id)
FROM comentario
WHERE data_hora BETWEEN '2025/03/18' AND '2025/03/20';

-- 23) Selecione publicações que tenham mais de um arquivo (fora o obrigatório)
SELECT publicacao.id, count(arquivo.id)
FROM publicacao
LEFT JOIN arquivo ON publicacao.id = arquivo.publicacao_id
GROUP BY publicacao.id
HAVING COUNT(arquivo.id) > 1; 

-- 24) Publicação com maior texto (maior número de caracteres)
SELECT publicacao.id, publicacao.texto, LENGTH(publicacao.texto) AS tamanho_texto
FROM publicacao
ORDER BY tamanho_texto DESC
LIMIT 1;

-- 25) Publicações com maior número de caracteres (nesta questão cuidar a questão do empate, ou seja, 2 ou mais publicações terem o texto com o mesma quantidade de caracteres)
SELECT publicacao.id, publicacao.texto, LENGTH(publicacao.texto) AS tamanho_texto
FROM publicacao
WHERE LENGTH(publicacao.texto) = (SELECT MAX(LENGTH(texto)) FROM publicacao);

-- 26) Usuário que mais publicou em um dado intervalo de tempo
SELECT usuario.nome, count(publicacao.id) AS quantidade_publicacoes
FROM usuario
JOIN conta ON conta.usuario_id = usuario.id
JOIN conta_publicacao ON conta_publicacao.conta_id = conta.id
JOIN publicacao ON publicacao.id = conta_publicacao.publicacao_id
WHERE publicacao.data_hora BETWEEN '2025-01-01' AND '2025-03-25'
GROUP BY usuario.nome, usuario.id
ORDER BY quantidade_publicacoes DESC
LIMIT 1;

-- 27) Conta que mais publicou
SELECT conta.nome_usuario, count(conta_publicacao.publicacao_id) AS quantidade_publicacao
FROM conta
JOIN conta_publicacao ON conta.id = conta_publicacao.conta_id
GROUP BY conta.id, conta.nome_usuario
ORDER BY quantidade_publicacao DESC
LIMIT 1;

-- 28) Conta que mais compartilhou publicações
SELECT conta.nome_usuario, count(conta_publicacao.publicacao_id) AS quantidade_compartilhamento
FROM conta
JOIN conta_publicacao ON conta.id = conta_publicacao.conta_id
GROUP BY conta.id, conta.nome_usuario
ORDER BY quantidade_compartilhamento DESC
LIMIT 1;

-- 29) Publicação com mais arquivos
INSERT INTO arquivo (arquivo, publicacao_id) VALUES ('Arquivão1111','1');

SELECT publicacao.id, count(arquivo.id) AS quantidade_arquivo
FROM publicacao
LEFT JOIN arquivo ON publicacao.id = arquivo.publicacao_id
GROUP BY publicacao.id, publicacao.texto
ORDER BY COUNT(arquivo.id) DESC;

-- 30) Alterar a tabela conta_publicação e adicionar a data e hora em que uma publicação foi compartilhada
ALTER TABLE conta_publicacao
ADD COLUMN data_hora_compartilhamento timestamp DEFAULT current_timestamp;

-- 31) Usuário que mais realizou comentários
SELECT usuario.nome, count(comentario.id)
FROM usuario
LEFT JOIN conta ON usuario.id = conta.usuario_id
LEFT JOIN conta_publicacao ON conta.id = conta_publicacao.conta_id
LEFT JOIN publicacao ON conta_publicacao.publicacao_id = publicacao.id
LEFT JOIN comentario ON publicacao.id = comentario.publicacao_id
GROUP BY usuario.nome, usuario.id
ORDER BY count(comentario.id) DESC
LIMIT 1;

-- 32) Conta que mais realizou comentários
SELECT conta.nome_usuario, count(comentario.id)
FROM conta
LEFT JOIN conta_publicacao ON conta.id = conta_publicacao.conta_id
LEFT JOIN publicacao ON conta_publicacao.publicacao_id = publicacao.id
LEFT JOIN comentario ON publicacao.id = comentario.publicacao_id
GROUP BY conta.nome_usuario, conta.id
ORDER BY count(comentario.id) DESC
LIMIT 1;

-- 33) Formatar o retorno da data e hora
SELECT TO_CHAR(data_hora, 'DD/MM/YYYY HH24:MI:SS') AS data_formatada
FROM publicacao;