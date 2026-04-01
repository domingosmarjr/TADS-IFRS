#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct aluno {
    char nome[20];
    int idade;
    int matricula;
    struct Aluno *proximo;  //aponta para o próximo aluno como um ponteiro
} Aluno;

void mostraAluno(Aluno aluno) {
    printf("\n Dados do Aluno:");
    printf("\n\tNome: %s", aluno.nome);
    printf("\n\tIdade: %d", aluno.idade);
    printf("\n\tMatricula: %d\n", aluno.matricula);
}

void cadastraAluno(Aluno *aluno, char nome[], int idade, int matricula) {
    strcpy(aluno->nome, nome);
    aluno->idade = idade;
    aluno->matricula = matricula;
    aluno->proximo = NULL;
}

// =======================================================================
// BASE DA LISTA
typedef struct lse {
    Aluno *primeiro;    //início da lista (ponteiro *primeiro)
    int n;              //quantidade de elementos
} LSE;

// INICIAR A LISTA
//  - recebe um ponteiro de lista
//  - inicializa o ponteiro *primeiro e quantidade de elementos da lista
void inicializaLista(LSE *lista) {
    lista->primeiro = NULL;
    lista->n = 0;
};

// CRIAR A LISTA
void criaLista(Aluno **lista, char nome[]) {
    //Aloca memória para uma lista (recebe ponteiro de ponteiro)
    *lista = (Aluno*)malloc(sizeof(Aluno));
    // Inicializa o próximo da lista 
    (*lista)->proximo = NULL;
};

// =======================================================================
int main() {

    // Alocação estática
    LSE matematica;

    // Alocação dinâmica
    LSE *portugues = (LSE*)malloc(sizeof(LSE));

    // Declara e inicializa um novo elemento de lista de alunos
    Aluno joao = {"Joao Pedro", 23, 12345};
    joao.proximo = NULL;

    // Cadastra novo aluno por meio de função
    Aluno paulo;
    cadastraAluno(&paulo, "Paulo Roberto", 34, 123321);


    /*          Aluno   *proximo
        xx01    João    Paula
        xx51    Maria   NULL
        xx81    Paula   Maria

        Joao -> Paula -> Maria -> NULL
    */


}