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

// CRIA UMA TAREFA
Tarefa *criaTarefa(int id) {
    Tarefa *nova = (Tarefa*)malloc(sizeof(Tarefa));
    nova->id = id;
    fflush(stdin); //?
    printf("Prioridade:");
    scanf("%d", &nova->prioridade);
    printf("Descrição:");
    scanf("%s", nova->descricao);
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

// INSERE NO FIM
void insereNoFim (LDE *ls, Tarefa *tf) {
    tf->proximo = NULL;
    if (ls->primeiro == NULL) insereNoInicio(ls, tf);
    else {
        tf->anterior = ls->ultimo;
        ls->ultimo->proximo = tf;
        ls->ultimo = tf;
        ls->num++;
    }
}

void mostraTarefa(Tarefa tf) {
    // Mostra dados de uma tarefa recebida por valor
    printf("\n Tafera N° %d", tf.id);
    printf("\n\t Descricao: %s", tf.descricao);
    printf("\n\t Prioridade: %d", tf.prioridade);
    if (tf.concluida)
        printf("\n\t Tarefa já concluída.");
    else
        printf("\n\t Tarefa Não concluída.");
}

// MOSTRAR LISTA (ESQUERDA - DIREITA)
void mostraListaED (LDE ls) {
    Tarefa *aux = ls.primeiro;
    if (aux == NULL) printf("\nLista vazia.\n");
    else {
        printf("\nInício da Lista.");
        while (aux != NULL) {
            mostraTarefa(*aux);
            aux = aux->proximo;
        }
        printf("\nFim da lista.");
    }
}

// MOSTRAR LISTA (DIREITA - ESQUERDA)
void mostraListaDE (LDE ls) {
    Tarefa *aux = ls.ultimo;
    if (aux == NULL) printf("\nLista vazia.\n");
    else {
        printf("\nFim da Lista.");
        while(aux != NULL) {
            mostraTarefa(*aux);
            aux = aux->anterior;
        }
        printf("\nFim da lista.");
    }
}

int main(){

    LDE lista1 = {NULL, NULL, "Tasks", 0};
    mostraListaED(lista1);

    Tarefa t1 = {"Acordar", 1, 10, 0, NULL, NULL};
    Tarefa t2 = {"Comer", 2, 8, 0, NULL, NULL};
    Tarefa t3 = {"Escovar", 2, 8, 0, NULL, NULL};
    Tarefa t4 = {"Trabalhar", 2, 8, 0, NULL, NULL};
    Tarefa t5 = {"Dormir", 2, 8, 0, NULL, NULL};

    insereNoInicio(&lista1, &t5);
    insereNoInicio(&lista1, &t4);
    insereNoInicio(&lista1, &t3);
    insereNoInicio(&lista1, &t2);

    insereNoFim(&lista1, &t1);

    mostraListaED(lista1);

}