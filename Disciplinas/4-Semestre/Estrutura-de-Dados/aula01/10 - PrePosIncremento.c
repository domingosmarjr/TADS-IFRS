#include <stdio.h>
#include <stdlib.h>

int main()
{
    // PRÉ-INCREMENTO E PÓS-INCREMENTO

    int c = 0;
    int d = 0;

    // INCREMENTA E DEPOIS MOSTRA (ANTES DE PRINTAR FAZ A CONTA)
    printf("C = %d\n", ++c); //1
    printf("C = %d\n", c);   //1

    // MOSTRA E DEPOIS INCREMENTA (DEPOIS DE PRINTAR FAZ A CONTA)
    printf("D = %d\n", d++); //0
    printf("D = %d\n", d);   //1
}