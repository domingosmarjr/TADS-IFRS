#include <stdio.h>
#include <stdlib.h>

typedef struct pessoa {
    int cod;
    char nome[15];
    char sobrenome[15];
    int idade;
    char telefone[10];
} Pessoa;

void mostraVetor (Pessoa **ps, int n) {
    // Mostra um struct
    Pessoa *p;
    for (int i = 0; i < n; i++){
        p = ps[i];
        printf("Pessoa: %s %s\n", p->nome, p->sobrenome);
        printf("\tCódigo: %d \tIdade: %d\n", p->cod, p->idade);
        printf("\tTelefone: %s\n", p->telefone);
    }
}

void preencheVetor (Pessoa **ps, int n) {
    for (int i = 0; i < n; i++) {
        // Alocação de memória
        ps[i] = (Pessoa*)malloc(sizeof(Pessoa));
        ps[i]->cod = i+1; //Entrada de dados com ponteiros
        printf("Nome:");
        scanf("%s", ps[i]->nome);
        printf("Sobrenome:");
        scanf("%s", ps[i]->sobrenome);
        printf("Idade:");
        scanf("%d", &ps[i]->idade);
        printf("Telefone:");
        scanf("%s", ps[i]->telefone);
    }
}

int main() {
    // DECLARAÇÃO DE UM VETOR DE PONTEIROS PARA 4 PESSOAS
    // - Vetor já é um ponteiro.
    // - Se elementos do vetor forem ponteiros = ponteiro de ponteiro!
    Pessoa *vetorPessoas[2];

    preencheVetor(vetorPessoas, 2);

    mostraVetor(vetorPessoas, 2);

    return 0;
}