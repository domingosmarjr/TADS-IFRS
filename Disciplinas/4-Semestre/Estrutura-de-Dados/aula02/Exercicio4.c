#include <stdio.h>
#include <stdlib.h>

void soma(int a, int b, int *pab){
    *pab = a + b;
}


int main()
{
    int a;
    int b;
    int s;

    printf("A:");
    scanf("%d",&a);
    printf("B:");
    scanf("%d",&b);

    // Realiza a soma, mas guarda no endereço de 's' o valor da soma
    soma(a,b,&s);

    printf("A: %d\n",a);
    printf("B: %d\n",b);
    printf("S: %d\n",s);

}