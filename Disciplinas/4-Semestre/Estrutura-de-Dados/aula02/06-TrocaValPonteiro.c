#include <stdio.h>
#include <stdlib.h>

int main()
{
    int x, y;
    int *px, *py;
    int aux;

    x = 10, px = &x;
    y = 20, py = &y;

    printf("\nValor x = %d | y = %d\n", x, y);
    aux = *px;
    *px = *py;
    *py = aux;
    printf("Valor x = %d | y = %d\n\n", x, y);

}