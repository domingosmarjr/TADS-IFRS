#include <stdio.h>
#include <stdlib.h>

// EXEMPLO ELEMENTO DE LDE
typedef struct tarefa {
    char descricao[20];
    int id, prioridade, concluida;
    struct tarefa *proximo;
    struct tarefa *anterior;
} Tarefa;

int main() {

}