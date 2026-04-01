ESTRUTURAS DE DADOS DINÂMICAS
- Listas
- Pilhas
- Filas
================================================================
LISTAS LINEARES
> LSE = Simplesmente Encadeadas
> LDE = Duplamente Encadeadas

- Lista Linear é uma coleção de elementos (nós) do mesmo tipo, dispostos linearmente.
- Lista dinâmica é composta por um número infinito de elementos. (n é a quantidade de elementos.)
- Fim da lista é marcado pelo terminador NULL
- Ponteiro próximo e/ou anterior determinam o encadeamento dos elementos

>SIMPLESMENTE ENCADEADA

E0[DADOS]|próximo ---> E1[DADOS]|próximo ---> E2[DADOS]próximo ---> NULL


>DUPLAMENTE ENCADEADA
- Lógica anterior e próximo
- Guarda endereço do próximo e do anterior

NULL  <---    Dados|Próximo  --->   Dados|Próximo  --->  Dados|Próximo  --->  NULL
                             <---                  <---                 

>LISTA CIRCULAR:
- Lista Duplamente Encadeada, onde o primeiro e o último tem conexão
 
 |---------------->
E0 -> E1 -> E2 -> E3
 <-----------------


LISTAS DINÂMICAS
- Elementos de Estrutura
> Elementos da estrutura precisam ser pensados
> Ex: lista de chamada => elementos são alunos matriculados
- Interface da lista
> precisamos pensar na interface da lista
> Ex: lista de chamada => a estrutura é definida pelas marcações que definem o início e o fim da lista. assim como funções de inserção e remoção de elementos.


>NÓ DA LISTA
- Estrutura de dados que armazenará:
    >Dados (informações da lista)
    >Ponteiros (anterior, próximo)
        Ex: a lista de chamada armazena alunos
        Ex: lista de chamada de alunos
            Aluno  
                Nome
                Idade
                Matrícula

LSE -> Ponteiro Próximo
- Para apontar para o próximo, cria-se um apontador que armazenará a referência para o próximo elemento da lista LSE.
- *próximo Aluno

    typdef struct aluno {
        char nome[50];
        int idade;
        int matricula;
        struct Aluno *proximo;
    } Aluno;


>LSE - GERENCIAMENTO DA LISTA
- PODE SER UMA Estrutura com ponteiros para início e fim
    typedef struct LSE {
        Aluno *primeiro;
        int n;
    } LSE;

    LSE matetica;
    inicializa(&matematica, "Matematica");

- PODE SER apenas um ponteiro para o início da lista (lista sem cabeça)
    Aluno *matematica = NULL;

- TAMANHO DA LISTA (n de elementos)
    Lista pode ter de 0 até n elementos
    >Pontos de acesso e navegação
        - *primeiro => marca o início da lista (obrigatório)
        - *ultimo   => marca o fim da lista (facultativo)
