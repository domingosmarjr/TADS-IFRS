#include <stdio.h>
#include <stdlib.h>

int* menorVetorPonteiro (int *v, int n){
    // RETORNA O ENDEREÇO DO MENOR ELEMENTO DO VETOR

    int *menor = &v[0];
    for (int i = 0; i < n; i++) if(v[i] < *menor) menor = &v[i];
    return menor;
}   

int* maiorVetorPonteiro (int *v, int n){
    // RETORNA O ENDEREÇO DO MAIOR ELEMENTO DO VETOR
    int *maior = &v[0];
    for (int i = 0; i < n; i++) if(v[i] > *maior) maior = &v[i];
    return maior;
}

void menorMaiorVetorPonteiro(int *v, int n, int *menor, int *maior){
    int *pmenor = menorVetorPonteiro(v, n);
    int *pmaior = maiorVetorPonteiro(v, n);

    *menor = *pmenor;
    *maior = *pmaior;

}

int main()
{
    int *menor;
    int *maior;

    int vet[5] = {-1,4,-2,-30,1};

    printf("Menor: %p\n", menor);
    menor = menorVetorPonteiro(vet, 5);
    printf("Menor: %p - %d\n", menor, *menor);

    printf("Maior: %p\n", maior);
    maior = maiorVetorPonteiro(vet, 5);
    printf("Maior: %p - %d\n", maior, *maior);

    vet[0] = -10;
    vet[1] = -232;
    vet[2] = 12312;
    vet[3] = 20;
    vet[4] = 232;

    menorMaiorVetorPonteiro(vet, 5, menor, maior);
    printf("Menor: %p - %d\n", menor, *menor);
    printf("Maior: %p - %d\n", maior, *maior);

}