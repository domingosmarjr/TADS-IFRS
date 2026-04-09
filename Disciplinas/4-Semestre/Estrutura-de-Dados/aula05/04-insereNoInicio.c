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

// ================================================
// INSERE NO INÍCIO
// > 2 possibilidades:
//      - lista vazia
//      - lista com elementos
void insereNoInicio (LDE *ls, Tarefa *tf) {
    // Define que o anterior é NULL (set de lista)
    tf->anterior = NULL;

    // Se a lista está vazia:
    if (ls->primeiro == NULL) {
        // próximo do item é o seu fim
        tf->proximo = NULL;
        // último da lista é o prório item
        ls->ultimo = tf;
    } 
    // Se a lista tem itens
    else {
        // O próximo do item é o primeiro da lista (linka para frente)
        tf->proximo = ls->primeiro;
        // O anterior do primeiro item da lista, é o item (linka de trás)
        ls->primeiro->anterior = tf;
    }

    // Define o primeiro da lista o item
    ls->primeiro = tf;
    // Incrementa numLista
    ls->num++;
}

int main(){

}