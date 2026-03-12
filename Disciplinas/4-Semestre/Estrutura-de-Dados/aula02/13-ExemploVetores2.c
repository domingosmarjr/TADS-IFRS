#include <stdio.h>
#include <stdlib.h>

void imprimeVetor(int vt[], int n){
    for (int i = 0; i < n; i++) {
        printf("v[%d] = %d\n", i, vt[i]);
    }
}

void imprimeVetorPonteiro(int *p, int n) {
    for (int i = 0; i < n; i++){
        printf("v[%d] = %d\n", i, *(p+i)); //ponteiro + incremento
    }
}

void somaDez(int *p, int n) {
    for(int i = 0; i < n; i++){
        *(p+i) += 10;
    }
}


int main()
{
    int v[5] = {3,4,5,2,1};
    int n = 5;

    imprimeVetor(v, n); //passagem por referência pois 'v' é ponteiro!
    imprimeVetorPonteiro(v, n);

    somaDez(v, n);

    imprimeVetor(v, n); //passagem por referência pois 'v' é ponteiro!
    imprimeVetorPonteiro(v, n);

}