-- LISTA 1 - IMPLEMENTAÇÃO DE BANCO DE DADOS
-- 12/03/2025

-- SpotPobre

DROP DATABASE IF EXISTS spotpobre;

CREATE DATABASE spotpobre;

\c spotpobre;

CREATE TABLE usuario (
    id serial PRIMARY KEY,
    nome character varying(30) NOT NULL,
    email character varying(30) UNIQUE NOT NULL,
    senha character varying(30) NOT NULL
);

CREATE TABLE playlist (
    id serial PRIMARY KEY,
    nome character varying(30) NOT NULL,
    data_hora timestamp DEFAULT current_timestamp,
    usuario_id integer REFERENCES usuario(id)
);

CREATE TABLE artista (
    id serial PRIMARY KEY,
    nome character varying(30) NOT NULL,
    nome_artistico character varying(30) NOT NULL UNIQUE
);

CREATE TABLE album (
    id serial PRIMARY KEY,
    titulo character varying(30) NOT NULL,
    data_lancamento date DEFAULT current_date,
    artista_id integer REFERENCES artista(id)
);

CREATE TABLE musica (
    id serial PRIMARY KEY,
    titulo character varying(30) NOT NULL,
    duracao integer NOT NULL CHECK (duracao > 0),
    album_id integer REFERENCES album(id)
);

CREATE TABLE playlist_musica (
    playlist_id integer REFERENCES playlist(id),
    musica_id integer REFERENCES musica(id),
    PRIMARY KEY (playlist_id, musica_id)
);

-- 1) Insira no mínimo 3 tuplas em cada tabela <!--(Dica: INSERT INTO)-->
INSERT INTO usuario (nome, email, senha) VALUES 
('Domingos', 'domingos.mar.jr@outlook.com', 'dom123'),
('Patrick', 'patrikera@gmail.com', 'patrick321'),
('Yasmin', 'yasmin@gmail.com', 'yasyas111');

INSERT INTO playlist (nome, data_hora, usuario_id) VALUES
('Treino Corrida', '2025/03/13', '1'),
('Taekwondo', '2020/04/11', '1'),
('Sea of Thieves', '2024/02/01', '2'),
('Academia', '2020/05/02', '3');

INSERT INTO artista (nome, nome_artistico) VALUES
('Chester Bennington', 'Chester'),
('Mike Shinoda', 'Shinoda'),
('Antonio', 'Baitaca'),
('Tauz','Player Tauz'),
('Keven','MC Kevinho'),
('Mauro','Oruam');

INSERT INTO album (titulo, data_lancamento, artista_id) VALUES
('Meteora', '2000/01/20', '1'),
('Hybrid Theroy', '1999/12/02', '2'),
('Baitaquisse', '2010/10/01', '3'),
('Rap de Anime Foda', '2015/12/02', '4'),
('Sarrada', '2018/10/10', '5'),
('Sou Oruam', '2010/10/10', '6');

INSERT INTO musica (titulo, duracao, album_id) VALUES 
('In the End', '360', '1'),
('Numb', '120', '1'),
('No More Sorrow', '420', '2'),
('By Myself', '200', '2'),
('Fundo da Grota', '120', '3'),
('Grosso', '100', '3'),
('Rap Kakashi', '290', '4'),
('Rap Guy', '150', '4'),
('Toma Pica', '130', '5'),
('Ai Papai', '100', '5'),
('Solta meu Pai!', '130', '6'),
('Ainnn', '100', '6');

INSERT INTO playlist_musica (playlist_id, musica_id) VALUES
('1','1'),
('1','2'),
('1','3'),
('2','3'),
('2','4'),
('3','5'),
('3','6'),
('3','7'),
('4','9'),
('4','10'),
('4','11');

-- 2) Adicione a coluna _data_nascimento_ na tabela de _usuários_. Além disso, coloque uma cláusula CHECK permitindo somente anos de nascimento >= 1900
ALTER TABLE usuario ADD COLUMN data_nascimento date CHECK (extract (year from data_nascimento) >= 1900);

-- 3) Retorne os _nomes_ dos _usuários_ e suas _datas de nascimento_ formatadas em dia/mes/ano. Para testar será preciso inserir ou atualizar as datas de nascimento de alguns usuários<!--postgres=# select to_char(current_date, 'DD/MM/YYYY') as dma;-->
UPDATE usuario SET data_nascimento = '1996/09/01' WHERE usuario.id = '1';
UPDATE usuario SET data_nascimento = '2000/10/11' WHERE usuario.id = '2';
UPDATE usuario SET data_nascimento = '2005/08/31' WHERE usuario.id = '3';

SELECT nome, to_char(data_nascimento,'DD/MM/YYYY') AS data_nascimento FROM usuario;

-- 4) Delete _usuários_ sem _nome_ <!--(Dica: DELETE)-->
DELETE FROM usuario WHERE nome = null;

-- 5) Torne a coluna _nome_ da tabela _usuários_ obrigatória <!--(Dica: NOT NULL)-->
ALTER TABLE usuario ALTER COLUMN nome SET NOT NULL;

-- 6) Retorne os _títulos_ de todos os _álbuns_ em maiúsculo <!--(Dica: UPPER)-->
SELECT upper(titulo) FROM album;

-- 7) Retorne somente os _títulos_ dos 2 primeiros _álbuns_ cadastrados
SELECT * FROM album LIMIT(2);

-- 8) Retorne o _nome_ e o _email_ de todos os _usuários_ separados por ponto-e-vírgula
SELECT nome || ';' || email AS FORMATADO FROM usuario;

-- 9) Retorne _músicas_ com _duração_ entre 100 e 200 segundos
SELECT musica.titulo, musica.duracao FROM musica WHERE duracao>=100 AND duracao<=200;

-- 10) Retorne _músicas_ que não possuem _duração_ entre 100 e 200 segundos
SELECT musica.titulo, musica.duracao FROM musica WHERE NOT(duracao>=100 AND duracao<=200);

-- 11) Retorne _artistas_ que possuem nome e nome artístico <!--(Dica: IS NULL)-->
SELECT nome, nome_artistico FROM artista WHERE (nome IS NOT NULL AND nome_artistico IS NOT NULL);

-- 12) Retorne, preferencialmente, o nome de todos os artistas. Caso um determinado artista não tenha cadastrado seu nome, retorne na mesma consulta seu nome artístico <!--select coalesce(nome, nome_artistico) from artista;-->
INSERT INTO artista (nome) VALUES ('Yago');
ALTER TABLE artista ALTER COLUMN nome_artistico DROP NOT NULL;
SELECT coalesce(nome, nome_artistico) AS nome FROM artista;

-- 13) Retorne o título dos _álbuns_ lançados em 2023 <!--(Dica: EXTRACT(YEAR FROM data_lancamento))-->
INSERT INTO album (titulo, data_lancamento, artista_id) VALUES ('Emptiness Machine','2023/10/10', '2');
SELECT * FROM album WHERE EXTRACT (YEAR FROM data_lancamento) = '2023';

-- 14) Retorne o _nome_ das _playlists_ que foram criadas hoje
INSERT INTO playlist (nome, usuario_id) VALUES ('Relax', '1');
SELECT nome FROM playlist WHERE extract (day from data_hora) = extract (day from current_date);

-- 15) Atualize todos os _nomes_ dos _artistas_ (_nome_ e _nome_artistico_) para maiúsculo
UPDATE artista SET nome = upper(nome), nome_artistico = upper(nome_artistico);

-- 16) Coloque uma verificação para a coluna _duracao_ (tabela _musica_) para que toda duração tenha mais de 0 segundos
ALTER TABLE musica ADD CONSTRAINT check_duracao CHECK (duracao > 0);

-- 17) Adicione uma restrição _UNIQUE_ para a coluna _email_ da tabela _usuario_
ALTER TABLE usuario DROP CONSTRAINT usuario_email_key;
ALTER TABLE usuario ADD CONSTRAINT usuario_email_key UNIQUE(email);

-- 18) Retorne somente os _artistas_ que o nome artístico começa com "Leo" (Ex: Leo Santana, Leonardo e etc.)
INSERT INTO artista (nome, nome_artistico) VALUES ('Leonardo', 'Leozinho');
SELECT nome_artistico FROM artista WHERE nome LIKE 'Leo%';

-- 19) Retorne o _título_ dos _álbuns_ que estão fazendo aniversário neste mês
INSERT INTO album (titulo, data_lancamento, artista_id) VALUES ('Leo e Leandro','2024/03/10','9');
SELECT titulo, data_lancamento FROM album WHERE EXTRACT (MONTH FROM data_lancamento) = EXTRACT (MONTH FROM current_date);

-- 20) Retorne o _título_ dos _álbuns_ lançados no segundo semestre do ano passado (de julho de 2022 a dezembro de 2022)
INSERT INTO album (titulo, data_lancamento, artista_id) VALUES ('Arrasa Corações', '2022/10/01', '9');
SELECT titulo, to_char(data_lancamento,'DD/MM/YYYY') AS data FROM album WHERE (extract (month from data_lancamento) >= '6' AND extract (year from data_lancamento) = '2022');

-- 21) Retorne o título dos álbuns lançados nos últimos 30 dias <!-- postgres=# select current_date - interval '30 DAY';-->
INSERT INTO album (titulo, data_lancamento, artista_id) VALUES ('2025 do Leo', '2025/02/28', '9');
SELECT titulo FROM album WHERE data_lancamento >= current_date - INTERVAL '30 DAYS';

-- 22) Retorne o título e o dia de lançamento (por extenso) de todos os álbuns <!-- CASE WHEN select extract(dow from current_date) -->
SELECT titulo, to_char(data_lancamento,'Day') AS dia_lancamento FROM album;

-- 23) Retorne o título e o mês de lançamento (por extenso) de todos os álbuns
SELECT titulo, to_char(data_lancamento,'Month') AS mes_lancamento FROM album;

-- 24) Retorne pelo menos um dos álbuns mais antigos
SELECT titulo, data_lancamento FROM album ORDER BY data_lancamento ASC LIMIT 1;

-- 25) Retorne pelo menos um dos álbuns mais recentes
SELECT titulo, data_lancamento FROM album ORDER BY data_lancamento DESC LIMIT 1;

-- 26) Liste os _títulos_ das _músicas_ de todos os _álbuns_ de um determinado _artista_
SELECT musica.titulo AS musica, album.titulo AS album, artista.nome AS nome 
    FROM musica 
    JOIN album ON musica.album_id = album.id 
    JOIN artista ON album.artista_id = artista.id 
WHERE artista.id = '2';

-- 27) Liste os _títulos_ das _músicas_ de um _álbum_ de um determinado _artista_
SELECT musica.titulo AS musica FROM musica
    JOIN album ON musica.album_id = album.id
    JOIN artista ON album.artista_id = artista.id
WHERE artista.id = '1' AND album.id='1';

-- 28) Liste somente os nomes de _usuários_ que possuem alguma _playlist_ (cuidado! com a repetição)
SELECT DISTINCT usuario.nome FROM usuario LEFT JOIN playlist ON usuario.id = playlist.usuario_id;

-- 29) Liste _artistas_ que ainda não possuem _álbuns_ cadastrados
INSERT INTO artista (nome, nome_artistico) VALUES ('Pedro', 'MC Pedroka');
SELECT artista.nome FROM artista LEFT JOIN album ON artista.id = album.artista_id WHERE album.id IS NULL;

-- 30) Liste _usuários_ que ainda não possuem _playlists_ cadastradas
INSERT INTO usuario (nome, email, senha) VALUES ('Luana', 'luna@outlook.com', '123');
SELECT usuario.nome FROM usuario LEFT JOIN playlist ON usuario.id = playlist.usuario_id WHERE playlist.id IS NULL;

-- 31) Retorne a quantidade de _álbuns_ por _artista_
SELECT artista.nome, count(album.id) AS quantidade_albuns 
FROM artista
LEFT JOIN album ON artista.id = album.artista_id
GROUP BY artista.nome
ORDER BY quantidade_albuns DESC;

-- 32) Retorne a quantidade de _músicas_ por _artista_
SELECT artista.nome, count(musica.id) AS quantidade_musica
FROM artista
LEFT JOIN album ON artista.id = album.artista_id
LEFT JOIN musica ON album.id = musica.album_id
GROUP BY artista.nome
ORDER BY quantidade_musica DESC;

-- 33) Retorne o _título_ das _músicas_ de uma _playlist_ de um determinado _usuário_
SELECT musica.titulo AS musica, playlist.nome AS playlist
FROM musica
JOIN playlist_musica ON musica.id = playlist_musica.musica_id
JOIN playlist ON playlist_musica.playlist_id = playlist.id
JOIN usuario ON playlist.usuario_id = usuario.id
WHERE usuario.id = 1 AND playlist.id = 1;

-- 34) Retorne a quantidade de _playlist_ de um determinado usuário
SELECT usuario.nome, count(playlist.id) AS quantidade_playlists
FROM usuario
LEFT JOIN playlist ON playlist.usuario_id = usuario.id 
WHERE usuario.id = '1'
GROUP BY usuario.nome;

-- 35) Retone a quantidade de _músicas_ por _artista_ (de artistas que possuem pelo menos 2 _músicas_)
SELECT artista.nome AS nome_artista, count(musica.id) AS quantidade_musica
FROM artista
LEFT JOIN album ON artista.id = album.artista_id
LEFT JOIN musica ON album.id = musica.album_id
GROUP BY artista.nome
HAVING count(musica.id) >= 2
ORDER BY quantidade_musica DESC;

-- 36) Retorne os títulos de todos os álbuns lançados no mesmo ano em que o álbum mais antigo foi lançado
INSERT INTO album (titulo, data_lancamento, artista_id) VALUES ('Pipokinha', '1999/01/20', '4');

SELECT DISTINCT album.titulo, album.data_lancamento 
FROM album
WHERE 
    EXTRACT (YEAR FROM album.data_lancamento) = 
    (SELECT EXTRACT (YEAR FROM data_lancamento) FROM album ORDER BY data_lancamento ASC LIMIT 1);

-- 37) Retorne os títulos de todos os álbuns lançados no mesmo ano em que o álbum mais novo foi lançado
INSERT INTO album (titulo, data_lancamento, artista_id) VALUES ('Pipocada', '2025/01/20', '4');

SELECT DISTINCT album.titulo, album.data_lancamento
FROM album
WHERE
    EXTRACT (YEAR FROM album.data_lancamento) =
    (SELECT EXTRACT (YEAR FROM data_lancamento) FROM album ORDER BY data_lancamento DESC LIMIT 1);


-- 38) Retorne na mesma consulta os nomes de todos os artistas e de todos os usuários. Caso um determinado artista não tenha cadastrado seu nome, retorne seu nome artístico
SELECT COALESCE (artista.nome, artista.nome_artistico) FROM artista
UNION
SELECT usuario.nome FROM usuario;

-- 39) Retorne nomes das _playlists_ com e sem _músicas_
SELECT playlist.nome, COUNT(playlist_musica.musica_id) AS quantidade_musica
FROM playlist
LEFT JOIN playlist_musica ON playlist_musica.playlist_id = playlist.id
GROUP BY playlist.nome
ORDER BY quantidade_musica DESC;

-- 40) Retorne a média da quantidade de _músicas_ de todas as _playlists_
SELECT avg(quantidade_musica) AS media_musicas_por_playlist
FROM (
    SELECT playlist.id, COUNT(playlist_musica.musica_id) AS quantidade_musica
    FROM playlist
    LEFT JOIN playlist_musica ON playlist_musica.playlist_id = playlist.id
    GROUP BY playlist.id
);

-- 41) Retorne somente _playlists_ que possuem quantidade de músicas maior ou igual a média
SELECT playlist.nome, count(playlist_musica.musica_id) AS quantidade_musica
FROM playlist
LEFT JOIN playlist_musica ON playlist_musica.playlist_id = playlist.id
GROUP BY playlist.id, playlist.nome
HAVING COUNT (playlist_musica.musica_id) >= (
    SELECT AVG(quantidade_musica)
    FROM (
        SELECT COUNT(playlist_musica.musica_id) AS quantidade_musica
        FROM playlist
        LEFT JOIN playlist_musica ON playlist_musica.playlist_id = playlist.id
        GROUP BY playlist.id
    )
)
ORDER BY quantidade_musica DESC;