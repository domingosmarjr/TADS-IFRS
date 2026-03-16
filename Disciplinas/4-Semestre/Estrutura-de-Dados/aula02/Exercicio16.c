#include <stdio.h>
#include <stdlib.h>

int quantidadeConsoante(char texto[], int qntTexto) {
    int q = 0;
    for (int i = 0; i < qntTexto+1 && texto[i] != '\0'; i++) {
        if(texto[i] != 'a' && texto[i] != 'e' && texto[i] != 'i' && texto[i] != 'o' && texto[i] != 'u'){
            q++;
        }
    }
    return q;
}

int main() {

    int qntTexto;
    int qntConsoante;

    printf("Quantidade texto: ");
    scanf("%d",&qntTexto);
    
    char texto[qntTexto];

    printf("Texto: ");
    scanf("%s", texto);

    qntConsoante = quantidadeConsoante(texto, qntTexto);

    printf("Texto: %s", texto);
    printf("Quantidade Consoantes: %d\n", qntConsoante);

}