#include <stdio.h>
#include <stdlib.h>

void imprimeString(char str[], int n){
    for (int i = 0; i < n; i++){
        printf("\ns[%d] = %c", i, str[i]);
    }
}

void imprimeStringPonteiro(char *p, int n) {
    for (int i = 0; i < n; i++) {
        printf("\ns[%d] = %c", i, *(p+i)); //*(p+i) -> pega o valor do vetor do ponteiro
    }                                      // equivale ao str[i]
}

void codigoASC(char *p, int n){
    for (int i = 0; i < n; i++){
        printf("\n%c = %d", *(p+i), *(p+i));
    }
}
int main()
{
    char v[] = "Estrutura de Dados";
    int n = sizeof(v);

    imprimeString(v, n); //passagem por referência pois v[] é ponteiro!
    imprimeStringPonteiro(v, n);
    codigoASC(v, n);
    exit(0);
}