#include <stdio.h>
#include <stdlib.h>

int main()
{
    // VETORES E STRINGS SÃO PONTEIRO EM C
    int v[5] = {3,4,5,2,1};
    int x[1] = {1};

    printf("%d bytes\n",sizeof(v)); //20 bytes
    printf("%d bytes\n",sizeof(x)); // 4 bytes

    printf("Endereço x = %p\n", v); //Endereços
    printf("Endereço y = %p\n", &x);
    printf("Endereço de x[1] = %p\n", &v[1]);

}