#include <stdio.h>
#include <stdlib.h>

int main()
{
    // ALOCAÇÃO DE MEMÓRIA DINÂMICA

    // Malloc:
    // - Função que reserva uma quantidade pré-determinada de memória
    // e retorna o endereço do primeiro byte alocado. Ela precisa ser
    // usada junto com um casting.

    int *px; //Declarar ponteiro

    // FUNÇÃO MALLOC = ALOCA BYTES DE MEMÓRIA
    //   (castParaInt*)malloc(tamanhoEmBytes)
    px = (int*)malloc(sizeof(int));
    
    *px = 20;

    printf("\n|ENDEREÇO\t|VALOR\n");
    printf("|%p\t|%d\n", px, *px);

    // LIBERAR MEMÓRIA
    free(px);
    exit(0);
}