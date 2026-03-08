#include <stdio.h>
#include <stdlib.h>

int main()
{
    int vInteiro = 10;
    double vDouble = 15.73;
    char vChar = 'A';
    long int vLongInt = 12;

    printf("O Inteiro ocupa %d na memoria %d Bytes.\n", vInteiro, sizeof(vInteiro));
    printf("O Double ocupa %f na memoria %d Bytes.\n", vDouble, sizeof(vDouble));
    printf("O Char ocupa %c na memoria %d Bytes.\n", vChar, sizeof(vChar));
    printf("O LongInt ocupa %d na memoria %d Bytes.\n", vLongInt, sizeof(vLongInt));
    
}