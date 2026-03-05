#include <stdio.h>;
int main()
{
    system("clear");
    // int x = 10;
    // double y = 20, z = 30;

    // printf("\nEndereo de X = %x e o valor X = %d \n", &x, x);
    // printf("\nEndereco de Y = %x e o valor Y = %f \n", &y, y);
    // printf("\nEndereco de Z = %x e o valor Z = %f \n", &z, z);

    int x;
    int *px;

    x = 10;
    px = &x;

    printf("Valor x = %d e o endereço de x = %x\n", x, &x);
    printf("Conteúdo px = endereco de %x \n", px);
    printf("Endereço de px = %x \n", &px);

    printf("Conteúdo de X = %d e o conteúdo referenciado por px = %d\n", x, *px);
    // *px -> mostra mesmo valor de x, porque referencia X
    // (o que tem dentro desse alguém?)
    // direcionamento de memória

    // & -> endereco de memória
    // %x -> hexadecimal

    

}