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

// ========================================
// MOSTRAR
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

void mostraPosicao (LSE *lista, int pos) {

}

// ========================================
// INSERTS
void insereInicio (LSE *lista, Aluno *novo) {
    if(lista->primeiro == NULL) {
        lista->primeiro = novo;
        lista->primeiro->proximo = NULL;
    } else {
        Aluno *aux = lista->primeiro;
        lista->primeiro = novo;
        novo->proximo = aux;
    }
    lista->n++;
}

void inserePosicao (LSE *lista, Aluno *novo, int posicao) {
    if (lista->primeiro == NULL) { //LISTA VAZIA
        novo->proximo = NULL;
        lista->primeiro = novo;
        lista->n++;
        return;
    } else if (posicao <= 0) { //POSICAO < 0
        novo->proximo = lista->primeiro;
        lista->primeiro = novo;
        lista->n++;
        return;
    } else if (posicao >= lista->n) { //POSICAO > NLista
        Aluno *aux = lista->primeiro;
        while (aux->proximo != NULL) {
            aux = aux->proximo;
        }
        aux->proximo = novo;
        novo->proximo = NULL;
        lista->n++;
        return;
    }
    Aluno *aux = lista->primeiro; //INSERE NO MEIO
    for (int i = 0; i < posicao - 1; i++) {
        aux = aux->proximo;
    }
    novo->proximo = aux->proximo;
    aux->proximo = novo;
    lista->n++;
}


void insereFim (LSE *lista, Aluno *novo) {
    // Define o fim da lista
    novo->proximo = NULL;
    if(lista->primeiro == NULL) {
    // Define o novo para o primeiro
        lista->primeiro = novo;
    } else {
        // Usa um auxiliar para navegar e setar o último
        // Salva no último da lista após percorrer
        Aluno *aux = lista->primeiro;
        while (aux->proximo != NULL) {
            aux = aux->proximo;
        }
        aux->proximo = novo;
    }
    lista->n++;
}


// ========================================
// REMOVER
Aluno* removeInicio (LSE *lista) {
    Aluno *aux = lista->primeiro;
    if(aux != NULL) {
        // Lista com +1 elemento
        // Coloca o [1] na posição [0]
        lista->primeiro = aux->proximo;
        lista->n--;
    }
    return aux;
}

Aluno* removePosicao (LSE *lista, int pos) {
    if (pos == 0) {
        return removeInicio(lista);
    }

    Aluno *aux = lista->primeiro;
    for (int i = 0; i < pos - 1; i++) {
        aux = aux->proximo;
    }

    Aluno *removido = aux->proximo;
    aux->proximo = removido->proximo;
    lista->n--;
    return removido;
}

Aluno* removeFim (LSE *lista) {
    if(lista->primeiro->proximo == NULL) {
        lista->primeiro = NULL;
        lista->n--;
        return lista->primeiro;
    } else if (lista->primeiro == NULL) {
        lista->primeiro = NULL;
        lista->n--;
        return lista->primeiro;
    }
    Aluno *aux = lista->primeiro;
    while (aux->proximo != NULL) {
        aux = aux->proximo;
    }
    aux = NULL;
    lista->n--;
    return aux;
}

int retornaQuantidade (LSE *lista) {

}

void apagaAluno (Aluno *aluno) {
    
}

void apagaLista (LSE *lista) {

}


int main() {


    LSE matematica;
    matematica.primeiro = NULL;
    matematica.n = 0;

    Aluno joao = {"Joao Pedro", 23, 12345};
    joao.proximo = NULL;
    Aluno paulo = {"Paulo Roberto", 34, 12346};
    paulo.proximo = NULL;
    Aluno caio = {"Caio", 34, 12346};
    caio.proximo = NULL;


    // INSERE INICIO
    // insereInicio(&matematica, &caio);
    // insereInicio(&matematica, &joao);
    // insereInicio(&matematica, &paulo);

    // INSERE FIM
    // insereFim(&matematica, &joao);
    // insereFim(&matematica, &paulo);
    // insereFim(&matematica, &caio);

    // INSERE POSICAO
    // inserePosicao(&matematica, &joao, 0);
    // inserePosicao(&matematica, &paulo, 0);
    // inserePosicao(&matematica, &caio, 1);

    // mostraLista(matematica);

    // REMOVE INICIO
    // removeInicio(&matematica);
    // removeInicio(&matematica);
    // removeInicio(&matematica);

    // REMOVE FIM
    // removeFim(&matematica);
    // removeFim(&matematica);
    // removeFim(&matematica);


    mostraLista(matematica);

}