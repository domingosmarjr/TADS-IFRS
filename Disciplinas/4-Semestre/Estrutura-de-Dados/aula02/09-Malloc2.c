#include <stdio.h>
#include <stdlib.h>

int main()
{
    int *px;
    px = (int*)malloc(sizeof(int));

    // Verifica se o malloc conseguiu alocar memória.
    // Se tiver null, não conseguiu, logo estourou memória.
    if(px == NULL) {
        printf("Erro - Memória Insuficiente");
        exit(0);
    }

    *px = 20;
    printf("\nEndereço de x = %p | Valor de x = %d\n", px, *px);
    free(px);
    exit(0);
}