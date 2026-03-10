#include <stdio.h>
#include <stdlib.h>

void trocal (int a, int b) {
    int temp;
    temp = a;
    a = b;
    b = temp;
}

int main()
{
    int x = 10;
    int y = 20;

    printf("TROCA 1: x = %d | y = %d\n", x, y);
    trocal(x,y);
    printf("TROCA 2: x = %d | y = %d\n", x, y);

    // NÃO REALIZA A TROCA - PASSAGEM POR VALOR
    // x, y, a e b são variáveis distintas na memória em C
    // 
    // |ENDEREÇO    |VALOR      |VARIÁVEL
    // |e943ab      |10         |x
    // |e943ad      |20         |y
    // |e943af      |10         |a
    // |e943b0      |20         |b
    // 
    // Troca só é realizada dentro do ESCOPO da FUNÇÃO trocal()!

}