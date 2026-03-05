#include <stdio.h>;

main()
{
    int a;
    int b;

    printf("\nA:");
    scanf("%d", &a);

    printf("\nB:");
    scanf("%d", &b);

    int s = 72;
    int *ps = &s;

    // 1)
    printf("1 - Conteúdo de S = %d\n", s);

    // 2) [CORRETO]
    printf("2 - Endereço do Ponteiro *ps = %x\n", &ps);
    printf("2 - Endereço de S = %x\n", &*ps);

    // 3) 
    printf("3 - Endereco do ponteiro *ps = %x\n", &ps);
    printf("3 - Conteúdo referenciado por *ps = %d\n", *ps);
    
    // 4)
    int *pab = &a;
    printf("4 - VAlor de A = %d\n", a);
    printf("4 - Endereço de A = %x\n", &a);
    printf("4 - Endereço de pab = %x\n", &pab);
    printf("4 - Endereço Rerenciado A(pab) = %x\n", pab);
    printf("4 - Conteúdo de A = %d\n", *pab);

    // 5)
    pab = &b;
    printf("5 - Valor de B = %d | Endereço B = %p\n", *pab, pab);


    // ATRIBUIÇÃO E SOMA
    *ps = a + b;


}