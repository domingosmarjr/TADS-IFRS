--Nome: Domingos Martins Jr.
--Matrícula: 2023017853

-- 26. Liste todos os eventos e, ao lado, mostre "Encerrado" se a data fim for menor que hoje, "Em andamento" se estiver entre início e fim, e "Futuro" se ainda não começou.
SELECT evento.nome, data_inicio as data_comeco, data_fim,
    WHEN data_inicio <= current_date AND data_fim >= current_date THEN 'Em andamento.'
CASE 
    WHEN data_fim < current_date THEN 'Encerrado'
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