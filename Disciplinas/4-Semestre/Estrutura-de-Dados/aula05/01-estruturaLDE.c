#include <stdio.h>
#include <stdlib.h>

// EXEMPLO ELEMENTO DE LDE
typedef struct tarefa {
    char descricao[20];
    int id, prioridade, concluida;
    struct tarefa *proximo;
    struct tarefa *anterior;
} Tarefa;

// EXEMPLO DE ESTRUTURA DE LDE
typedef struct lde {
    Tarefa *primeiro;
    Tarefa *ultimo;
    char nome[30];
    int num;
} LDE;

int main() {

}