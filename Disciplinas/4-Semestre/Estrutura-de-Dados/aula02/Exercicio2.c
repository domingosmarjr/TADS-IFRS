#include <stdio.h>
#include <stdlib.h>

int main()
{
    int a, b;
    int *ps;

    printf("A:");
    scanf("%d",&a);
    printf("B:");
    scanf("%d",&b);

    ps = (int*)malloc(sizeof(int));
    *ps = a + b;

    // 1) A+B referenciado pelo Ponteiro *ps
    printf("A + B (referenciado) = %d\n", *ps);

    // 2) Endereço armazenado no ponteiro ps
    printf("Endereço armazendo em ps = %p\n", ps);

    // 3) Endereço do ponteiro ps
    printf("Endereço do ponteiro ps = %p\n", &ps);

    // 4) Endereço de A e B
    printf("Endereço de A = %p\n", &a);
    printf("Endereço de B = %p\n", &b);

    free(ps);
    exit(0);
}