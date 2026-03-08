#include <stdio.h>
#include <stdlib.h>

int main()
{
    int total = 360;

    // CASAS VAGAS ANTES DO NÚMERO INTEIRO
    printf("Nº Alunos: %d\n", total);   //360
    printf("Nº Alunos: %2d\n", total);  //  360
    printf("Nº Alunos: %4d\n", total);  //    360
    printf("Nº Alunos: %10d\n", total); //          360

    double valor = 35.123456789;

    printf("Valor: %f \n", valor);      //35.123456789
    printf("Valor: %2.2f \n", valor);   //35.12
    printf("Valor: %2.4f \n", valor);   //35.1235
    printf("Valor: %4.10f \n", valor);  //35.123456789
    printf("Valor: %2.6f\n", valor);    //35.123457
}