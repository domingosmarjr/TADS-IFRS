#include <stdio.h>
#include <stdlib.h>

int main()
{
    int x;
    int *px;
    x = 10;
    px = &x;

    int y;
    int *py;
    y = 20;
    py = &y;

    printf("\n|ENDEREÇO\t|VALOR\t\t|VARIAVEL\n");
    printf("|%p\t|%d\t\t|x\n", px, x);
    printf("|%p\t|%d\t\t|y\n", py, y);
    printf("|%p\t|%p\t|px\n",&px, px);
    printf("|%p\t|%p\t|py\n",&py, py);
    printf("\n");
    printf("Valor de *px = %d\n", *px);
    printf("Valor de *py = %d\n", *py);

    // SE EU DEFINIR
    // px = py
    // PX guardará o novo endereço de &y
}