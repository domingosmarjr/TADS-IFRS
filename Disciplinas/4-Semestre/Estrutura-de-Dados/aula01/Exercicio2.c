#include <stdio.h>
#include <stdlib.h>

int main()
{
    // TIPOS -> TIPAGEM FORTE
    int idade;
    float salario;
    double media;
    char letra;

    // LIMITES DOS TIPOS
    //              byte     escala
    char a;    //      1     -128 a 127
    int b;     //      2     -32.768 a 32.767
    float c;   //      4     3.4e-38 a 3.4e+38
    double d;  //      8     1.7e-308 a 1.7e+308
    // long/long int -> 4 bytes
    // unsigned char -> 1 byte
    // unsigned int  -> 2 bytes

    int soma = 10;
    float money = 2.21;
    char let = 'A';
    double pi = 3.1415E6;

    printf("\n Valor da soma: = %d\n", soma);
    printf("\n Valor do money = %f\n", money);
    printf("\n Valor do char = %c - Código ASCII = %d\n", let, let);
    printf("\n Valor do pi = %e\n", pi);

    /*
        %c -> char
        %d -> int
        %e -> notação científica
        %f -> ponto flutuante
        %o -> octal
        %x -> hexadecimal
        %s -> string (array de char)
        %lf -> double
    */

    // LEITURA DE DADOS
    printf("Insira sua idade: ");
    scanf("%d", &idade); //inserir um tipo inteiro no endereço (&) de idade
    printf("\nSua idade é %d.\n", idade);
}