#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct aluno {
    char nome[20];
    int idade;
    int matricula;
    struct aluno *proximo;
} Aluno;

typedef struct lse {
    Aluno *primeiro;    //início da lista (ponteiro *primeiro)
    int n;              //quantidade de elementos
} LSE;

// INSERE NO INÍCIO
void insereInicio (LSE *lista, Aluno *novo) {
    if (lista->primeiro == NULL) { //CASO DE LISTA VAZIA (PRÓXIMO = NULL)
        novo->proximo = NULL;
    } else { //CASO TENHA ELEMENTOS NA LISTA
        novo->proximo = lista->primeiro; //O PRÓXIMO DO ALUNO É O ANTIGO PRIMEIRO DA LISTA
    }
    lista->primeiro = novo; //PRIMEIRO DA LISTA É NOVO INSERIDO NO INÍCIO DA LISTA
    lista->n++; //INCREMENTA QUANTIDADE DE ITENS DA LISTA
}

void insereInicioReduzida (LSE *lista, Aluno *novo) {
    novo->proximo = lista->primeiro; //próximo do inserido é o primeiro antigo da lista
    lista->primeiro = novo;          //primeiro da lista é novo inserido na lista
    lista->n++;                      //aumenta quantidade de itens da lista
}

void mostraAluno(Aluno aluno) {
    printf("\n Dados do Aluno:");
    printf("\n\tNome: %s", aluno.nome);
    printf("\n\tIdade: %d", aluno.idade);
    printf("\n\tMatricula: %d\n", aluno.matricula);
}

void mostraLista(LSE lista) {
    printf("LISTA SIMPLESMENTE ENCADEADA:\n");
    Aluno *aux;
    aux = lista.primeiro;
    for (int i = 0; i < lista.n ; i++) {
        mostraAluno(*aux);
        aux = aux->proximo;
    }
    printf("FIM DA LISTA!\n");
}

int main () {
    // MÉTODO PARA INSERIR NO INÍCIO DA LISTA
    // > CASO 1 = LISTA VAZIA:
    //      Ponteiro (*primeiro == NULL)    ||      n (n==0)
    // 
    // > CASO 2 = LISTA COM ELEMENTOS:
    //      Ponteiro (*primeiro != NULL)    ||      n (n>0)

    // INSERIR EM LISTA VAZIA:
    //      *primeiro = null        n = 0;
    // ação de inserir
    //      *primeiro = E0          n = 1;

    // INSERIR EM LISTA COM ELEMENTOS
    //      *primeiro -> se desloca para o novo elemento E0


    LSE matematica;
    matematica.primeiro = NULL;
    matematica.n = 0;

    Aluno joao = {"Joao Pedro", 23, 12345};
    joao.proximo = NULL;
    Aluno paulo = {"Paulo Roberto", 34, 12346};
    paulo.proximo = NULL;

    mostraLista(matematica);
    insereInicio(&matematica, &joao);
    mostraLista(matematica);
    insereInicio(&matematica, &paulo);
    mostraLista(matematica);

}