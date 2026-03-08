#include <stdio.h>
#include <stdlib.h>

int main()
{
    // OPERADORES LÓGICOS E RELACIONAIS EM C
    // - 0 ZERO = FALSE
    // - 1 UM   = TRUE

    int teste1, teste2;
    teste1 = 12 > 10;
    teste2 = 12 < 10;

    printf("Resultado teste1: %d\n", teste1); //1
    printf("Resultado teste2: %d\n", teste2); //0

    if(teste1) printf("True = %d \n", teste1);
    else printf("False = %d \n", teste1);

    if(teste2) printf("True = %d \n", teste2);//0
    else printf("False = %d \n", teste2);     //1

    // ------------------------------------------
    int teste3 = (10 > 4 && !(10 < 9) || 3 <= 4);
    //             (1)   &&    !(0)   ||   (1)
    //                   (1)          ||   (1)     = (1)
    printf("\nResultado do teste3 = %d\n", teste3);

    int teste4 = (10 > 4 && 10 < 9 || 3 >= 4);
    //              (1)  &&   (0)  ||   (0)
    //                   (0)       ||   (0)        = (0)
    printf("\nResultado do teste4 = %d\n", teste4);
}