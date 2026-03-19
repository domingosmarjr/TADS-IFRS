#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct pessoa {
    int cod;
    char nome[15];
    char sobrenome[15];
    int idade;
    char telefone[10];
} Pessoa;

int main() {
    Pessoa maria;
    Pessoa *pmaria;

    // Atribuindo o endereço do ponteiro
    pmaria = &maria;

    // Refenciar membro do ponteiro
    // Se usa a seta '->' para acessar dado do ponteiro
    pmaria->cod = 1;
    pmaria->idade = 30;
    strcpy(pmaria->nome,"Maria");
    strcpy(pmaria->sobrenome, "Antunes");
    strcpy(pmaria->telefone, "5332321122");

    printf("\nPessoa:\n\tNome: %s %s\n", pmaria->nome, pmaria->sobrenome);
    printf("\tCodigo: %d\tIdade: %d\n", pmaria->cod, pmaria->idade);
    printf("\tTelefone: %s\n\n", pmaria->telefone);
}