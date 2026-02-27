#include <stdio.h>
#include <stdlib.h>

int main () {
    int num1 = 1;
    int num2 = 2;
    int num3 = 3;
    int num4 = 4;

    printf("Numero: %d \n", num1);
    printf("Memoria: %x \n", &num1);
    printf("Numero: %d \n", num2);
    printf("Memoria: %x \n", &num2);
    printf("Numero: %d \n", num3);
    printf("Memoria: %x \n", &num3);
    printf("Numero: %d \n", num4);
    printf("Memoria: %x \n", &num4);

    for (int i = 10; i > 0; i--)
    {
        printf("\n %d - %d = %d", i, num1, (i-num1));
        printf("\n %x", &num1);
    }
    

}