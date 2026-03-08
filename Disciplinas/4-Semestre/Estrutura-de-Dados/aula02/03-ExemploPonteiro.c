#include <stdio.h>
#include <stdlib.h>

int main()
{
    int x;
    int *px;

    x = 10;
    px = &x;

    printf("\nO valor de X = %d | Endereço de X = %x\n", x, &x);
    printf("O valor do ponteiro px = %x\n", px);
    printf("O valor eo endereço do ponteiro px = %x\n", &px);

    /*
        | ENDEREÇO    | VALOR   | 
        | 327e99c     | 10      | x
        | ...         |         |
        | 327e9a0     | 327e99c | *px
    */

    // X tem endereço e guarda uma variável
    // px guarda o endereço de x (assim, ele é um ponteiro que aponta para x)
    // px também tem seu endereço próprio (endereço do ponteiro)
    //  - permite navegabilidade (ponteiro de ponteiro)

    printf("Valor referenciado de px = %d\n", *px);
    // *px -> ponteiro que referencia x
    // logo, valor de *px == valor de x
    //                 x = 10 && *px = 10

    //=====SÍMBOLOS=====
    // * -> usado na declaração de ponteiros e também para consultar e alterar (*px)
    // & -> usado para acessar endereço de memória de determinado campo da memória
    // %x ou %p -> imprime endereço de memória

    // EXEMPLO
    /*
        int x = 10;
        int *px = &x;
        print(*px); -> 10
        print(&px); -> endereço do ponteiro
    */
}