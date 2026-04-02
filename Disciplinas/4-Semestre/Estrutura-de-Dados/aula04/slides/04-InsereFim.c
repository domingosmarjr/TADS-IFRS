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

// DUAS POSSIBILIDADES DE INSERÇÃO = LISTA VAZIA | LISTA COM ELEMENTOS
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

int main() {
    LSE matematica;
    matematica.primeiro = NULL;
    matematica.n = 0;

    Aluno joao = {"Joao Pedro", 23, 12345};
    joao.proximo = NULL;
    Aluno paulo = {"Paulo Roberto", 34, 12346};
    paulo.proximo = NULL;

    mostraLista(matematica);
    insereFim(&matematica, &joao);
    mostraLista(matematica);
    insereFim(&matematica, &paulo);
    mostraLista(matematica);

}