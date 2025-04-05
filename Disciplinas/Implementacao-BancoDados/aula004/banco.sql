-- TYPE e WITH

-- Type = Tipo
-- Dá de criar tipos dentro do Postgres (tamanho [pequeno,grande,médio])

-- enum = enumeração de tipos
CREATE TYPE tamanho AS ENUM ('pequena','media','grande');

CREATE TABLE residenciais (
    tamanho tamanho NOT NULL;
);

-- With = Com
-- Às vezes eu preciso criar uma tabela auxiliar para fazer uma consulta mais sofisticada.
-- Parecido como View, mas ele não existe, diferente da View.
-- Dividir problema em partes!
-- Pode ter vários with

WITH tabela_aux AS (
    SELECT
        evento_id,
        evento.nome,
        count(*)*valor AS sub_total
        ...
) SELECT evento_id, nome, sum(sub_total) AS total FROM tabela_aux GROUP BY ...;
