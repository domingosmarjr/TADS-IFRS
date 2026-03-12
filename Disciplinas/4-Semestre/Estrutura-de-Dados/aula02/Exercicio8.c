#include <stdio.h>
#include <stdlib.h>

void menorVetor (int v[], int n, int *menor){
    for (int i = 0; i < n; i++) if(v[i] < *menor) *menor = v[i];
}

void maiorVetor (int *vt, int n, int *maior) {
    for (int i = 0; i < n; i++) if(vt[i] > *maior) *maior = vt[i];
}

void maiorMenorVetor (int *vt, int n, int *menor, int *maior){
    menorVetor(vt, n, menor);
    maiorVetor(vt, n, maior);
}

int main()
{
    int v[10] = {2,6,39,290,233,-2,98,65,11,1};
    int menor;
    int maior;

    maiorMenorVetor(v, 10, &menor, &maior);

    printf("Menor: %d\n", menor);
    printf("Maior: %d\n", maior);


}