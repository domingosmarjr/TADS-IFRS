#include <stdio.h>
#include <stdlib.h>

int main()
{
    // Exemplo de ponteiro para int
    int *p;

    // Ponteiro de Ponteiro
    // um ponteiro que aponta para outro ponteiro
    // **r -> *p -> p
    int **r;

    //============== EXEMPLO ===================
    int x = 10;
    int y = 20;

    int *px = &x;
    int *py = &y;
    int **ppx = &px;

    printf("\n|ENDEREÇO\t|VALOR\t\t|VARIÁVEL\n");
    printf("|%p\t|%d\t\t|x\n", &x, x);
    printf("|%p\t|%d\t\t|y\n", &y, y);
    printf("|%p\t|%p\t|*px\n", &px, px);
    printf("|%p\t|%p\t|**px\n\n", &ppx, ppx);

    printf("Valor de x: %d\n", x);
    printf("Valor de *px: %d\n", *px);
    printf("Valor de **px: %d\n\n", **ppx);
    printf("x == *px == **px -> 10\n\n");

}