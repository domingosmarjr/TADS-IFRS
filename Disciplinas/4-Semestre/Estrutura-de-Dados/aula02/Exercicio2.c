#include <stdio.h>;
#include <stdlib.h>;

main() 
{
    // EXEMPLOD DE NULL
    // int *px = NULL;
    // printf("Valor de px = %d", px);

    int *px;
    
    //ESTABELECE UMA MEMÓRIA DE 4 BYTES
    // MALLOC RETORNA O ENDEREÇO ALOCADO
    px = (int*)malloc(sizeof(int)); 
    *px = 20;

    printf("\nEndereco de x = %p e o valor de x = %d\n", &px, *px);
    free(px); //desalocar memória
    exit(0);

}