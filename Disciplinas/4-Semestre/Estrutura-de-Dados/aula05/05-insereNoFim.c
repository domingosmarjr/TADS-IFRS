#include <stdio.h>
#include <stdlib.h>

typedef struct tarefa {
    char descricao[20];
    int id, prioridade, concluida;
    struct tarefa *proximo;
    struct tarefa *anterior;
} Tarefa;

typedef struct lde {
    Tarefa *primeiro;
    Tarefa *ultimo;
    char nome[30];
    int num;
} LDE;

// CRIA UMA LISTA VAZIA E SETA
LDE *criaListaLDE (char nome[]) {
    // Aloca memória e inicializa nova lista LDE.
    LDE *nova = (LDE*)malloc(sizeof(LDE));
    strcpy(nova->nome, nome);
    nova->primeiro = NULL;
    nova->ultimo = NULL;
    nova->num = 0;
    return nova;
}

// CRIA UMA TAREFA
Tarefa *criaTarefa(int id) {
    Tarefa *nova = (Tarefa*)malloc(sizeof(Tarefa));
    nova->id = id;
    fflush(stdin); //?
    printf("Prioridade:");
    scanf("&d", nova->prioridade);
    printf("Descrição:");
    scanf("&s", nova->descricao);
    printf("Tarefa Criada com Sucesso!\n");
    return nova;
}

// INSERE NO INÍCIO
void insereNoInicio (LDE *ls, Tarefa *tf) {
    tf->anterior = NULL;
    if (ls->primeiro == NULL) {
        tf->proximo = NULL;
        ls->ultimo = tf;
    } 
    else {
        tf->proximo = ls->primeiro;
        ls->primeiro->anterior = tf;
    }
    ls->primeiro = tf;
    ls->num++;
}

// ================================================
// INSERE NO FIM
// > 2 possibilidades:
//      - lista vazia
//      - lista com elementos

// Complexidade de 0(1) [mais otimizado computacionalmente]

void insereNoFim (LDE *ls, Tarefa *tf) {
    // Seta o item para setup de fim de lista
    tf->proximo = NULL;
    // Se lista vazia = insereNoInicio
    if (ls->primeiro == NULL) {
        insereNoInicio(ls, tf);
    } else {
        // Anterior do item, recebe o último da lista
        tf->anterior = ls->ultimo;
        // Próximo do último da lista, recebe o item
        ls->ultimo->proximo = tf;
        // Último da lista, recebe item
        ls->ultimo = tf;
        // Incremente numLista
        ls->num++;
    }
}

void insereNoFim2 (LDE *ls, Tarefa *tf) {
    Tarefa *aux = ls->primeiro;
    if (ls->primeiro == NULL) insereNoInicio(ls, tf);
    else {
        while (aux->proximo != NULL) aux = aux->proximo;

        aux->proximo = tf;
        tf->anterior = aux;
        tf->proximo = NULL;
        ls->num++;

        // Nessa função a complexidade computacional é 0(n)
        // Ou seja, a execução depende do n elementos da lista.
        // Se eu tivesse um ls->ultimo, eu iria direto para ele, independente do n.
    }
}



int main(){

}