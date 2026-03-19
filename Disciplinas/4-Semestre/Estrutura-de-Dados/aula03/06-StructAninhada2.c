#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct endereco {
    char nomeRua[15];
    int numero;
    char cep[10];
} Endereco;

typedef struct pessoa {
    int cod;
    int idade;
    char nome[15];
    Endereco endereco;
} Pessoa;

typedef struct casal {
    char data[10];
    Pessoa *marido;     //Ponteiro para operar nos dados diretos (sem cópias)
    Pessoa *esposa;     //Ponteiro para operar nos dados diretos (sem cópias)
} Casal;

// FUNÇÃO MATRIMÔNIO = RETORNA PONTEIRO CASAL E RECEBE 2 PONTEIROS PESSOA
Casal *matrimonio (Pessoa *p1, Pessoa *p2) {
    Casal *c = (Casal*)malloc(sizeof(Casal));
    strcpy(c->data, "02/06/2020");
    c->marido = p1;
    c->esposa = p2;
    return c;
}

void imprimeCertidaoCasamento(Casal casal) {
    printf("\n\nCERTIDÃO DE CASAMENTO\n");
    printf("\nNa data %s, casaram-se neste cartório ", casal.data);
    printf("\n%s e %s.\n", casal.marido->nome, casal.esposa->nome);
    printf("Dou fé a este Matrimônio.\n\n");
}

int main() {
    Pessoa joao = {1, 23, "João Pedro"};
    Pessoa maria = {2, 24, "Maria Claudia"};
    Endereco minhaCasa;

    Casal *joaoMaria = matrimonio(&joao, &maria);
    imprimeCertidaoCasamento(*joaoMaria);

    free(joaoMaria);
    exit(0);

}