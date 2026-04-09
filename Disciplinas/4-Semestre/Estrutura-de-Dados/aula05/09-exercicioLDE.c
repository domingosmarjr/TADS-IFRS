#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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

void menu (LDE *lista) {
    int op;
    int posicao;
    int id = 1;
    char ch;

    do  {
        // system("clear");
        printf("\nInforme uma Opção:");
        printf("\n1 - Inserir Tarefa no Início:");
        printf("\n2 - Inserir Tarefa no Fim:");
        printf("\n3 - Inserir Tarefa na Posição:");
        printf("\n4 - Remover Tarefa no Início:");
        printf("\n5 - Remover Tarefa no Fim:");
        printf("\n6 - Remover Tarefa na Posição:");
        printf("\n7 - Mostrar Tarefa na Posição:");
        printf("\n8 - Mostrar Lista E-D:");
        printf("\n9 - Mostrar Lista D-E:");
        printf("\n10 - Apagar Lista:");
        printf("\n0 - Sair do Programa:");
        printf("\nInforme sua opção:");
        scanf("%d", &op);
        fflush(stdin);
    } while(op != 0);
}


int main() {
    LDE *mLista = (LDE*)malloc(sizeof(LDE));
    mLista = criaListaLDE("MINHAS TAREFAS");
    menu(mLista);
}