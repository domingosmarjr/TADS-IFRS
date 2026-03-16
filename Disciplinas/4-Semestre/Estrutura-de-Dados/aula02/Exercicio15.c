#include <stdio.h>
#include <stdlib.h>

int quantidadeVogais (char v[], int qntElementos) {

    int q = 0;

    for (int i = 0; i < qntElementos; i++) {
        if (v[i] == 'a' || v[i] == 'e' || v[i] == 'i' || v[i] == 'o' || v[i] == 'u') {
            q++;
        }
    }
    return q;
}

int main(){

    int qntTexto = 10;
    char texto[qntTexto];
    int qntVogais;

    printf("TEXTO:");
    scanf("%s", texto);

    qntVogais = quantidadeVogais(texto, qntTexto);

    printf("Quantidade de vogais: %d\n", qntVogais);
}
