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

int main() {
    Pessoa vetorPessoas[4];

    // Cada posição do vetor é uma estrutura struct
    for (int i = 0; i < 4; i++) {
        vetorPessoas[i].cod = i+1;
        // Entrada de Dados
        printf("NOME:");
        scanf("%s",vetorPessoas[i].nome);
        printf("IDADE:");
        scanf("%d",vetorPessoas[i].idade);
        //Manipular vetor é semelhante e manipular struct.
    }

}