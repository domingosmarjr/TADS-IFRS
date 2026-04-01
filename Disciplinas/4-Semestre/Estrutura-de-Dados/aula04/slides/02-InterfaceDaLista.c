#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct aluno {
    char nome[20];
    int idade;
    int matricula;
    struct Aluno *proximo;
} Aluno;

typedef struct lse {
    Aluno *primeiro;    //início da lista (ponteiro *primeiro)
    int n;              //quantidade de elementos
} LSE;




int main() {

    // LISTA LSE NÃO POSSUI RESTRIÇÃO PARA QUANTIDADE DE INSERÇÃO E REMOÇÃO
    // INTERFACE DE LISTA
    //  - implementa as funções de lista
    //      - insereNoInicio()      = insere um novo elemento em E0
    //      - insereNoFim()         = insere um novo elemento em En
    //      - insereNaPosicao()     = insere um novo elemento em Ei
    // 
    //      - removeNoInicio()
    //      - removeNoFim()
    //      - removeNaPosicao()
    // 
    //      - mostraElemento()
    //      - mostraLista()
    // 
    //      - apagaElemento()
    //      - apagaLista()

}