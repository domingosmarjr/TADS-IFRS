FUNÇÃO:
> Função que é usada por muitas aplicações, ela pode ser incorporada pelo SGBD
> Aplicativos não precisarão de mais de uma cópia da função.
> Ex de função pré-instalada no SGBD: upper()

Criando funções:
- CREATE FUNCTION 
- DROP FUNCTION

> Create:
    - Nome da função
    - Número de argumentos da função
    - Tipo de dado de cada argumento
    - Tipo de retorno da função
    - Ação da função
    - Linguagem usada pela função

    Ex: converter fahrenheit para celsius
    - nome: ftoc
    - argumento: float
    - retorno: float
    - ação: SELECT ($1 - 32.0) * 5.0 / 9.0;
    - linguagem: SQL

> EXEMPLO FUNÇÃO
CREATE FUNCTION ftoc (float) RETURNS float AS 
$$
    SELECT (($1 - 32.0) * 5.0 / 9.0);
$$ LANGUAGE sql;

- Após o AS vem o corpo da função
- $1 representa o primeiro parâmetro, $2 representa o segundo...
- PGSQL se usa $$ <método> $$

> EXEMPLO CRU
CREATE FUNCTION nome (parâmetros)
RETURNS tipo
AS $$
    código SQL
$$
LANGUAGE linguagem;

SELECT ftoc(68);  -> saída: ftoc = 20;


> AÇÕES DE FUNÇÕES
- Funções também podem conter instruções como insert, update e delete

====================================================
PL/PGSQL = Linguagem destinada para funções/procedimentos do SGBD
- linguagem de programação com variáveis, condicionais e looping
- precisa instalar ela em cad abanco de dados
- comando para instalar no banco:
    createlang plpgsql -U nomeuser nomebanco


Exemplo de função do PLPGSQL:

    CREATE FUNCTION soma (text, text) RETURN char AS
    $$
        DECLARE                      // estabelece variáveis abaixo
        resultado text;              // exemplo de variável
        BEGIN                        // início da função em si
        resultado := $1 || $2;       // guarda a concatenação do t1 e t2 em resultado
        return resultado;            // retorna o resultado
        END;                         // estabelece o fim da função
    $$ LANGUAGE 'plpgsql';

    > Chamando:
    SELECT soma('Sidney', 'Silva'); -> 'Sidney Silva'

    > DECLARE = define as variáveis usadas na função
        variable := expression;

        Exemplo:
            user_id integer;
            quantidade numeric(8,2);
            url character varying(60);      
            minhalinha tablename%ROWTYPE;       //tipo de dado da linha
            meucampo tablename.columname%TYPE;  //tipo de dado da coluna
            arow RECORD;                        //registro específico da linha

    > RETURN = sai e retorna um valor da função
    > BEGIN END = corpo principal da PL/PGSQL Function


VETORES:

    CREATE OR REPLACE FUNCTION test(in_array TEXT[]) RETURNS void AS
    $$
        DECLARE
            t TEXT;
        BEGIN
            FOREACH t IN ARRAY in_array LOOP
                raise notice 't: %', t;
            END loop;
        END;
    $$ language plpgsql;

    SELECT test(array['a','b','c']);

    RESULTADO:
        NOTICE: t:  a
        NOTICE: t:  b
        NOTICE: t:  c



CONDICIONAIS:
    IF numero = 0 THEN
        resultado := 'zero';
    ELSIF numero > 0 THEN
        resultado := 'positivo';
    ELSIF numero < 0 THEN
        resultado := 'negativo';
    ELSE
        resultado := 'NULL';
    END IF;

FOR:
    FOR i IN 1..(quantidade*2) LOOP
        RAISE NOTICE 'i é %', i;
    END LOOP;

    FOR i IN REVERSE 10..1 LOOP
        RAISE NOTICE 'i é %', i;
    END LOOP;

    Exemplo:

    CREATE FUNCTION calculaMaiorSalario(codigo INTEGER) RETURNS REAL AS
    $$
        DECLARE
            r_funcionario RECORD;
            salario REAL
        BEGIN
            salario = 0;
            FOR r_funcionario IN SELECT * FROM funcionario LOOP
                RAISE NOTICE 'Funcionario: %, %, %, %', r_funcionario.codigo, r_funcionario.nome, r_funcionario.departamento, r_funcionario.salario
                
                IF (r_funcionario.departamento = codigo) AND (r_funcionario.salario > salario) THEN
                    salario := r_funcionario.salario;
                END IF;
            END LOOP;
            RETURN salario;
        END;
    $$ LANGUAGE 'plpgsql';


WHILE:
    WHILE quantidade > 0 AND quantidade < 1000 LOOP
        --eventos
    END LOOP;

    WHILE NOT (quantidade <= 0) LOOP
        --eventos
    END LOOP;

LOOP:
    LOOP
        --eventos
        IF contador > 0 THEN
            EXIT; //break
        END IF;
    END LOOP;

    LOOP
        --eventos
        EXIT WHEN contador > 0;
    END LOOP;


EXEMPLO PRÁTICO DE LOOP E FOR
    CREATE OR REPLACE FUNCTION lacos (tipo_laco int) RETURNS VOID AS
    $$
        DECLARE
            contador int NOT NULL DEFAULT 0;
        BEGIN
            IF tipo_laco = 1 THEN
                WHILE contador < 10 LOOP
                    contador := contador + 1;
                    RAISE NOTICE 'Contador: %', contador;
                END LOOP;
            ELSIF tipo_laco = 2 THEN
                LOOP
                    contador := contador + 1;
                    RAISE NOTICE 'Contado: %', contador;
                    EXIT WHEN contador > 9;
                END LOOP
            ELSE
                FOR contador IN 1..10 LOOP
                    RAISE NOTICE 'Contador: %', contador;
                END LOOP;
            END IF;
            RETURN;
        END;
    $$ LANGUAGE 'plpgsql';

<!-- Método de Incremento -->
CREATE OR REPLACE FUNCTION incremento (i INTEGER) RETURNS INTEGER AS
$$
    BEGIN
        RETURNS i + 1;
    END;
$$ LANGUAGE plpgsql;


<!-- RETORNANDO MAIS DE UM PARÂMETRO -->
CREATE FUNCTION somaNProdutos (x int, y int, OUT sum int, OUT prod int) AS
$$
    BEGIN
        sum := x + y;
        prod := x * y;
    END;
$$ LANGUAGE plppgsql;

- Nos parâmetros da função, já se declara sua saída [sum e prod]
- Resultado:
    somaNProdutos
    -------------
    (3,2)









<!-- CAPUTRANDO ERROS NA FUNÇÃO -->
CREATE FUNCTION altera_salario() RETURNS integer AS
$$
    BEGIN
        UPDATE funcionario SET salario = salario * 1.1;

        DECLARE
            x integer;
        BEGIN
            UPDATE funcionario SET salario = 5000;
            --gerar erro proposital
            x := 1/0;
            RETURN 1;
        
        EXCEPTION
            WHEN division_by_zero THEN RAISE NOTICE 'Divisão por zero';
            RETURN 0;
            //Isso desfaz a alteração do bloco anterior! Mantem apenas o salario = 5000
        END;
    END;
$$ LANGUAGE plpgsql;


<!-- SELECT INTO = GUARDAR RESULTADO DENTRO DE UMA VARIÁVEL-->
CREATE FUNCTION numero_de_empregados (depto departamento.nomedepto%TYPE) RETURNS integer AS $$
    DECLARE
        reg RECORD;
        contador integer := 0;
    BEGIN
        SELECT INTO reg * FROM departamento WHERE nomedepto = depto;
        IF NOT FOUND THEN
            RAISE EXCEPTION 'Departamento % não encontrado', depto;
        ELSE
            FOR reg IN SELECT * FROM funcionario WHERE id_depto = reg.id_depto ORDER BY nomefunc LOOP
                contador := contador + 1;
                RAISE NOTICE '%) % : %', contador, reg.nomefunc, reg.endereco;
                PERFORM insere_log_2('leitura do funcionario' || reg.id_func);
            END LOOP;
            RETURN contador;
        END IF;
    END;
$$ LANGUAGE plpgsql;

> SELECT INTO = executa o select e guarda o resultado dentro de uma variável!
> PERFORM = função que não espera retorno (LOGS, auditoria)






<!-- ROWTYPE = TIPO DA LINHA-->
CREATE FUNCTION encontrar_gerentes (tuplad departamento) RETURNS text AS
$$
    DECLARE
        tuplaf funcionario%ROWTYPE;
    BEGIN
        //Guarda na variável tuplaf os funcionários que também são gerentes do dpt
        SELECT * INTO tuplaf FROM funcionario WHERE tuplad.id_gerente = id.func;

        RETURN tuplad.nomedepto || '---' || tuplaf.nomefunc;
    END;
$$ LANGUAGE plpgsql;

SELECT encontrar_gerentes(t.*) FROM departamento t;






<!-- RECORD = igual ROWTYPE mas sem estrutura definida-->
- variável RECORD assume a estrutura de linha
- atribuição nova de RECORD assuma nova linha atribuída
- precisa atribuir para operar

CREATE FUNCTION encontrar_departamento (func funcionario.id_func%TYPE) RETURNS text AS
$$
    DECLARE
        reg RECORD;
        depto funcionario.id_depto%TYPE;
    BEGIN
        //Armazena em reg o registro do funcionario de código inserido
        SELECT INTO reg * FROM funcionario WHERE id_func = func;

        IF NOT FOUND THEN
            ---
            RAISE EXCEPTION 'Empregado % não encontrado', func;
        ELSE
            depto := reg.id_depto;
            //Armazena em reg o registro do departamento de código departamento
            SELECT INTRO reg * FROM departamento WHERE id_depto = depto;
            RETURN reg.id_depto || '---' || reg.nomedepto;
        END IF;
    END;
$$ LANGUAGE plpgsql;

SELECT encontra_departamento(1);









=================================================
