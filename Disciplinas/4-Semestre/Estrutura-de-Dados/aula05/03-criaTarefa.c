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

// CRIA UMA LISTA VAZIA E SETA
// - Início NULL
// - Fim NULL
// - n = 0
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

int main() {
    // FUNÇÕES DE LDE
    
    // Inserir Início
    //      void insereNoInicio (Lista *ls, tarefa *tf)
    // Inserir Fim
    //      void insereNoFim (Lista *ls, tarefa *tf)
    // Inserir n
    //      void insereNaPosicao (Lista *ls, tarefa *tf, int p)

    // Remover início
    //      Tarefa* removeNoInicio (Lista *ls)
    // Remover fim
    //      Tarefa* removeNoFim (Lista *ls)
    // Remover n
    //      Tarefa* removeNaPosicao (Lista *ls, int p)

    // Mostrar lista >-->
    //      void mostraListaEsquerdaDireita (Lista ls)
    // Mostrar lista <--<
    //      void mostraListaDireitaEsquerda (Lista ls)

    // Apaga elemento
    //      void apagaElemento (Tarefa *tf)
    // Apaga lista
    //      void apagaLista (Lista *ls)
}