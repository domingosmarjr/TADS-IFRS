#include <stdio.h>
#include <stdlib.h>
                                // pega o valor do endereço inserido de ponteiro
void menorVetor (int v[], int n, int *menor){
    for (int i = 0; i < n; i++) {
        if(v[i] < *menor) *menor = v[i];
    }
}

int main()
{
    int menor;
    int v1[8] = {3,2,4,5,-3232,2,0-2,3};
    

    printf("%p = %d\n", &menor, menor);

    menorVetor(v1, 8, &menor);
    printf("%p = %d\n", &menor, menor);

}