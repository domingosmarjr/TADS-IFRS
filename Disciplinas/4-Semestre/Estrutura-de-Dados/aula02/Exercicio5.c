#include <stdio.h>
#include <stdlib.h>

void dividaAB (int *pa, int *pb, double *pab) {
    *pab = *pa / *pb;
}

int main() {
    int a = 10;
    int b = 4;
    double resultado;

    printf("A:");
    scanf("%d",&a);
    printf("B:");
    scanf("%d",&b);

    dividaAB(&a, &b, &resultado);

    printf("A = %d\n", a);
    printf("B = %d\n", b);
    printf("RESULTADO = %lf\n", resultado);

}