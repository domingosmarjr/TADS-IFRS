#include <stdio.h>
#include <stdlib.h>

// STRUCT
// Grupo ou conjunto de itens, onde cada item tem um identificador.
// Struct possui membros, e o conjunto de membros formam Struct
// Struct é "vó" das classes de Orientação a Objetos.

struct nome_struct {
    // tipo nome_item ou membro;
};

struct pessoa {
    int cod;
    char nome[15];
    int idade;
    float salario;
};

int main() {

    // Declaração direta
    struct pessoa luana = {2, "Luana", 23, 1800.50};

    // Declaração separada
    strcpy(luana.nome, "Luana Pessanha");
    luana.cod = 3;
    luana.idade = 24;
    luana.salario = 2850.20;

    // Vetor de Pessoas
    struct pessoa ListaConvidados[20];

    for (int i = 0; i < 2; i++) {
        ListaConvidados[i].cod = i + 1;
        printf("Nome: ");
        scanf("%s", &ListaConvidados[i].nome);
        printf("Idade: ");
        scanf("%d", &ListaConvidados[i].idade);
    }

    for (int i = 0; i < 2; i++) {
        printf("\n|Código: %d", ListaConvidados[i].cod);
        printf("\n|Nome: %s", ListaConvidados[i].nome);
        printf("\n|Idade: %d\n", ListaConvidados[i].idade);
    }
}