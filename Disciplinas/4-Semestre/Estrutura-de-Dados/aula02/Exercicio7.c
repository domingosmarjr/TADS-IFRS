#include <stdio.h>
#include <stdlib.h>

void maiorVetor (int *vt, int n, int *maior) {

    for (int i = 0; i < n; i++) {
        if(vt[i] > *maior) *maior = vt[i];
        // Guarda o valor maior diretamente no maior
        // a partir da referência do ponteiro *maior.
    }
}

int main()
{

    int maior_valor;
    int v[10] = {2,3,6,290,0,-2,9,99,2,30};

    
    printf("==================\n");
    printf("|ENDEREÇO\t|VALOR\t|VARIÁVEL\n");
    printf("|%p\t|%d\t|maior_valor\n", &maior_valor, maior_valor);
    printf("|%p\t|%d\t|v[0]\n", &v[0], v[0]);
    printf("|%p\t|%d\t|v[1]\n", &v[1], v[1]);
    printf("|%p\t|%d\t|v[2]\n", &v[2], v[2]);
    printf("|%p\t|%d\t|v[3]\n", &v[3], v[3]);
    printf("|%p\t|%d\t|v[4]\n", &v[4], v[4]);
    printf("|%p\t|%d\t|v[5]\n", &v[5], v[5]);
    printf("|%p\t|%d\t|v[6]\n", &v[6], v[6]);
    printf("|%p\t|%d\t|v[7]\n", &v[7], v[7]);
    printf("|%p\t|%d\t|v[8]\n", &v[8], v[8]);
    printf("|%p\t|%d\t|v[9]\n", &v[9], v[9]);

    maiorVetor(v, 10, &maior_valor);

    printf("==================\n");
    printf("|ENDEREÇO\t|VALOR\t|VARIÁVEL\n");
    printf("|%p\t|%d\t|maior_valor\n", &maior_valor, maior_valor);
    printf("|%p\t|%d\t|v[0]\n", &v[0], v[0]);
    printf("|%p\t|%d\t|v[1]\n", &v[1], v[1]);
    printf("|%p\t|%d\t|v[2]\n", &v[2], v[2]);
    printf("|%p\t|%d\t|v[3]\n", &v[3], v[3]);
    printf("|%p\t|%d\t|v[4]\n", &v[4], v[4]);
    printf("|%p\t|%d\t|v[5]\n", &v[5], v[5]);
    printf("|%p\t|%d\t|v[6]\n", &v[6], v[6]);
    printf("|%p\t|%d\t|v[7]\n", &v[7], v[7]);
    printf("|%p\t|%d\t|v[8]\n", &v[8], v[8]);
    printf("|%p\t|%d\t|v[9]\n", &v[9], v[9]);

}