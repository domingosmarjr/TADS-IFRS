#include <stdio.h>
#include <stdlib.h>

typedef struct pessoa {
    int cod;
    char nome[15];
    char sobrenome[15];
    int idade;
    char telefone[10];
} Pessoa;


int main() {

    Pessoa *p1;
    p1 = NULL;  //endereço de memória vazio

    p1 = (Pessoa*)malloc(sizeof(Pessoa));
    // Aloca memória para o tamanho de um struct.
    // No caso:
    //  - int      = 4bytes
    //  - char[15] = 15bytes
    //  - char[15] = 15bytes
    //  - int      = 4bytes
    //  - char[10] = 10bytes
    //  TOTAL      = 48 bytes

    printf("\n%zu bytes alocados\n\n",
        sizeof(p1->cod)+
        sizeof(p1->nome)+
        sizeof(p1->sobrenome)+
        sizeof(p1->idade)+
        sizeof(p1->telefone)
    ); 

    // IMPORTANTE LIBERAR MEMÓRIA
    free(p1);
}