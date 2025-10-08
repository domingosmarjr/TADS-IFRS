DROP DATABASE IF EXISTS sistema_eventos;

CREATE DATABASE sistema_eventos;

\c sistema_eventos;

CREATE TABLE evento (
    id serial PRIMARY KEY,
    nome character varying(200) NOT NULL,
    data_inicio date DEFAULT current_date,
    hora_inicio time DEFAULT current_time,
    data_fim date,
    hora_fim time,
    local text
);

CREATE TABLE participante (
    id serial PRIMARY KEY,
    nome character varying(200) NOT NULL,
    data_nascimento date
);

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

CREATE TABLE palestra (
    id serial PRIMARY KEY,
    titulo text NOT NULL,
    duracao integer CHECK (duracao > 0),
    data_hora_inicio timestamp DEFAULT current_timestamp,
    evento_id integer REFERENCES evento(id)
);

CREATE TABLE palestrante (
    id serial PRIMARY KEY,
    nome character varying(200) NOT NULL,
    biografia text,
    cpf character(11) NOT NULL UNIQUE
);

CREATE TABLE palestra_palestrante (
    palestra_id INTEGER REFERENCES palestra(id),
    palestrante_id INTEGER references palestrante(id),
    PRIMARY KEY (palestra_id, palestrante_id)
);

-- INSERTS DA AULA DE IOBD - EXEMPLOS USADOS EM AULA

-- INSERT INTO evento (nome, data_fim, hora_fim, local) VALUES ('AULA DE IOBD', '2025-08-12', '22:20', 'IFRS');
-- INSERT INTO evento(nome, local) VALUES ('AULA DE IOBD - Na Chuva', 'IFRS');
-- INSERT INTO participante (nome, data_nascimento) VALUES
-- ('JAAZIEL', '2002-04-23'),
-- ('BETITO', '1900-01-01');
-- INSERT INTO inscricao (evento_id, participante_id) VALUES
-- (1,1),
-- (1,2),
-- (2,1);
-- INSERT INTO palestra (titulo, duracao, evento_id) VALUES
-- ('MODELAGEM RELACIONAL', 120, 1),
-- ('JAVA', 120, 1),
-- ('BD', 120, 2),
-- ('OO', 120, 2),
-- ('WEB', 120, 2),
-- ('DADOS', 120, 2),
-- ('OS DADOS', 120, 2),
-- ('OS DADOS 2', 120, 3),
-- ('OS DADOS 3', 120, 3),
-- ('OS DADOS 4', 120, 3),
-- ('OS DADOS 5', 120, 3),
-- ('OS DADOS 6', 120, 3),
-- ('OS DADOS 7', 120, 3),
-- ('OS DADOS 8', 120, 2);
-- INSERT INTO palestrante (nome, cpf) VALUES
-- ('IGOR AVILA PEREIRA', '12345678910'),
-- ('MARCIO JOSUE RAMOS TORRES', '12345678911'),
-- ('RAQUEL BARBOSA', '12345678912');
-- INSERT INTO palestra_palestrante (palestra_id, palestrante_id) VALUES
-- (1,1),
-- (1,2);

-- INSERTS COM DADOS DIFERENTES PARA DESENVOLVER O TRABALHO
-- ________________________                 _______________
--                         |               |
-- (1) Alice               |               |    (1)ModeloER       -> (1)Igor
-- (2) Angela              |(1)Curso Banco |    (2)Postgres       -> (2)Andrew
-- (3) Ana                 |               |    (3)MySQL          -> (3)Michael
-- (4) Aninha              |               |_______________
-- (5) Anastacia           |
-- ________________________|
-- ________________________                 _______________
--                         |               |
-- (6) Bruna               |               |    (4)Polimorfismo   -> (4)Marcio
-- (7) Bianca              |(2)Curso POO   |    (5)Herança        -> (5)Vinicius
-- (8) Bela                |               |    (6)Encapsulamento -> (6)Betito
-- (9) Bia                 |               |_______________       -> (7)Wendel
-- (10)Beatriz             |
-- (11)Bruninha            |                                         (8)Luciano
-- ________________________|
-- (12)Marcio Torres        (3)Curso Redes

INSERT INTO participante (nome, data_nascimento) VALUES
('Alice', '1980-01-01'),
('Angela', '1985-01-01'),
('Ana Lucia', '1990-01-01'),
('Aninha', '1995-01-01'),
('Anastacia', '2000-01-01'),
('Bruna', '2005-01-01'),
('Bianca', '2007-01-01'),
('Bela', '2010-01-01'),
('Bia', '2011-01-01'),
('Beatriz', '2015-01-01'),
('Bruninha', '1996-01-09'),
('Belinha', '1995-02-01'),
('Marcio Torres','1980-10-02');


INSERT INTO evento (nome, data_inicio, hora_inicio, data_fim, hora_fim, local) VALUES
('Curso de Banco de Dados','2025-09-01','13:00','2025-09-30','18:00','IFRS'),
('Curso de Orientação Objetos', '2025-10-01', '13:00', '2025-10-31', '18:00', 'FURG'),
('Curso de Redes', '2025-11-01','13:00','2025-11-20','18:00','UFPEL');

INSERT INTO palestra (titulo, duracao, data_hora_inicio, evento_id) VALUES
('Modelo ER',120,'2025-09-02 15:00',1),
('Postgres',120,'2025-09-10 15:00',1),
('MySQL',120,'2025-09-20 15:00',1),
('Polimorfismo',120,'2025-10-01 15:00',2),
('Herança',120,'2025-10-10 15:00',2),
('Encapsulamento',120,'2025-10-20 15:00',2);
('Abstração',120,'2025-10-23 15:00',2);

INSERT INTO palestrante (nome, biografia, cpf) VALUES 
('Igor Avila','Professor do IFRS de Banco','12345678910'),
('Andrew Yu','Criador do PSQL','12345678911'),
('Michael Widenius','Criador do MySQL','12345678912'),
('Marcio Torres','Professor do IFRS de Modular','12345678913'),
('Vinicius Machado','Professor do IFRS de POO','12345678914'),
('Rafael Betito','Professor do IFRS de Logica','12345678915'),
('Wendel Brião','Professor do IFRS de SO','12345678916'),
('Luciano Gonçalves','Professor do IFRS de Redes','12345678917');

INSERT INTO inscricao (evento_id, participante_id) VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(2,6),
(2,7),
(2,8),
(2,9),
(2,10),
(2,11);

INSERT INTO palestra_palestrante (palestra_id, palestrante_id) VALUES
(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6),
(6,7);




-- -- EXERCÍCIOS DE CONSULTA

-- -- 1–5. JOINs Simples e Compostos
-- -- 1)Liste o nome dos participantes e os nomes dos eventos em que estão inscritos.
-- SELECT participante.nome, evento.nome FROM participante
-- INNER JOIN inscricao ON participante.id = inscricao.participante_id
-- INNER JOIN evento ON evento.id = inscricao.evento_id;

-- -- 2)Liste o nome dos eventos e os nomes das palestras associadas.
-- SELECT evento.nome, palestra.titulo FROM evento
-- INNER JOIN palestra ON evento.id = palestra.evento_id;

-- -- 3)Liste os nomes dos palestrantes e as palestras que eles ministram.
-- SELECT palestrante.nome, palestra.titulo FROM palestrante
-- INNER JOIN palestra_palestrante ON palestrante.id = palestra_palestrante.palestrante_id
-- INNER JOIN palestra ON palestra.id = palestra_palestrante.palestra_id;

-- -- 4)Liste os eventos e a quantidade de palestras que cada um possui.
-- SELECT evento.nome, count(*) FROM evento
-- INNER JOIN palestra ON evento.id = palestra.evento_id
-- GROUP BY evento.nome;

-- -- 5)Liste os eventos e o número total de participantes inscritos.
-- SELECT evento.nome, count(*) AS inscritos FROM evento
-- INNER JOIN inscricao ON evento.id = inscricao.evento_id
-- GROUP BY evento.nome;

-- -- 6-10 - WHERE, IN E BETWEEN
-- -- 6)Liste os eventos que ocorrem entre duas data específicas(ex:2025-01-01 e 2025-12-31);
-- SELECT evento.nome, evento.data_inicio, evento.data_fim FROM evento
-- WHERE data_inicio >= '2025-09-01' AND data_fim <= '2025-09-30';

-- SELECT evento.nome, evento.data_inicio, evento.data_fim FROM evento WHERE 
-- (data_inicio BETWEEN '2025-08-31' AND '2025-09-02') AND
-- (data_fim BETWEEN '2025-09-25' AND '2025-10-01');

-- -- 7)Liste os participantes que estão inscritos em mais de um evento
-- SELECT participante.nome, count(*) FROM participante
-- INNER JOIN inscricao ON participante.id = inscricao.participante_id
-- GROUP BY participante.id HAVING (count(*) > 1);

-- -- 8)Liste os palestrantes que participaram de uma palestra com o nome contendo a palavra “tecnologia”.
-- SELECT palestrante.nome, palestra.titulo FROM palestrante
-- INNER JOIN palestra_palestrante ON palestrante.id = palestra_palestrante.palestrante_id
-- INNER JOIN palestra ON palestra_palestrante.palestra_id = palestra.id
-- WHERE palestra.titulo ILIKE '%tecnologia%';

-- -- 9)Liste as inscrições feitas em uma data específica.
-- SELECT participante.nome, evento.nome, inscricao.data_hora FROM participante
-- INNER JOIN inscricao ON participante.id = inscricao.participante_id
-- INNER JOIN evento ON inscricao.evento_id = evento.id
-- WHERE (cast(inscricao.data_hora as date)) = '2025-01-01';

-- -- 10)Liste os eventos que ocorreram em um local específico (ex: 'São Paulo').
-- SELECT evento.nome, evento.local FROM evento WHERE evento.local = 'São Paulo';

-- -- 11–15. Subselects
-- -- 11)Liste os participantes que estão inscritos no evento com o maior número de palestras.
-- SELECT participante.nome, count(palestra.id) AS qnt_palestra FROM participante 
-- INNER JOIN inscricao ON participante.id = inscricao.participante_id
-- INNER JOIN evento ON evento.id = inscricao.evento_id
-- INNER JOIN palestra ON evento.id = palestra.evento_id
-- GROUP BY participante.nome HAVING count(evento.nome) = (
--     SELECT count(palestra.titulo) FROM evento
--     INNER JOIN palestra ON evento.id = palestra.evento_id
--     GROUP BY evento.nome ORDER BY count(palestra.titulo) DESC LIMIT 1    
-- );
--     -- Retorna o evento com mais palestras
--     SELECT count(palestra.titulo) FROM evento
--     INNER JOIN palestra ON evento.id = palestra.evento_id
--     GROUP BY evento.nome ORDER BY count(palestra.titulo) DESC LIMIT 1;

-- -- 12) Liste as palestras com mais de um palestrante.
-- SELECT palestra.titulo, count(palestra_palestrante.palestra_id) FROM palestra
-- INNER JOIN palestra_palestrante ON palestra.id = palestra_palestrante.palestra_id
-- GROUP BY palestra.titulo HAVING count(palestra_palestrante.palestra_id) > 1;

-- -- 13) Liste os eventos que têm mais participantes do que a média de participantes por evento.
-- SELECT evento.nome, count(participante_id) AS qnt_participante FROM inscricao
-- INNER JOIN evento ON inscricao.evento_id = evento.id
-- GROUP BY evento.nome HAVING count(participante_id) > (
--     SELECT avg(inscricao.participante_id) FROM inscricao
-- ) ORDER BY qnt_participante DESC ;

--     -- -> Média de participantes por evento
--         SELECT avg(inscricao.participante_id) FROM inscricao;

-- -- 14) Liste os nomes dos palestrantes que não estão vinculados a nenhuma palestra.
-- SELECT palestrante.nome FROM palestrante
-- LEFT JOIN palestra_palestrante ON palestrante.id = palestra_palestrante.palestrante_id
-- LEFT JOIN palestra ON palestra.id = palestra_palestrante.palestra_id
-- WHERE palestra.titulo IS NULL;

-- -- 15) Liste os participantes que não se inscreveram em nenhum evento.
-- SELECT participante.nome FROM participante
-- LEFT JOIN inscricao ON participante.id = inscricao.participante_id
-- LEFT JOIN evento ON inscricao.evento_id = evento.id
-- WHERE evento.id IS NULL;

-- -- 16–20. GROUP BY e HAVING
-- -- 16) Liste a quantidade de inscrições por evento.
-- SELECT evento.nome, count(inscricao.participante_id) FROM evento
-- INNER JOIN inscricao ON inscricao.evento_id = evento.id
-- GROUP BY evento.nome ORDER BY evento.nome;

-- -- 17) Liste os palestrantes que participam de mais de 3 palestras.
-- SELECT palestrante.nome, count(palestra_palestrante.palestra_id) FROM palestrante
-- LEFT JOIN palestra_palestrante ON palestrante.id = palestra_palestrante.palestrante_id
-- GROUP BY palestrante.nome HAVING count(palestra_palestrante.palestra_id) > 3;

-- -- 18) Liste os eventos com mais de 100 participantes.
-- SELECT evento.nome, count(inscricao.participante_id) as qnt_participante FROM evento
-- LEFT JOIN inscricao ON evento.id = inscricao.evento_id
-- GROUP BY evento.nome HAVING count(inscricao.participante_id) > 100;

-- -- 19) Mostre a média de palestras por evento.
--     -- Quantidade de palestra por evento
--     SELECT evento.nome,count(palestra.id) FROM evento
--     LEFT JOIN palestra ON evento.id = palestra.evento_id
--     GROUP BY evento.id;

-- SELECT avg(quantidade) FROM (
--     SELECT count(palestra.id) as quantidade FROM evento
--     LEFT JOIN palestra ON evento.id = palestra.evento_id
--     GROUP BY evento.id
-- );

-- -- 20) Liste os eventos cuja duração (data fim - data início) seja maior que 3 dias.
-- SELECT evento.nome, evento.data_inicio, evento.data_fim, (evento.data_fim - evento.data_inicio) 
-- AS qnt_dias_evento FROM evento WHERE (evento.data_fim - evento.data_inicio) > 3;

-- -- 21–25. Agregações: COUNT, AVG, MAX, MIN, SUM
-- -- 21) Calcule a média de participantes por evento.
-- SELECT avg(media) FROM (
--     SELECT count(inscricao.participante_id) AS media FROM inscricao
--     GROUP BY inscricao.evento_id
-- );

-- -- 22) Calcule a média de palestras por palestrante.
-- SELECT avg(media) FROM (
--     SELECT count(palestra_palestrante.palestra_id) AS media FROM
--     palestra_palestrante GROUP BY palestra_palestrante.palestrante_id
-- );

-- -- 23) Mostre o total de eventos que já ocorreram (data fim < data atual).
-- SELECT count(evento.id) AS qnt_eventos FROM evento WHERE evento.data_inicio < current_date;

-- -- 24) Mostre a quantidade de palestras realizadas em cada mês.
-- SELECT extract(month from palestra.data_hora_inicio) AS mes, count(palestra.id) AS qnt_palestra
-- FROM palestra GROUP BY extract(month from palestra.data_hora_inicio) ORDER BY mes ASC;

-- -- NÃO ENTENDI!
-- -- 25) Calcule a idade média (em dias) das inscrições feitas até hoje.
-- SELECT avg(extract(days from(current_date - inscricao.data_hora))) FROM inscricao;


-- -- 26–30. CASE WHEN, COALESCE, UNION, INTERSECT
-- -- 26) Liste todos os eventos e, ao lado, mostre "Encerrado" se a data fim for menor que hoje, "Em andamento" se estiver entre início e fim, e "Futuro" se ainda não começou.
-- SELECT evento.nome,
-- CASE
--     WHEN evento.data_fim < current_date THEN 'Encerrado'
--     WHEN evento.data_fim >= current_date AND evento.data_inicio <= current_date THEN 'Andamento'
--     WHEN evento.data_inicio > current_date THEN 'Futuro'
-- END as status
-- FROM evento;

-- -- 27) Liste os nomes das palestras e, ao lado, mostre “Sem palestrante” caso nenhuma pessoa esteja associada a ela.
-- SELECT palestra.titulo,
-- CASE
--     WHEN count(palestra_palestrante.palestra_id) > 0 THEN 'Com palestrante'
--     ELSE 'Sem palestrante'
-- END as status
-- FROM palestra_palestrante INNER JOIN palestra ON palestra_palestrante.palestra_id = palestra.id 
-- GROUP BY palestra.titulo ORDER BY palestra.titulo ASC;

-- -- 28) Liste os participantes e mostre “Inscrito” se estiverem inscritos em algum evento e “Não inscrito” caso contrário.
-- SELECT participante.nome,
-- CASE
--     WHEN inscricao.participante_id IS NULL THEN 'Não inscrito'
--     ELSE 'Inscrito'
-- END AS status 
-- FROM participante
-- LEFT JOIN inscricao ON participante.id = inscricao.participante_id;

-- -- 29) Liste os nomes das pessoas que são tanto palestrantes quanto participantes.
-- SELECT palestrante.nome FROM palestrante
-- INTERSECT
-- SELECT participante.nome FROM participante;

-- -- 30) Liste todos os nomes de pessoas envolvidas no sistema, sejam palestrantes ou participantes (sem duplicar nomes).
-- SELECT participante.nome FROM participante
-- UNION
-- SELECT palestrante.nome FROM palestrante
-- ORDER BY nome ASC;

-- -- 31–35. ORDER BY, LIMIT, OFFSET
-- -- 31) Liste os 5 eventos mais recentes (ordenados pela data de início decrescente).
-- SELECT * FROM evento ORDER BY data_inicio DESC;
-- SELECT evento.nome, evento.data_inicio FROM evento ORDER BY data_inicio DESC;

-- -- 32) Liste os 10 participantes mais antigos no sistema (ordenado pela data de inscrição mais antiga).
-- SELECT participante.nome, inscricao.data_hora FROM inscricao
-- INNER JOIN participante ON inscricao.participante_id = participante.id
-- ORDER BY data_hora DESC LIMIT 10;

-- -- 33) Liste os 3 palestras com o maior número de palestrantes.
-- SELECT palestra.titulo, count(palestrante_id) FROM palestra_palestrante
-- INNER JOIN palestra ON palestra_palestrante.palestra_id = palestra.id
-- GROUP BY palestra.titulo ORDER BY count(palestrante_id) DESC LIMIT 3;

-- -- 34) Mostre os 5 eventos com maior número de participantes, ordenando do maior para o menor.
-- SELECT evento.nome, count(participante_id) FROM inscricao
-- INNER JOIN evento ON evento.id = inscricao.evento_id
-- GROUP BY evento.nome ORDER BY count(participante_id)
-- DESC LIMIT 5;


-- -- 35) Mostre a 2ª página de participantes com 10 resultados por página (use LIMIT e OFFSET).
-- SELECT participante.nome FROM participante LIMIT 10 OFFSET 10;

-- -- 36–40. JOINs Variados, LIKE, Filtros
-- -- 36) Liste todos os eventos e, se houver, o número de palestras associadas (use LEFT JOIN).
-- SELECT evento.nome, count(palestra.evento_id) AS num_palestras FROM evento
-- LEFT JOIN palestra ON evento.id = palestra.evento_id GROUP BY evento.nome;

-- -- 37) Liste todas as palestras, mesmo que não tenham palestrantes ainda associados.
-- SELECT palestra.titulo, count(palestra_palestrante.palestrante_id) FROM palestra
-- LEFT JOIN palestra_palestrante ON palestra.id = palestra_palestrante.palestra_id
-- GROUP BY palestra.titulo ORDER BY count(palestra_palestrante.palestrante_id) DESC;

-- -- 38) Liste todos os palestrantes e, se houver, as palestras que ministram (use RIGHT JOIN).
-- SELECT palestra.titulo,palestrante.nome FROM palestrante
-- RIGHT JOIN palestra_palestrante ON palestra_palestrante.palestrante_id = palestrante.id
-- RIGHT JOIN palestra ON palestra.id = palestra_palestrante.palestra_id;

-- -- 39) Encontre os participantes cujos nomes começam com a letra 'A'.
-- SELECT participante.nome FROM participante WHERE participante.nome ILIKE 'a%';

-- -- 40) Liste as palestras cujo nome contém a palavra "dados" (insensível a maiúsculas).
-- SELECT palestra.titulo FROM palestra WHERE palestra.titulo ILIKE '%dados%';

-- -- 41–45. Funções de Agregação Avançadas
-- -- 41) Liste o total de palestras ministradas por cada palestrante, ordenando da maior para a menor.
-- SELECT palestrante.nome, count(palestra_palestrante.palestra_id) FROM palestra_palestrante
-- LEFT JOIN palestrante ON palestrante.id = palestra_palestrante.palestrante_id 
-- GROUP BY palestrante.nome ORDER BY count(palestra_palestrante.palestra_id) DESC;

-- -- 42) Mostre o evento com a maior quantidade de palestras.
-- SELECT evento.nome, count(palestra.id) FROM evento
-- LEFT JOIN palestra ON evento.id = palestra.evento_id
-- GROUP BY evento.nome ORDER BY count(palestra.id) DESC LIMIT 1;

-- -- 43) Liste o número total de inscrições feitas no mês atual.
-- SELECT count(data_hora) FROM inscricao
-- WHERE EXTRACT(MONTH FROM data_hora) = EXTRACT(MONTH FROM current_date);

-- -- 44) Calcule a soma total de palestras realizadas em todos os eventos.
-- SELECT count(palestra.id) FROM evento
-- LEFT JOIN palestra ON evento.id = palestra.evento_id;

-- -- 45) Liste o evento com a menor duração (data fim - data início).
-- SELECT evento.nome, (data_fim - data_inicio) AS duracao_dias
-- FROM evento ORDER BY data_fim - data_inicio ASC;

-- -- 46–50. EXCEPT, IS NULL, NOT IN, Subselects
-- -- 46) Liste os participantes que não estão inscritos em nenhum evento.
-- SELECT participante.nome FROM participante
-- EXCEPT
-- SELECT participante.nome FROM participante
-- INNER JOIN inscricao ON participante.id = inscricao.participante_id;
-- -- !DÚVIDA! -> "E se tiverem o mesmo nome? Consigo fazer por participante.id, mas não
--                 -- consigo mostrar o nome."

-- -- 47) Liste as palestras sem palestrante associado.
-- SELECT palestra.titulo FROM palestra LEFT JOIN palestra_palestrante
-- ON palestra.id = palestra_palestrante.palestra_id
-- WHERE palestra_palestrante.palestrante_id IS NULL;

-- -- 48) Liste os eventos que não têm nenhuma palestra associada.
-- SELECT evento.nome FROM evento 
-- LEFT JOIN palestra ON evento.id = palestra.evento_id
-- WHERE palestra.evento_id IS NULL;

-- -- 49) Liste os nomes dos palestrantes que não participam de nenhuma palestra cujo nome contenha "inovação".
-- SELECT palestrante.nome FROM palestrante
-- WHERE palestrante.id NOT IN (
--     SELECT palestra_palestrante.palestrante_id FROM palestra_palestrante
--     INNER JOIN palestra ON palestra.id = palestra_palestrante.palestra_id
--     WHERE palestra.titulo ILIKE '%inovação%'
-- );

-- -- 50) Liste os participantes que se inscreveram em todos os eventos que ocorreram em 2025.
-- SELECT participante.nome, extract(year from inscricao.data_hora) FROM participante
-- LEFT JOIN inscricao ON participante.id = inscricao.participante_id
-- WHERE extract(year from inscricao.data_hora) = 2025;

-- -- 51–55. Schemas e Views
-- -- 51) Crie um schema chamado relatorios e dentro dele uma tabela eventos_passados contendo apenas os eventos já encerrados.
-- CREATE SCHEMA relatorios;
-- SET search_path TO public, relatorios;
-- CREATE TABLE relatorios.eventos_passados AS
-- SELECT * FROM evento WHERE data_fim < current_date;

-- -- 52) Crie uma view que mostre os eventos futuros com suas respectivas palestras.
-- CREATE VIEW eventos_futuros AS
-- SELECT evento.nome, evento.data_inicio, count(palestra.id) FROM evento
-- LEFT JOIN palestra ON evento.id = palestra.evento_id
-- WHERE evento.data_inicio > current_date
-- GROUP BY evento.nome, evento.data_inicio;

-- SELECT * FROM eventos_futuros;

-- -- 53) Crie uma view que liste os participantes e o número de eventos em que estão inscritos.
-- CREATE VIEW participante_evento AS
-- SELECT participante.nome, count(inscricao.evento_id) FROM participante
-- INNER JOIN inscricao ON participante.id = inscricao.participante_id
-- INNER JOIN evento ON inscricao.evento_id = evento.id
-- GROUP BY participante.nome;

-- SELECT * FROM participante_evento;

-- -- 54) Crie uma view que mostre palestrantes e a soma total de palestras ministradas por cada um.
-- CREATE VIEW totalPalestrasDePalestrantes AS 
-- SELECT palestrante.nome, count(palestra.id) FROM palestrante
-- LEFT JOIN palestra_palestrante ON palestra_palestrante.palestrante_id = palestrante.id
-- LEFT JOIN palestra ON palestra_palestrante.palestra_id = palestra.id
-- GROUP BY palestrante.nome
-- ORDER BY palestrante.nome ASC;

-- SELECT * FROM totalPalestrasDePalestrantes;
-- -- 55) Crie uma consulta que utilize uma view para exibir apenas os eventos que têm mais de 50 inscrições.
-- CREATE VIEW eventosMais50 AS
-- SELECT evento.id, count(inscricao.participante_id) FROM evento
-- LEFT JOIN inscricao ON evento.id = inscricao.evento_id
-- GROUP BY evento.id HAVING count(inscricao.participante_id) > 50
-- ORDER BY evento.id ASC;

-- -- 56–60. UNION, INTERSECT, EXCEPT
-- -- 56) Liste todos os nomes de pessoas que são participantes ou palestrantes (use UNION).
-- SELECT participante.nome FROM participante
-- UNION
-- SELECT palestrante.nome FROM palestrante
-- ORDER BY nome ASC;

-- -- 57) Liste apenas os nomes de pessoas que aparecem tanto como palestrantes quanto como participantes (use INTERSECT).
-- SELECT participante.nome FROM participante
-- INTERSECT
-- SELECT palestrante.nome FROM palestrante;

-- -- 58) Liste os participantes que não são palestrantes (use EXCEPT).
-- SELECT participante.nome FROM participante
-- EXCEPT
-- SELECT palestrante.nome FROM palestrante;

-- -- 59) Liste os palestrantes que não são participantes (use EXCEPT).
-- SELECT palestrante.nome FROM palestrante
-- EXCEPT
-- SELECT participante.nome FROM participante;

-- -- 60) Mostre os nomes de todos os palestrantes e participantes em uma única lista, indicando a função (coluna “Tipo”).
-- SELECT participante.nome AS tipo FROM participante
-- UNION
-- SELECT palestrante.nome AS tipo FROM palestrante
-- ORDER BY tipo ASC;

-- -- 61–65. Subselects
-- -- 61) Liste os eventos que possuem mais palestras do que a média geral de palestras por evento.
-- SELECT evento.nome, count(palestra.id) qnt_palestra FROM evento
-- LEFT JOIN palestra ON evento.id = palestra.evento_id
-- GROUP BY evento.id HAVING count(palestra.id) > (
--     -- media de cada evento separada
--     SELECT avg(quantidade) AS media_evento FROM (
--         --quantidade de palestra/evento separada 
--         SELECT count(palestra.id) AS quantidade FROM evento
--         LEFT JOIN palestra ON evento.id = palestra.evento_id
--         GROUP BY palestra.id
--     )
-- );

-- -- 62) Liste os palestrantes que ministram palestras apenas em eventos que ocorreram em 2025.
-- -- nessa primeira, não garante que participaram apenas de palestras de 25
-- SELECT palestrante.nome, evento.data_inicio, evento.data_fim FROM palestrante
-- LEFT JOIN palestra_palestrante ON palestrante.id = palestra_palestrante.palestrante_id
-- LEFT JOIN palestra ON palestra_palestrante.palestra_id = palestra.id
-- LEFT JOIN evento ON palestra.evento_id = evento.id
-- WHERE EXTRACT(YEAR FROM evento.data_inicio) >= 2025 AND EXTRACT(YEAR FROM evento.data_fim) <= 2025;

-- -- nessa segunda, pega os de 25, mesmo que tenham participado de outros anos
-- SELECT palestrante.nome FROM palestrante
-- LEFT JOIN palestra_palestrante ON palestrante.id = palestra_palestrante.palestrante_id
-- LEFT JOIN palestra ON palestra_palestrante.palestra_id = palestra.id
-- LEFT JOIN evento ON palestra.evento_id = evento.id
-- GROUP BY palestrante.id, palestra.titulo
-- HAVING MIN(EXTRACT(YEAR FROM evento.data_inicio)) = 2025
-- AND MAX(EXTRACT(YEAR FROM evento.data_fim)) = 2025;

-- -- 63) Liste os participantes que estão em eventos que possuem mais de 5 palestras.
-- SELECT participante.nome FROM inscricao
-- LEFT JOIN participante ON inscricao.participante_id = participante.id
-- WHERE inscricao.evento_id IN (
--     SELECT evento.id FROM evento
--     LEFT JOIN palestra ON evento.id = palestra.evento_id
--     GROUP BY evento.id HAVING count(palestra.id) > 5  
-- ) ORDER BY participante.nome ASC;

-- -- Retorna eventos que tem + 5 palestras e seus inscritos
-- SELECT inscricao.participante_id ,inscricao.evento_id FROM inscricao
-- WHERE inscricao.evento_id IN (
--     SELECT evento.id FROM evento
--     LEFT JOIN palestra ON evento.id = palestra.evento_id
--     GROUP BY evento.id HAVING count(palestra.id) > 5
-- ) ORDER BY inscricao.participante_id ASC;

-- -- Eventos com +5 Palestras
-- SELECT evento.id FROM evento
-- LEFT JOIN palestra ON evento.id = palestra.evento_id
-- GROUP BY evento.id HAVING count(palestra.id) > 5;

-- -- 64) Liste os eventos que têm exatamente o mesmo número de participantes que o evento de ID = 1.
-- SELECT evento.nome, count(inscricao.participante_id) FROM evento
-- LEFT JOIN inscricao ON evento.id = inscricao.evento_id
-- GROUP BY evento.id, evento.nome HAVING count(inscricao.participante_id) = (
--     SELECT count(inscricao.participante_id) FROM inscricao
--     WHERE inscricao.evento_id = 1
-- ) AND evento.id != 1;

-- -- 65) Liste os participantes que estão inscritos em menos eventos do que a média de inscrições por participante.

--     -- quantidade de eventos que tem cada inscrito
--     SELECT participante_id, count(evento_id) AS media_evento FROM inscricao GROUP BY participante_id
--     ORDER BY participante_id ASC;

--     -- media de inscricoes/evento
--     SELECT avg(media) AS media_inscricoes_por_evento FROM (
--         SELECT count(evento_id) AS media FROM inscricao GROUP BY participante_id
--     );

--     -- quantidade de eventos que cada participante tá inscrito
--     SELECT participante.nome, count(inscricao.evento_id) AS qnt_eventos FROM participante
--     JOIN inscricao ON participante.id = inscricao.participante_id
--     GROUP BY participante.id, participante.nome
--     HAVING count(inscricao.evento_id) > 0 AND count(inscricao.evento_id) < (
--         SELECT avg(media) AS media_inscricoes_por_evento FROM (
--             SELECT count(evento_id) AS media FROM inscricao GROUP BY participante_id
--         )
--     );

-- -- 66–70. GROUP BY + HAVING
-- -- 66) Liste os locais (cidades) que já receberam mais de 3 eventos.
-- SELECT evento.local, count(evento.nome) AS qnt_eventos FROM evento
-- GROUP BY evento.local HAVING count(evento.nome) > 3;

-- -- 67) Liste os anos em que ocorreram mais de 10 palestras.
-- SELECT EXTRACT(YEAR FROM data_hora_inicio), count(palestra.titulo) FROM palestra
-- GROUP BY EXTRACT(YEAR FROM data_hora_inicio) HAVING count(palestra.titulo) > 10;

-- -- 68) Liste os palestrantes que participaram de palestras em mais de um evento diferente.
-- SELECT palestrante.nome, count(evento.nome) FROM palestrante
-- LEFT JOIN palestra_palestrante ON palestra_palestrante.palestrante_id = palestrante.id
-- LEFT JOIN palestra ON palestra.id = palestra_palestrante.palestra_id
-- LEFT JOIN evento ON palestra.evento_id = evento.id
-- GROUP BY palestrante.id HAVING count(evento.nome) > 1;

-- -- 69) Liste os eventos que possuem pelo menos 2 palestras com múltiplos palestrantes.
-- SELECT evento.nome, count(*) AS qnt_palestra_multiplos_participantes FROM evento
-- INNER JOIN palestra ON palestra.evento_id = evento.id
-- INNER JOIN palestra_palestrante ON palestra_palestrante.palestra_id = palestra.id
-- GROUP BY evento.id, evento.nome, palestra.id
-- HAVING count(palestra_palestrante.palestra_id) > 1;

-- -- 70) Liste os participantes que se inscreveram em todos os eventos realizados em sua cidade.
-- SELECT p.nome, e.local
-- FROM participante p
-- JOIN inscricao i ON p.id = i.participante_id
-- JOIN evento e ON i.evento_id = e.id
-- GROUP BY p.id, p.nome, e.local
-- HAVING COUNT(DISTINCT i.evento_id) = (
--     SELECT COUNT(*)
--     FROM evento ev
--     WHERE ev.local = e.local
-- );

-- -- 71–75. CASE WHEN e COALESCE
-- -- 71) Liste todos os participantes e mostre “VIP” se estiverem em mais de 5 eventos, senão “Regular”.
-- SELECT participante.nome,
-- CASE
--     WHEN count(inscricao.evento_id) > 5 THEN 'VIP'
--     ELSE 'Regular'
-- END
-- FROM participante INNER JOIN inscricao ON participante.id = inscricao.participante_id
-- GROUP BY participante.id;

-- -- 72) Liste os eventos e mostre “Grande Evento” se tiver mais de 100 inscrições, “Médio” entre 50–100 e “Pequeno” se tiver menos de 50.
-- SELECT evento.nome, 
-- CASE
--     WHEN count(inscricao.participante_id) > 100 THEN 'Grande Evento'
--     WHEN count(inscricao.participante_id) >= 50 AND count(inscricao.participante_id) < 100 THEN 'Médio'
--     ELSE 'Pequeno'
-- END
-- FROM evento INNER JOIN inscricao ON inscricao.evento_id = evento.id
-- GROUP BY evento.id;

-- -- 73) Liste todas as palestras e, caso não tenham palestrante, mostre “A Definir” na coluna de palestrante.
-- SELECT palestra.titulo, 
-- CASE 
--     WHEN count(palestra_palestrante.palestrante_id) = 0 THEN 'A Definir'
--     ELSE palestrante.nome
-- END AS palestrante
-- FROM palestra LEFT JOIN palestra_palestrante ON palestra_palestrante.palestra_id = palestra.id
-- LEFT JOIN palestrante ON palestra_palestrante.palestrante_id = palestrante.id
-- GROUP BY palestra.id, palestrante.id ORDER BY palestra.id ASC;

-- -- 74) Liste os participantes e mostre o número de eventos inscritos, mas se for NULL, exiba 0 (use COALESCE).
-- SELECT participante.id, COALESCE((
--     CASE
--         WHEN count(inscricao.evento_id) = 0 THEN NULL
--         ELSE count(inscricao.evento_id)
--     END),0) FROM participante
-- LEFT JOIN inscricao ON participante.id = inscricao.participante_id
-- GROUP BY participante.id ORDER BY participante.id ASC;

-- -- 75) Liste os palestrantes e classifique-os como “Ativo” se já ministraram palestra neste ano, senão “Inativo”.
-- SELECT palestrante.id,
--     CASE
--         WHEN EXTRACT(YEAR FROM palestra.data_hora_inicio) = EXTRACT(YEAR FROM current_date) THEN 'Ativo'
--         ELSE 'Inativo'
--     END AS status_evento
-- FROM palestrante
-- LEFT JOIN palestra_palestrante ON palestra_palestrante.palestrante_id = palestrante.id
-- LEFT JOIN palestra ON palestra_palestrante.palestra_id = palestra.id
-- LEFT JOIN evento ON evento.id = palestra.evento_id
-- GROUP BY palestrante.id, palestra.data_hora_inicio ORDER BY palestrante.id ASC;

-- -- 76–80. ORDER BY, LIMIT, OFFSET
-- -- 76) Mostre os 10 eventos com maior duração.
-- SELECT evento.nome, (data_fim - data_inicio) as duracao_dias
-- FROM evento ORDER BY duracao_dias DESC LIMIT 10;
 
-- -- 77) Mostre os 5 participantes que mais se inscreveram em eventos.
-- SELECT participante.nome, count(inscricao.evento_id) qnt_inscricao FROM participante
-- LEFT JOIN inscricao ON participante.id = inscricao.participante_id
-- GROUP BY participante.id ORDER BY qnt_inscricao DESC LIMIT 5;

-- -- 78) Liste as 3 palestras mais recentes.
-- SELECT palestra.titulo, palestra.data_hora_inicio FROM palestra
-- ORDER BY palestra.data_hora_inicio DESC LIMIT 3;

-- -- 79) Liste os 10 eventos mais antigos já realizados.
-- SELECT evento.id, evento.nome, evento.data_inicio, evento.data_fim FROM evento
-- ORDER BY evento.data_inicio, evento.data_fim ASC LIMIT 10;

-- -- 80) Liste os 20 primeiros participantes em ordem alfabética.
-- SELECT participante.nome FROM participante
-- ORDER BY participante.nome ASC LIMIT 20;

-- -- 81–85. JOINs e Filtros Extras
-- -- 81) Liste todos os eventos e, ao lado, o número total de palestrantes que participaram das palestras daquele evento.
-- SELECT evento.nome, count(palestra_palestrante.palestrante_id) AS qnt_palestrante FROM evento
-- LEFT JOIN palestra ON palestra.evento_id = evento.id
-- LEFT JOIN palestra_palestrante ON palestra.id = palestra_palestrante.palestra_id
-- GROUP BY evento.id ORDER BY evento.nome ASC;

-- -- 82) Liste todos os participantes e, ao lado, a quantidade de eventos realizados em 2025 em que estão inscritos.
-- SELECT participante.nome, count(inscricao.evento_id) FROM participante
-- LEFT JOIN inscricao ON inscricao.participante_id = participante.id
-- LEFT JOIN evento ON inscricao.evento_id = evento.id
-- GROUP BY participante.id, evento.data_inicio, evento.data_fim
-- HAVING (EXTRACT(YEAR FROM evento.data_inicio) = 2025 AND EXTRACT(YEAR FROM evento.data_fim) = 2025);

-- -- 83) Liste todos os eventos e mostre apenas os que têm palestras contendo a palavra “Machine”.
-- SELECT evento.nome, palestra.titulo FROM evento
-- LEFT JOIN palestra ON palestra.evento_id = evento.id
-- WHERE palestra.titulo LIKE '%Machine%';

-- SELECT evento.nome FROM evento
-- LEFT JOIN palestra ON palestra.evento_id = evento.id
-- WHERE palestra.titulo LIKE '%Machine%';

-- -- 84) Liste os palestrantes que estão associados a palestras em mais de uma cidade diferente.
-- SELECT palestrante.nome, count(evento.local) FROM palestrante
-- LEFT JOIN palestra_palestrante ON palestrante.id = palestra_palestrante.palestrante_id
-- LEFT JOIN palestra ON palestra_palestrante.palestra_id = palestra.id
-- LEFT JOIN evento ON palestra.evento_id = evento.id
-- GROUP BY palestrante.nome, evento.local HAVING count(evento.local) > 1;

-- -- 85) Liste todos os participantes e os eventos em que estão inscritos, mesmo que algum evento não tenha palestras associadas (LEFT JOIN).
-- SELECT participante.nome, evento.nome, count(palestra_palestrante.palestra_id) FROM participante
-- LEFT JOIN inscricao ON participante.id = inscricao.participante_id
-- LEFT JOIN evento ON inscricao.evento_id = evento.id
-- LEFT JOIN palestra ON palestra.evento_id = palestra.id
-- LEFT JOIN palestra_palestrante ON palestra.id = palestra_palestrante.palestra_id
-- GROUP BY participante.nome, evento.nome;

-- SELECT participante.nome, evento.nome, count(palestra.titulo) FROM participante
-- LEFT JOIN inscricao ON inscricao.participante_id = participante.id
-- LEFT JOIN evento ON evento.id = inscricao.evento_id
-- LEFT JOIN palestra ON palestra.evento_id = evento.id
-- GROUP BY participante.nome, evento.nome;

-- -- 86–90. IS NULL, NOT IN, EXISTS
-- -- 86) Liste os eventos sem palestras (usando IS NULL).
-- SELECT evento.nome, palestra.titulo FROM evento
-- LEFT JOIN palestra ON evento.id = palestra.evento_id
-- WHERE palestra.titulo IS NULL;

-- SELECT evento.nome FROM evento
-- LEFT JOIN palestra ON evento.id = palestra.evento_id
-- WHERE palestra.titulo IS NULL;

-- -- 87) Liste os participantes que não aparecem em nenhuma inscrição (usando NOT IN).
-- SELECT participante.nome FROM participante
-- WHERE participante.id NOT IN (
--     SELECT inscricao.participante_id FROM inscricao
-- );

-- -- 88) Liste os palestrantes que não têm nenhuma palestra associada (usando NOT IN).
-- SELECT palestrante.nome FROM palestrante
-- WHERE palestrante.id NOT IN (
--     SELECT palestra_palestrante.palestrante_id FROM palestra_palestrante
-- );

-- -- 89) Liste os eventos que possuem palestras associadas (usando EXISTS).
-- SELECT evento.id, evento.nome FROM evento
-- WHERE EXISTS (
--     SELECT * FROM palestra WHERE palestra.evento_id = evento.id
-- );

-- -- 90) Liste os participantes que estão inscritos apenas em eventos que possuem palestras.

--     -- mostra participantes e o evento que tão escritos
--     SELECT participante.nome, evento.nome FROM participante
--     INNER JOIN inscricao ON participante.id = inscricao.participante_id
--     LEFT JOIN evento ON evento.id = inscricao.evento_id;

--     -- mostra eventos que tem palestra
--     SELECT evento.nome FROM evento
--     INNER JOIN palestra ON evento.id = palestra.evento_id
--     GROUP BY evento.id;

--     SELECT participante.nome, evento.nome FROM participante
--     INNER JOIN inscricao ON participante.id = inscricao.participante_id
--     LEFT JOIN evento ON evento.id = inscricao.evento_id
--     WHERE evento.id IN (
--         SELECT evento.id FROM evento
--         INNER JOIN palestra ON evento.id = palestra.evento_id
--         GROUP BY evento.id
--     );

-- -- 91–95. Agregações Extras
-- -- 91) Mostre a quantidade de eventos realizados por cidade.
-- SELECT evento.local, count(evento.id) AS qnt_evento FROM evento
-- GROUP BY evento.local ORDER BY evento.local ASC;

-- -- 92) Mostre a quantidade de palestras realizadas em cada ano.
-- SELECT palestra.titulo, EXTRACT(YEAR FROM palestra.data_hora_inicio) FROM palestra;

-- SELECT EXTRACT(YEAR FROM palestra.data_hora_inicio), count(palestra.titulo) FROM palestra
-- GROUP BY EXTRACT(YEAR FROM palestra.data_hora_inicio);

-- -- 93) Mostre o palestrante que mais ministrou palestras.
-- SELECT palestrante.nome, count(palestra_palestrante.palestra_id) FROM palestrante
-- LEFT JOIN palestra_palestrante ON palestrante.id = palestra_palestrante.palestra_id
-- GROUP BY palestrante.nome ORDER BY count(palestra_palestrante.palestra_id) DESC;

-- -- 94) Mostre o evento com maior número de participantes distintos.
-- SELECT DISTINCT evento.nome, count(inscricao.participante_id) FROM evento
-- LEFT JOIN inscricao ON inscricao.evento_id = evento.id
-- GROUP BY evento.id ORDER BY count(inscricao.participante_id) DESC;

-- -- 95) Mostre a média de palestras por evento em cada cidade.

--     -- quantidade de palestras por cidade:
--     SELECT evento.id, evento.local, count(palestra.id) AS quantidade_palestra FROM evento
--     LEFT JOIN palestra ON palestra.evento_id = evento.id
--     GROUP BY evento.id, evento.local;

--     SELECT sub.local, AVG(quantidade_palestra) AS media_palestra_evento FROM (
--         SELECT evento.id, evento.local, count(palestra.id) AS quantidade_palestra FROM evento
--         LEFT JOIN palestra ON palestra.evento_id = evento.id
--         GROUP BY evento.id, evento.local
--     ) sub GROUP BY sub.local;


-- -- 96–100. Diversos
-- -- 96) Liste os 5 eventos com maior número de palestrantes.
-- SELECT evento.nome, count(inscricao.participante_id) qnt FROM evento
-- LEFT JOIN inscricao ON evento.id = inscricao.evento_id
-- GROUP BY evento.id ORDER BY qnt DESC LIMIT 5;

-- -- 97) Liste os 5 participantes que mais frequentaram eventos em 2024.
-- SELECT participante.nome, EXTRACT(YEAR FROM inscricao.data_hora), count(inscricao.evento_id) as qnt_eventos
-- FROM participante
-- LEFT JOIN inscricao ON participante.id = inscricao.participante_id
-- GROUP BY participante.id, EXTRACT(YEAR FROM inscricao.data_hora) 
-- HAVING EXTRACT(YEAR FROM inscricao.data_hora) = 2025
-- ORDER BY count(inscricao.evento_id) DESC LIMIT 5;

-- -- 98) Liste todas as palestras e mostre também o evento a que pertencem, ordenadas por nome de evento.
-- SELECT evento.nome, palestra.titulo FROM palestra
-- LEFT JOIN evento ON palestra.evento_id = evento.id
-- ORDER BY evento.nome ASC;

-- -- 99) Liste todos os participantes que nunca participaram de eventos em São Paulo.
-- SELECT participante.nome, evento.local FROM participante
-- LEFT JOIN inscricao ON inscricao.participante_id = participante.id
-- LEFT JOIN evento ON inscricao.evento_id = evento.id
-- WHERE evento.local != 'São Paulo' OR evento.local IS NULL;

-- -- 100) Liste os palestrantes que só ministraram palestras em eventos encerrados.

--     -- ID do evento que já encerrou
--     SELECT evento.id FROM evento
--     WHERE evento.data_fim < current_date;

-- SELECT evento.nome, palestra.titulo, palestrante.nome FROM palestrante
-- LEFT JOIN palestra_palestrante ON palestra_palestrante.palestrante_id = palestrante.id
-- LEFT JOIN palestra ON palestra_palestrante.palestra_id = palestra.id
-- LEFT JOIN evento ON palestra.evento_id = evento.id
-- WHERE evento.data_fim < current_date AND palestra.data_hora_inicio < current_timestamp;