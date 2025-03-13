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

-- 12) Retorne, preferencialmente, o nome de todos os artistas. Caso um determinado artista não tenha cadastrado seu nome, retorne na mesma consulta seu nome artístico <!--select coalesce(nome, nome_artistico) from artista;-->

-- 13) Retorne o título dos _álbuns_ lançados em 2023 <!--(Dica: EXTRACT(YEAR FROM data_lancamento))-->

-- 14) Retorne o _nome_ das _playlists_ que foram criadas hoje

-- 15) Atualize todos os _nomes_ dos _artistas_ (_nome_ e _nome_artistico_) para maiúsculo

-- 16) Coloque uma verificação para a coluna _duracao_ (tabela _musica_) para que toda duração tenha mais de 0 segundos

-- 17) Adicione uma restrição _UNIQUE_ para a coluna _email_ da tabela _usuario_

-- 18) Retorne somente os _artistas_ que o nome artístico começa com "Leo" (Ex: Leo Santana, Leonardo e etc.)

-- 19) Retorne o _título_ dos _álbuns_ que estão fazendo aniversário neste mês

-- 20) Retorne o _título_ dos _álbuns_ lançados no segundo semestre do ano passado (de julho de 2022 a dezembro de 2022)

-- 21) Retorne o título dos álbuns lançados nos últimos 30 dias <!-- postgres=# select current_date - interval '30 DAY';-->

-- 22) Retorne o título e o dia de lançamento (por extenso) de todos os álbuns <!-- CASE WHEN select extract(dow from current_date) -->

-- 23) Retorne o título e o mês de lançamento (por extenso) de todos os álbuns

-- 24) Retorne pelo menos um dos álbuns mais antigos

-- 25) Retorne pelo menos um dos álbuns mais recentes

-- 26) Liste os _títulos_ das _músicas_ de todos os _álbuns_ de um determinado _artista_

-- 27) Liste os _títulos_ das _músicas_ de um _álbum_ de um determinado _artista_

-- 28) Liste somente os nomes de _usuários_ que possuem alguma _playlist_ (cuidado! com a repetição)

-- 29) Liste _artistas_ que ainda não possuem _álbuns_ cadastrados

-- 30) Liste _usuários_ que ainda não possuem _playlists_ cadastradas

-- 31) Retorne a quantidade de _álbuns_ por _artista_

-- 32) Retorne a quantidade de _músicas_ por _artista_

-- 33) Retorne o _título_ das _músicas_ de uma _playlist_ de um determinado _usuário_

-- 34) Retorne a quantidade de _playlist_ de um determinado usuário

-- 35) Retone a quantidade de _músicas_ por _artista_ (de artistas que possuem pelo menos 2 _músicas_)

-- 36) Retorne os títulos de todos os álbuns lançados no mesmo ano em que o álbum mais antigo foi lançado

-- 37) Retorne os títulos de todos os álbuns lançados no mesmo ano em que o álbum mais novo foi lançado

-- 38) Retorne na mesma consulta os nomes de todos os artistas e de todos os usuários. Caso um determinado artista não tenha cadastrado seu nome, retorne seu nome artístico

-- 39) Retorne nomes das _playlists_ com e sem _músicas_

-- 40) Retorne a média da quantidade de _músicas_ de todas as _playlists_

-- 41) Retorne somente _playlists_ que possuem quantidade de músicas maior ou igual a média