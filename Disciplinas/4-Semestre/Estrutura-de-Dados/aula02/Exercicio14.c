#include <stdio.h>
#include <stdlib.h>

void consoantes (char vetor[], int qntElementos, char vetFim[]) {
    int j = 0;
    for (int i = 0; i < qntElementos; i++)
    {
        if(vetor[i] != 'a' && vetor[i] != 'e' && vetor[i] != 'i' && vetor[i] != 'o' && vetor[i] != 'u') {
            vetFim[j] = vetor[i];
            j++;
        }
    }
    // Precisa adicionar \0 no fim, para não acumular lixo de memória.
    vetFim[j] = '\0';
    
}

int main(){
    // Escreva uma função para receber uma String (vetor char), a quantidade de
    // elementos do vetor e um ponteiro (vetor) para armazenar todas as consoantes
    // presentes na String.

    char texto[10];
    char consoante[10];

    scanf("%s", texto);
    printf("%s\n", texto);

    consoantes(texto, 10, consoante);

    printf("%s\n", consoante);

}