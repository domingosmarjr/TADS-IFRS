
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

-- 8) Retornar publicações que não tem comentários

-- 9) Retornar somente usuários que possuem um única conta

-- 10) Retornar usuários com mais de uma conta sob sua responsabilidade

-- 11) Retornar publicações sem arquivos adicionais (Sem registros na tabela de arquivo)

-- 12) Retornar somente publicações compartilhadas por mais de uma conta

-- 13) Retornar usuários e suas respectivas contas que não criaram nenhuma publicação

-- 14) Retornar usuários que possuem só publicações sem comentários

-- 15) Retornar a conta que mais realizou comentários

-- 16) Retornar o nome do usuário e o nome da conta da última conta criada

-- 17) Retornar usuário(s) que possue(m) a maior quantidade de contas

-- 18) Retornar usuário(s) que possue(m) a menor quantidade de contas

-- 19) Retornar comentários realizados durante a última semana (últimos 7 dias)

-- 20) Retornar as contas do(s) usuário(s) mais velho(s)

-- 21) Listar nos primeiros resultados usuários sem conta acima dos usuários com conta

-- 22) Quantidade total de comentários dado um intervalo de datas

-- 23) Selecione publicações que tenham mais de um arquivo (fora o obrigatório)

-- 24) Publicação com maior texto (maior número de caracteres)

-- 25) Publicações com maior número de caracteres (nesta questão cuidar a questão do empate, ou seja, 2 ou mais publicações terem o texto com o mesma quantidade de caracteres)

-- 26) Usuário que mais publicou em um dado intervalo de tempo

-- 27) Conta que mais publicou

-- 28) Conta que mais compartilhou publicações

-- 29) Publicação com mais arquivos

-- 30) Alterar a tabela conta_publicação e adicionar a data e hora em que uma publicação foi compartilhada

-- 31) Usuário que mais realizou comentários

-- 32) Conta que mais realizou comentários

-- 33) Formatar o retorno da data e hora