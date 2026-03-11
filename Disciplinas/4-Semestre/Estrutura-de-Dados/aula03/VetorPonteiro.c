#include <stdio.h>
#include <stdlib.h>

int main()
{
    // VETOR COM 5 POSIÇÕES DE PONTEIRO DE INTEIRO
    int *vponteiro[5];

    for (int i = 0; i < 5; i++) {
        *(vponteiro + i) = (int *)malloc(sizeof(int));
    }

    for (int i = 0; i < 5; i++) {
        **(vponteiro + i) = 10 * 1;
    }

    /*
        AA1 |   BAF  | [0 *px]
        AA2 |   BB1  | [1 *px]
        AA3 |   BB2  | [2 *px]
        AA4 |   BB3  | [3 *px]
        AA5 |   BB4  | [4 *Px]
            |        |
        AB1 |   AA1  | [vPonteiro]
            |        | 
        BAF |   0    |
        BB1 |   10   |
        BB2 |   20   |
        BB3 |   30   |
        BB4 |   40   |

    */




}