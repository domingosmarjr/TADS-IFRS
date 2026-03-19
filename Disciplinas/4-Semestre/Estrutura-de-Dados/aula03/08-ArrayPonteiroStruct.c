#include <stdio.h>
#include <stdlib.h>

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
    
    // VETOR ARRAY ESTÁTICO
    Pessoa vetorPessoasEstatica[4];

    // VETOR ARRAY DE PONTEIROS 
    // - Cada posição do vetor tem um ponteiro para uma struct pessoa
    // - Estruturas não foram alocadas na criação do vetor

    Pessoa *vetorPessoas[4];

    for(int i = 0; i < 4; i++) {
        //Aloca memória para cada uma das Pessoas
        // - Preciso alocar para cada um dos sujeitos.
        vetorPessoas[i] = (Pessoa*)malloc(sizeof(Pessoa));
        vetorPessoas[i]->cod = i + 1;
        printf("NOME:");
        scanf("%s", vetorPessoas[i]->nome);
        printf("IDADE:");
        scanf("%d", vetorPessoas[i]->idade);

    }

}