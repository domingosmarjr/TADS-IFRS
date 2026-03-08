#include <stdio.h>
#include <stdlib.h>

int main()
{
    // SIZEOF() E QUANTIDADE DE BYTES
    int a = 0;
    float b = 10.04f;
    char c = 'A';
    double d = 12.235;

    // sizeof(variavel) -> retorna quantidade de bytes ocupado
    printf("A = %d \t\t\t OCUPA = %d bytes\n", a, sizeof(a));  //4 (int)
    printf("B = %f \t\t OCUPA = %d bytes\n", b, sizeof(b));    //4 (float)
    printf("C = %c \t\t\t OCUPA = %d bytes\n", c, sizeof(c));  //1 (char)
    printf("D = %f \t\t OCUPA = %d bytes\n", d, sizeof(d));    //8 (double)
    
}