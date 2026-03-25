===== AULA 04 =====

LISTAS - PILHAS - FILAS

Listas Lineares - Tipos:
- Simplesmente Encadeada (LSE)
- Duplamente Encadeada (LDE)

Lista Linear:
>Coleção de elementos (nós) do mesmo tipo.
>ex: lista telefone, carro, filmes
>Lista dinâmica = número infinito de elementos
>   [enquanto houver memória, posso ir alocando]
>fim da lista é marcado pela terminador ("null")
> 'n' é a quantidade de elementos da lista

SIMPLESMENTE ENCADEADA:
> Lógica próxima
> Guarda endereço do próximo

Dados|Próximo  --->   Dados|Próximo  --->  Dados|Próximo  --->  NULL

DUPLAMENTE ENCADEADA:
- Lógica anterior e próximo
- Guarda endereço do próximo e do anterior

NULL  <---    Dados|Próximo  --->   Dados|Próximo  --->  Dados|Próximo  --->  NULL
                             <---                  <---                 


LISTA CIRCULAR:
- Lista Duplamente Encadeada, onde o primeiro e o último tem conexão
 
 |---------------->
E0 -> E1 -> E2 -> E3
 <-----------------

ELEMENTO DA LISTA (NÓ DA LISTA)
- Estrutura capaz de armazenar todos os dados que eu quero, mais os ponteiros (próximo e anterior);
- Nó é uma struct

    Ex:
        - Aluno 
        > nome
        > idade
        > matrícula
        > Aluno *proximo
        > Aluno *anterior

        typedef struct Aluno {
            char nome[20];
            int idade;
            int matricula;
            struct Aluno *proximo;
        } Aluno;

=====================================================================================
LSE -> Gerenciamento de Lista
- Estrutura com ponteiros para início/fim

<!-- List Simple Encaded -->
typedef struct LSE{
    Aluno *primeiro;
    int n;
} LSE;
LSE matematica;
inicializaLista(&matematica, "Matematica");

- Declaração de um ponteiro para o início da lista;
Aluno *matematica = NULL;
> ponteiro que dita quem é o primeiro (ponteiro de ponteiro)

DECLARAR NOVA LISTA ESTÁTICA:
    LSE matematica;

DECLARAR NOVA LISTA COMO DINÂMICA:
    LSE *matematica = (LSE*)malloc(sizeof(LSE));


INICIALIZAÇÃO DOS PARÂMETROS DE UMA LISTA:
    void inicializaLista(LSE *lista) {
        lista->primeiro = NULL;
        lista->n_elementos = 0;
    }

    