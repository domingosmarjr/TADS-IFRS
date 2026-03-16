#include <stdio.h>
#include <stdlib.h>

int main()
{
    int x = 10;
    int *px = &x;
    int **ppx = &px;

    **ppx = 100;

    printf("\nx: %d\n", x);
    printf("**px: %d\n\n", **ppx);

    *px = 10;
    int y = 20;
    int *py = &y;
    int *soma;
    soma = (int*)malloc(sizeof(int));
    soma = *py + *px;

    printf("x: %d\n", x);
    printf("y: %d\n", y);
    printf("soma: %d\n", soma);
    // free(soma);

}