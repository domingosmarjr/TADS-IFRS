#include <stdio.h>
#include <stdlib.h>

// TYPEDEF
// TypeDef fornece mecanismo de criação de sinônimos para o novo tipo de dados.

typedef struct pessoa {
    int cod;
    char nome[15];
    char sobrenome[15];
    int idade;
    char telefone[10];
} Pessoa;
// se coloca o sinônimo no fim, para melhor legibilidade.

// struct pessoa -> passa a se chamar Pessoa
// typedef struct pessoa Pessoa;

int main () {
    Pessoa joao;
    Pessoa maria = {2, "Maria", "Aparecida", 23, "454423232"};
}

