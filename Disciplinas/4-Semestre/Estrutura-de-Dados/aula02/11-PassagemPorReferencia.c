#include <stdio.h>
#include <stdlib.h>

void troca(int *pa, int *pb) {
    int temp;
    temp = *pa;
    *pa = *pb;
    *pb = temp;
}

int main() {
    int x = 10;
    int y = 20;

    printf("TROCA 1: x = %d | y = %d\n", x, y);
    // Função usa ponteiros para executar
    troca(&x,&y);
    printf("TROCA 2: x = %d | y = %d\n", x, y);

    // REALIZA A TROCA - PASSAGEM POR REFERÊNCIA DE MEMÓRIA &
    // x, y, a e b são variáveis distintas na memória em C
    // mas quando passa por referência, opera diretamente no endereço de memória
    // 
    // |ENDEREÇO    |VALOR      |VARIÁVEL
    // |e943ab      |10         |x
    // |e943ad      |20         |y
    // |e943af      |e943ab     |pa
    // |e943b0      |e943ad     |pb
    // 
    // *pa = x;
    // *pb = y;
    // 
    // Troca só é realizada dentro do ESCOPO da FUNÇÃO trocal()!
}