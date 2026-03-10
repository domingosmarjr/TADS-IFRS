#include <stdio.h>
#include <stdlib.h>

int main()
{
    int idadeA;
    int idadeB;

    printf("Idade A: ");
    scanf("%d", &idadeA);
    printf("Idade B: ");
    scanf("%d", &idadeB);

    int s;
    int *ps = &s; 
    *ps = idadeA + idadeB;
    // pega a soma, guarda exatamente, referenciado, para s
    // (soma) -> endereço de s -> valor de s

    // 1) Mostrar resultado em S
    printf("\n1)\n");
    printf("S: %d\n", s);

    // 2) Mostrar valor de *ps e endereço de s
    printf("\n2)\n");
    printf("VALOR de *ps = %d\n", *ps);
    printf("ENDEREÇO de s = %p\n", &s);

    // 3) Mostrar o endereço de *ps, mostrar conteúdo referenciado por *ps
    printf("\n3)\n");
    printf("ENDEREÇO *ps = %p\n", &ps);
    printf("CONTEÚDO REF *ps = %d\n", *ps);

    // 4) Definir ponteiro *pab, mostrar endereço e conteúdo de A, usandp *pab
    printf("\n4)\n");
    int *pab = &idadeA;
    printf("ENDEREÇO *pab = %p\n", &pab);
    printf("VALOR A = %d\n", *pab);

    // 5) Trocar endereço de pab pelo endereço de idadeB, mostrar conteúdo em pab
    printf("\n5)\n");
    pab = &idadeB;
    printf("VALOR REF pab = %d\n\n", *pab);

}