#include <stdio.h>
#include <stdlib.h>

int main()
{
    // DIFERENÇA DE VALORES = FLOAT E DOUBLE
    // FLOAT  -> MENOR PRECISÃO
    // DOUBLE -> MAIOR PRECISÃO

    float a = 35.111111111111;
    float b = 35.111111111111;
    float c = a + b;

    printf("OPERAÇÕES COM FLOAT - ERRO COMPUTACIONAL\n");
    printf("A: %4.20f\n", a);
    printf("B: %4.20f\n", b);
    printf("C: %4.20f\n", c);

    //------------------------
    double a1 = 35.111111111111;
    double b1 = 35.111111111111;
    double c1 = a1 + b1;

    printf("OPERAÇÕES COM DOUBLE - MAIOR PRECISÃO DE CÁLCULO\n");
    printf("A: %4.20f\n", a1);
    printf("B: %4.20f\n", b1);
    printf("C: %4.20f\n", c1);
}