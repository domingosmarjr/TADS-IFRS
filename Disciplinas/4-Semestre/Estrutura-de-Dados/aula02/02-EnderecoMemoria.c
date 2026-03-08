#include <stdio.h>
#include <stdlib.h>

int main()
{
    system("clear");
    int x = 10;
    double y = 20;
    double z = 30;
    double w = 23;

    printf("\nEndereco X = %x \t Valor X = %d\n", &x, x); //4bytes
    printf("Endereco Y = %x \t Valor Y = %f\n", &y, z);   //8bytes
    printf("Endereco Z = %x \t Valor Z = %f\n", &z, y);   //8bytes
    printf("Endereco W = %x \t Valor Z = %f\n", &w, w);   //8bytes

    // %p = também imprime endereço de memória (mais completo)

    printf("|ENDEREÇO\t|VALOR\n");
    printf("|%p |%d \n", &x, x);   //4bytes 0x----------cc
    printf("|%p |%f \n", &y, y);   //8bytes 0x----------d0
    printf("|%p |%f \n", &z, z);   //8bytes 0x----------d8
    printf("|%p |%f \n", &w, w);   //8bytes 0x----------e0


    exit(0);

}