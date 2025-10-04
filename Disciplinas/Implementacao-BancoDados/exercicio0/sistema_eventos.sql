DROP DATABASE IF EXISTS sistema_eventos;

CREATE DATABASE sistema_eventos;

\c sistema_eventos;

CREATE TABLE evento (
    id serial primary key,
    nome character varying(200) not null,
    data_inicio date default current_date,
    hora_inicio time default current_time,
    data_fim date,
    hora_fim time,
    local text
);

INSERT INTO evento (nome, data_fim, hora_fim, local) VALUES 
('AULA DE IOBD', '2025-06-12', '22:20', 'IFRS');

CREATE TABLE participante (
    id serial PRIMARY KEY,
    nome character varying(200) NOT NULL,
    data_nascimento date
);

INSERT INTO participante (nome, data_nascimento) VALUES
('JAAZIEL', '2002-04-23'),
('BETITO', '1900-02-01');

CREATE TABLE inscricao (
    id serial PRIMARY KEY,
    evento_id integer REFERENCES evento(id),
    participante_id integer REFERENCES participante(id),
    data_hora timestamp DEFAULT current_timestamp,
    valor money DEFAULT 0,
    pago boolean DEFAULT false,
    CHECK (cast(valor as numeric(8,2)) >= 0),
    UNIQUE (evento_id, participante_id)
);

INSERT INTO inscricao (evento_id, participante_id) VALUES
(1,1),
(1,2);

CREATE TABLE palestra(
    id serial PRIMARY KEY,
    titulo text NOT NULL,
    duracao integer CHECK (duracao > 0),
    data_hora_inicio timestamp DEFAULT current_timestamp,
    evento_id integer REFERENCES evento(id)
);

INSERT INTO palestra (titulo, duracao, evento_id) VALUES
('MODELAGEM RELACIONAL', '120', 1);
INSERT INTO palestra (titulo, duracao, evento_id) VALUES
('JAVA', '120', 1);


CREATE TABLE palestrante (
    id serial PRIMARY KEY,
    nome character varying (200) NOT NULL,
    biografia text,
    cpf character(11) NOT NULL UNIQUE
);

INSERT INTO palestrante (nome, cpf) VALUES
('IGOR AVILA PEREIRA','17633322212');

CREATE TABLE palestra_palestrante (
    palestra_id integer REFERENCES palestra(id),
    palestrante_id integer REFERENCES palestrante(id),
    PRIMARY KEY(palestra_id, palestrante_id)
);

INSERT INTO palestra_palestrante (palestra_id, palestrante_id) VALUES
(1,1);


/*--------------------------------*/
--1)
SELECT evento.nome, participante.nome FROM evento
INNER JOIN inscricao ON (evento.id = inscricao.evento_id)
INNER JOIN participante ON (participante.id = inscricao.participante_id);

SELECT evento.nome, palestra.titulo FROM evento 
INNER JOIN palestra ON (evento.id = palestra.evento_id);

--3)
SELECT palestra.titulo, palestrante.nome FROM palestra
LEFT JOIN palestra_palestrante ON (palestra.id = palestra_palestrante.palestra_id)
LEFT JOIN palestrante ON (palestrante.id = palestra_palestrante.palestrante_id);

--4)
SELECT evento.nome, count(*) FROM evento
INNER JOIN palestra ON (evento.id = palestra.evento_id)
GROUP BY evento.id;
--"VOU CONTAR POR" -> USA O "GROUP BY" (AGRUPAR POR)
--"Contar quantidade de palestra por evento, AGRUPANDO palestras no evento"

--HAVING (é um where que tu usa DEPOIS do GROUP BY)
SELECT evento.nome, count(*) FROM evento
INNER JOIN palestra ON (evento.id = palestra.evento_id)
GROUP BY evento.id
HAVING count(*) >= 2;

--5)
SELECT evento.nome, count(*) FROM evento
INNER JOIN inscricao ON (evento.id = inscricao.evento_id)
GROUP BY evento.id;




-- 1–5. JOINs Simples e Compostos
-- Liste o nome dos participantes e os nomes dos eventos em que estão inscritos.
SELECT participante.nome, evento.nome FROM evento
INNER JOIN inscricao ON (evento.id = inscricao.evento_id)
INNER JOIN participante ON (participante.id = inscricao.participante_id); 

-- Liste o nome dos eventos e os nomes das palestras associadas.
SELECT evento.nome, palestra.titulo as palestra FROM evento
INNER JOIN palestra ON (evento.id = palestra.evento_id);

-- Liste os nomes dos palestrantes e as palestras que eles ministram.
-- Liste os eventos e a quantidade de palestras que cada um possui.
-- Liste os eventos e o número total de participantes inscritos.

-- 6–10. WHERE, IN, BETWEEN
-- Liste os eventos que ocorrem entre duas datas específicas (ex: entre '2025-01-01' e '2025-12-31').
-- Liste os participantes que estão inscritos em mais de um evento.
-- Liste os palestrantes que participaram de uma palestra com o nome contendo a palavra “tecnologia”.
-- Liste as inscrições feitas em uma data específica.
-- Liste os eventos que ocorreram em um local específico (ex: 'São Paulo').

-- 11–15. Subselects
-- Liste os participantes que estão inscritos no evento com o maior número de palestras.
-- Liste as palestras com mais de um palestrante.
-- Liste os eventos que têm mais participantes do que a média de participantes por evento.
-- Liste os nomes dos palestrantes que não estão vinculados a nenhuma palestra.
-- Liste os participantes que não se inscreveram em nenhum evento.

-- 16–20. GROUP BY e HAVING
-- Liste a quantidade de inscrições por evento.
-- Liste os palestrantes que participam de mais de 3 palestras.
-- Liste os eventos com mais de 100 participantes.
-- Mostre a média de palestras por evento.
-- Liste os eventos cuja duração (data fim - data início) seja maior que 3 dias.

-- 21–25. Agregações: COUNT, AVG, MAX, MIN, SUM
-- Calcule a média de participantes por evento.
-- Calcule a média de palestras por palestrante.
-- Mostre o total de eventos que já ocorreram (data fim < data atual).
-- Mostre a quantidade de palestras realizadas em cada mês.
-- Calcule a idade média (em dias) das inscrições feitas até hoje.

-- 26–30. CASE WHEN, COALESCE, UNION, INTERSECT
-- Liste todos os eventos e, ao lado, mostre "Encerrado" se a data fim for menor que hoje, "Em andamento" se estiver entre início e fim, e "Futuro" se ainda não começou.
-- Liste os nomes das palestras e, ao lado, mostre “Sem palestrante” caso nenhuma pessoa esteja associada a ela.
-- Liste os participantes e mostre “Inscrito” se estiverem inscritos em algum evento e “Não inscrito” caso contrário.
-- Liste os nomes das pessoas que são tanto palestrantes quanto participantes.
-- Liste todos os nomes de pessoas envolvidas no sistema, sejam palestrantes ou participantes (sem duplicar nomes).

-- 31–35. ORDER BY, LIMIT, OFFSET
-- Liste os 5 eventos mais recentes (ordenados pela data de início decrescente).
-- Liste os 10 participantes mais antigos no sistema (ordenado pela data de inscrição mais antiga).
-- Liste os 3 palestras com o maior número de palestrantes.
-- Mostre os 5 eventos com maior número de participantes, ordenando do maior para o menor.
-- Mostre a 2ª página de participantes com 10 resultados por página (use LIMIT e OFFSET).

-- 36–40. JOINs Variados, LIKE, Filtros
-- Liste todos os eventos e, se houver, o número de palestras associadas (use LEFT JOIN).
-- Liste todas as palestras, mesmo que não tenham palestrantes ainda associados.
-- Liste todos os palestrantes e, se houver, as palestras que ministram (use RIGHT JOIN).
-- Encontre os participantes cujos nomes começam com a letra 'A'.
-- Liste as palestras cujo nome contém a palavra "dados" (insensível a maiúsculas).

-- 41–45. Funções de Agregação Avançadas
-- Liste o total de palestras ministradas por cada palestrante, ordenando da maior para a menor.
-- Mostre o evento com a maior quantidade de palestras.
-- Liste o número total de inscrições feitas no mês atual.
-- Calcule a soma total de palestras realizadas em todos os eventos.
-- Liste o evento com a menor duração (data fim - data início).

-- 46–50. EXCEPT, IS NULL, NOT IN, Subselects
-- Liste os participantes que não estão inscritos em nenhum evento.
-- Liste as palestras sem palestrante associado.
-- Liste os eventos que não têm nenhuma palestra associada.
-- Liste os nomes dos palestrantes que não participam de nenhuma palestra cujo nome contenha "inovação".
-- Liste os participantes que se inscreveram em todos os eventos que ocorreram em 2025.
