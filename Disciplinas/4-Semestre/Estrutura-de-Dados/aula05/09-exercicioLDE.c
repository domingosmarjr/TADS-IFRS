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

// TAMANHO DA LISTA
void length (LDE lista) {
    printf("\n%d\n",lista.num);
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


int insereNaPosicao(LDE *ls, Tarefa *tf, int posicao) {
    // Se a posição é maior que o limite (adiciona no fim)
    if (posicao >= ls->num) {
        insereNoFim(ls, tf);
        return 1;
    } else if (posicao <= 0) { // Se a posição é inferior que posição 0
        insereNoInicio(ls, tf);
        return 1;
    } else { //Percorre até a posição
        
        // Faz um get do item na posição
        Tarefa *aux = ls->primeiro;
        for (int i = 0; i < posicao - 1; i++) {
            aux = aux->proximo;
        }

        // O próximo do item, é o próximo do item da lista
        tf->proximo = aux->proximo;
        // O anterior do item é item iterado da lista
        tf->anterior = aux;

        // Verifica se o próximo não é o fim
        if(aux->proximo != NULL) {
            // Define que o anterior do próximo é o item
            aux->proximo->anterior = tf;
        } else {
            // Próximo é null, logo o último da lista é o item
            ls->ultimo = tf;
        }
        aux->proximo = tf;
        ls->num++;
        return ls->num;
    }
}

Tarefa* removeNoInicio (LDE *lista) {
    if(lista->primeiro == NULL) {
        return NULL;
    } else {
        // Guarda o primeiro
        Tarefa *aux = lista->primeiro;
        
        // Define o início da lista o próximo
        lista->primeiro = aux->proximo;

        // Se o primeiro da lista existe
        if (lista->primeiro != NULL) {
            // Define que o anterior é NULL, marcando início da lista
            lista->primeiro->anterior = NULL;
        } else {
            // Define que a lista é vazia
            lista->ultimo = NULL;
        }

        lista->num--;
        aux->proximo = NULL;
        aux->anterior = NULL;

        return aux;
    }
}

Tarefa* removeNoFim (LDE *lista) {
    // Lista tem 1 elemento?
    if(lista->ultimo == NULL) {
        return NULL;
    } else { //Lista tem >1 elemento
        // Guarda último node
        Tarefa *aux = lista->ultimo;

        // Atualiza o último da lista
        lista->ultimo = aux->anterior;

        // Se o último tem elemento
        if (lista->ultimo != NULL) {
            // Define o fim da lista, com o próximo como NULL
            lista->ultimo->proximo = NULL;
        } else {
            // Lista vazia
            lista->primeiro = NULL;
        }

        lista->num--;

        aux->proximo = NULL;
        aux->anterior = NULL;

        return aux;
    }
}

Tarefa* removeNaPosicao (LDE *lista, int p) {
    // Lista vazia
    if (lista->primeiro == NULL) {
        return NULL;
    }

    // Posição inválida
    // > remove na posição, não na quantidade
    if (p < 0 || p >= lista->num) {
        return NULL;
    }

    if (p == 0) return removeNoInicio(lista);
    if (p == (lista->num - 1)) return removeNoFim(lista);

    Tarefa *aux = lista->primeiro;
    for (int i = 0; i < p; i++) {
        aux = aux->proximo;
    }

    aux->anterior->proximo = aux->proximo;
    aux->proximo->anterior = aux->anterior;

    lista->num--;

    aux->proximo = NULL;
    aux->anterior = NULL;

    return aux;
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

void mostraTarefaPosicao (LDE *lista, int posicao) {
    if (lista->primeiro == NULL) {
        printf("\nLista vazia.\n");
        return;
    }
    if (posicao >= 0 && posicao < lista->num) {
        Tarefa *aux = lista->primeiro;
        for (int i = 0; i < posicao; i++) {
            aux = aux->proximo;
        }
        mostraTarefa(*aux);
    } else {
        printf("\nPosição Inválida.\n");
    }

}

void apagaLDE (LDE *lista) {

    // Lista vazia já
    if (lista == NULL) return;

    Tarefa *aux = lista->primeiro;
    Tarefa *aux2;

    while (aux != NULL) {
        aux2 = aux;
        aux = aux->proximo;
        free(aux2);
    }
    free(lista);
}

void apagaTarefa(Tarefa *tarefa) {
    free(tarefa);
}


int main(){

    LDE lista1 = {NULL, NULL, "Tasks", 0};
    mostraListaED(lista1);

    Tarefa t1 = {"Acordar", 1, 10, 0, NULL, NULL};
    Tarefa t2 = {"Comer", 2, 8, 0, NULL, NULL};
    Tarefa t3 = {"Escovar", 2, 8, 0, NULL, NULL};
    Tarefa t4 = {"Trabalhar", 2, 8, 0, NULL, NULL};
    Tarefa t5 = {"Dormir", 2, 8, 0, NULL, NULL};
    Tarefa t6 = {"LIMPAR", 2, 8, 1, NULL, NULL};

    // Inserindo início e fim
    insereNoInicio(&lista1, &t1);
    insereNoFim(&lista1, &t2);
    insereNoFim(&lista1, &t3);

    // Inserindo i < 0
    insereNaPosicao(&lista1, &t5, -1);

    // Inserindo i > n
    insereNaPosicao(&lista1, &t4, 10);

    // Inserindo p > 0 && p < n
    insereNaPosicao(&lista1, &t6, 3);

    mostraListaED(lista1);

    removeNoInicio(&lista1);
    removeNoInicio(&lista1);
    removeNoInicio(&lista1);
    removeNoInicio(&lista1);
    removeNoInicio(&lista1);
    removeNoInicio(&lista1);
    
    mostraListaED(lista1);

    insereNoInicio(&lista1, &t1);
    insereNoInicio(&lista1, &t2);
    insereNoInicio(&lista1, &t3);
    insereNoInicio(&lista1, &t4);
    insereNoInicio(&lista1, &t5);
    insereNoInicio(&lista1, &t6);

    mostraListaED(lista1);

    removeNoFim(&lista1);
    removeNoFim(&lista1);
    removeNoFim(&lista1);
    removeNoFim(&lista1);
    removeNoFim(&lista1);
    removeNoFim(&lista1);

    mostraListaED(lista1);

    insereNoInicio(&lista1, &t1);
    insereNoInicio(&lista1, &t2);
    insereNoInicio(&lista1, &t3);
    insereNoInicio(&lista1, &t4);
    insereNoInicio(&lista1, &t5);
    insereNoInicio(&lista1, &t6);

    mostraListaED(lista1);

    removeNaPosicao(&lista1, 1);
    removeNaPosicao(&lista1, 1);
    removeNaPosicao(&lista1, 1);
    removeNaPosicao(&lista1, 1);
    // removeNaPosicao(&lista1, 1);
    // removeNaPosicao(&lista1, 0);
    
    mostraListaED(lista1);
    mostraTarefaPosicao(&lista1, 2);

}
