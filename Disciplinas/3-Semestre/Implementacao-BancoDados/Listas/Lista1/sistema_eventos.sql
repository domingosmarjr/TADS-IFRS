-- psql -h localhost -U postgres
-- sudo -u postgres psql

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

INSERT INTO evento (nome, data_fim, hora_fim, local) VALUES ('AULA DE IOBD', '2025-08-12', '22:20', 'IFRS');
INSERT INTO evento (nome, data_fim, hora_fim, local) VALUES ('AULA POO', '2025-09-01', '11:00', 'IFRS');
INSERT INTO evento (nome, data_fim, hora_fim, local) VALUES ('AULA APS', '2025-10-01', '12:00', 'IFRS');
INSERT INTO evento (nome, data_fim, hora_fim, local) VALUES ('AULA BANCO', '2025-12-01', '14:00', 'IFRS');

CREATE TABLE participante (
    id serial PRIMARY KEY,
    nome character varying(200) NOT NULL,
    data_nascimento date
);

INSERT INTO participante (nome, data_nascimento) VALUES ('JAAZIEL','2002-04-23'),('BETITO','1900-01-01');
INSERT INTO participante (nome, data_nascimento) VALUES ('PEDRO', '1990-03-03'), ('CLIETON','2000-10-10'), ('JACK','2003-02-02');
INSERT INTO participante (nome, data_nascimento) VALUES ('OLIVER', '2000-04-04');
INSERT INTO participante (nome, data_nascimento) VALUES ('BATUTA' , '2000-09-02');
INSERT INTO participante (nome, data_nascimento) VALUES ('NARUSCI', '1980-10-10');


CREATE TABLE inscricao (
    id serial PRIMARY KEY,
    evento_id integer REFERENCES evento(id),
    participante_id integer REFERENCES participante(id),
    data_hora timestamp DEFAULT current_timestamp,
    valor money DEFAULT 0,
    pago boolean DEFAULT false,
    CHECK(cast(valor as numeric(8,2)) >= 0), UNIQUE (evento_id, participante_id)
);

INSERT INTO inscricao (evento_id, participante_id) VALUES (1,1),(1,2);
INSERT INTO inscricao (evento_id, participante_id) VALUES (3,3), (3,4), (2,5), (2,1);
INSERT INTO inscricao (evento_id, participante_id) VALUES (2,6);

CREATE TABLE palestra (
    id serial PRIMARY KEY,
    titulo text NOT NULL,
    duracao integer CHECK (duracao > 0),
    data_hora_inicio timestamp DEFAULT current_timestamp,
    evento_id integer REFERENCES evento(id)
);

INSERT INTO palestra (titulo, duracao, evento_id) VALUES ('MODELAGEM RELACIONAL', 120, 1);
INSERT INTO palestra (titulo,duracao, evento_id) VALUES ('JAVA', 120, 1);

INSERT INTO palestra (titulo,duracao, evento_id) VALUES ('SQL', 120, 1);
INSERT INTO palestra (titulo,duracao, evento_id) VALUES ('Polimorfismo', 120, 2);
INSERT INTO palestra (titulo,duracao, evento_id) VALUES ('UML', 120, 3);
INSERT INTO palestra (titulo,duracao, evento_id) VALUES ('Herança', 120, 2);

CREATE TABLE palestrante (
    id serial PRIMARY KEY,
    nome character varying(200) NOT NULL,
    biografia text,
    cpf character(11) NOT NULL, UNIQUE (cpf)
);

INSERT INTO palestrante (nome, cpf) VALUES ('IGOR AVILA PEREIRA','17658586072');
INSERT INTO palestrante (nome, cpf) VALUES ('MARCIO', '12312312313');
INSERT INTO palestrante (nome, cpf) VALUES ('NARUSCI', '12312312312');


CREATE TABLE palestra_palestrante (
    palestra_id integer REFERENCES palestra(id),
    palestrante_id integer REFERENCES palestrante(id),
    PRIMARY KEY (palestra_id, palestrante_id)
);
INSERT INTO palestra_palestrante (palestra_id, palestrante_id) VALUES (1,1);

INSERT INTO palestra_palestrante (palestra_id, palestrante_id) VALUES (2,2);
INSERT INTO palestra_palestrante (palestra_id, palestrante_id) VALUES (3,2);
INSERT INTO palestra_palestrante (palestra_id, palestrante_id) VALUES (4,2);


---EXERCÍCIOS---

-- ### 🔹 1–5. JOINs Simples e Compostos
-- 1. Liste o nome dos participantes e os nomes dos eventos em que estão inscritos.
SELECT participante.nome, evento.nome AS evento FROM participante INNER JOIN inscricao ON participante.id = inscricao.participante_id INNER JOIN evento ON evento.id = inscricao.evento_id;

-- 2. Liste o nome dos eventos e os nomes das palestras associadas.
SELECT evento.nome, palestra.titulo FROM evento
LEFT JOIN palestra ON evento.id = palestra.evento_id
ORDER BY evento.nome;

-- 3. Liste os nomes dos palestrantes e as palestras que eles ministram.
SELECT palestrante.nome, palestra.titulo FROM palestrante
INNER JOIN palestra_palestrante ON palestrante.id = palestra_palestrante.palestrante_id
INNER JOIN palestra ON palestra_palestrante.palestra_id = palestra.id
ORDER BY palestrante.nome ASC; 

-- 4. Liste os eventos e a quantidade de palestras que cada um possui.
SELECT evento.nome, COUNT(palestra.id) FROM evento
INNER JOIN palestra ON evento.id = palestra.evento_id
GROUP BY evento.id, evento.nome;

-- 5. Liste os eventos e o número total de participantes inscritos.
SELECT evento.nome, COUNT(participante.id) FROM evento
INNER JOIN inscricao ON evento.id = inscricao.evento_id
INNER JOIN participante ON participante.id = inscricao.participante_id
GROUP BY evento.id, evento.nome;

-- ### 🔹 6–10. WHERE, IN, BETWEEN
-- 6. Liste os eventos que ocorrem entre duas datas específicas (ex: entre '2025-01-01' e '2025-12-31').
SELECT evento.nome, evento.data_inicio, evento.data_fim FROM evento
WHERE data_inicio BETWEEN '2025-01-01' AND '2025-12-31';

-- 7. Liste os participantes que estão inscritos em mais de um evento.
SELECT participante.nome, COUNT(evento.nome) FROM participante
INNER JOIN inscricao ON participante.id = inscricao.participante_id
INNER JOIN evento ON inscricao.evento_id = evento.id
GROUP BY participante.nome
HAVING count(evento.nome) > 1;

-- 8. Liste os palestrantes que participaram de uma palestra com o nome contendo a palavra “tecnologia”.
INSERT INTO palestrante (nome, cpf) VALUES ('PEDRO', '12312312314');
INSERT INTO palestra (titulo,duracao, evento_id) VALUES ('NOVAS Tecnologias', 120, 2);
INSERT INTO palestra_palestrante (palestra_id, palestrante_id) VALUES (8,4);

SELECT palestrante.nome, palestra.titulo FROM palestrante
INNER JOIN palestra_palestrante ON palestrante.id = palestra_palestrante.palestrante_id
INNER JOIN palestra ON palestra_palestrante.palestra_id = palestra.id
WHERE palestra.titulo LIKE '%ecnologia%';

-- 9. Liste as inscrições feitas em uma data específica.
SELECT inscricao.id, participante.nome FROM inscricao
INNER JOIN participante ON inscricao.participante_id = participante.id
WHERE CAST (data_hora AS date) = CURRENT_DATE;

-- 10. Liste os eventos que ocorreram em um local específico (ex: 'São Paulo').
SELECT evento.id, evento.nome, evento.local FROM evento WHERE local = 'IFRS';

-- ### 🔹 11–15. Subselects
11. Liste os participantes que estão inscritos no evento com o maior número de palestras.

    -- pega o id do evento que mais tem palestras
    SELECT evento.id FROM evento
    JOIN palestra ON evento.id = palestra.evento_id
    GROUP BY evento.id
    ORDER BY COUNT(palestra.id) DESC
    LIMIT 1;

SELECT participante.nome, evento.nome as nome_evento FROM participante
LEFT JOIN inscricao ON participante.id = inscricao.participante_id
LEFT JOIN evento ON inscricao.evento_id = evento.id 
WHERE evento.id = (
    SELECT evento.id FROM evento
    JOIN palestra ON evento.id = palestra.evento_id
    GROUP BY evento.id
    ORDER BY COUNT(palestra.id) DESC
    LIMIT 1
);

12. Liste as palestras com mais de um palestrante.
13. Liste os eventos que têm mais participantes do que a média de participantes por evento.
14. Liste os nomes dos palestrantes que não estão vinculados a nenhuma palestra.
15. Liste os participantes que não se inscreveram em nenhum evento.



























--O SQL ele pega o último inserido, mesmo em situação de empate entre os dois.

-- 26. Liste todos os eventos e, ao lado, mostre "Encerrado" se a data fim for menor que hoje, "Em andamento" se estiver entre início e fim, e "Futuro" se ainda não começou.
SELECT evento.nome, data_inicio as data_comeco, data_fim,
CASE 
    WHEN data_fim < current_date THEN 'Encerrado'
    WHEN data_inicio <= current_date AND data_fim >= current_date THEN 'Em andamento.'
    ELSE 'Futuro'
END AS status
FROM evento;

-- 27. Liste os nomes das palestras e, ao lado, mostre “Sem palestrante” caso nenhuma pessoa esteja associada a ela.
SELECT palestra.titulo AS nome_palestra,
    CASE
        WHEN count(palestra_palestrante.palestrante_id) = 0 THEN 'Sem palestrante'
        ELSE 'Com palestrante'
    END as status_palestrante
FROM palestra
LEFT JOIN palestra_palestrante ON palestra.id = palestra_palestrante.palestra_id
GROUP BY palestra.id, palestra.titulo;

-- 28. Liste os participantes e mostre “Inscrito” se estiverem inscritos em algum evento e “Não inscrito” caso contrário.
SELECT participante.nome,
    CASE
        WHEN count(evento.id) != 0 THEN 'Inscrito'
        ELSE 'Não inscrito'
    END AS status
FROM participante 
LEFT JOIN inscricao ON participante.id = inscricao.participante_id
LEFT JOIN evento ON evento.id = inscricao.evento_id
GROUP BY participante.nome
ORDER BY participante.nome ASC;

-- 29. Liste os nomes das pessoas que são **tanto palestrantes quanto participantes**.
SELECT participante.nome FROM participante
INTERSECT
SELECT palestrante.nome FROM palestrante;

-- 30. Liste todos os nomes de pessoas envolvidas no sistema, sejam palestrantes ou participantes (sem duplicar nomes). (palestrantes, ) [Narusci (final02) - não repete]
ALTER TABLE participante ADD COLUMN cpf character(11) UNIQUE;
UPDATE participante SET cpf = '12312312311' WHERE id = 1;
UPDATE participante SET cpf = '12312312310' WHERE id = 2;
UPDATE participante SET cpf = '12312312313' WHERE id = 3;
UPDATE participante SET cpf = '12312312314' WHERE id = 4;
UPDATE participante SET cpf = '12312312315' WHERE id = 5;
UPDATE participante SET cpf = '12312312316' WHERE id = 6;
UPDATE participante SET cpf = '12312312317' WHERE id = 7;
UPDATE participante SET cpf = '12312312312' WHERE id = 8;
UPDATE palestrante SET cpf = '12312312312' WHERE id = 3;

SELECT palestrante.nome, palestrante.cpf FROM palestrante
UNION
SELECT participante.nome, participante.cpf FROM participante;

