#include <stdio.h>
#include <stdlib.h>

int main()
{
    int x = 10, *px = &x;
    int y = 20, *py = &y;

    printf("\nValor referenciado de px = %d\n", *px); //10
    printf("Valor referenciado de py = %d\n\n", *py); //20 

    // MUDAR VALOR DE VARIÁVEL SEM ACESSAR ELA
    // MUDAR VALOR APENAS ACESSANDO PONTEIRO
    *px = *py;
    //*px = 20 
    // Isso muda o valor de x indiretamente!

    printf("Valor de x = %d e y = %d\n", x, y);

}