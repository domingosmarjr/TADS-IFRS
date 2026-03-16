#include <stdio.h>
#include <stdlib.h>

int main()
{
    int x = 10;
    int y = 20;

    int *px = &x;
    int *py = &y;

    int **ppx = &px;
    int **ppy = &py;

    int *temp;

    temp = px;
    px = py;
    py = temp;

    printf("\nx: %d", x);
    printf("\ny: %d", y);
    printf("\n*px: %d", *px);
    printf("\n*py: %d", *py);
    printf("\n**ppx: %d\n", **ppx);

}