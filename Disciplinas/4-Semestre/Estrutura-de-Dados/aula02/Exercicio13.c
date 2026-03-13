#include <stdio.h>
#include <stdlib.h>

void funcao (char v[], int qntV, char vetor[]) {
    int j = 0;
    for (int i = 0; i < qntV; i++) {
        if (v[i] == 'a' || v[i] == 'e' || v[i] == 'i' || v[i] == 'o' || v[i] == 'u' || v[i] == 'A' || v[i] == 'E' || v[i] == 'I' || v[i] == 'O' || v[i] == 'U') {
            vetor[j] = v[i];
            j++;
        }
    }
}

void imprimeString(char str[], int n){
    for (int i = 0; i < n; i++){
        printf("\ns[%d] = %c", i, str[i]);
    }
}

int main()
{
    char string[] = "Estrutura de Dados";
    char vogais[18] = {};

    imprimeString(string, 18);

    funcao(string, 18, vogais);

    imprimeString(vogais, 18);
}