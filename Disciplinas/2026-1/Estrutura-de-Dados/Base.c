// Include <nomeBiblioteca>
// C não é orientado a objetos, logo precisa de imports (include)
#include <stdio.h>
#include <stdlib.h>

int main()
{
    printf("\nHello World!\n");

    // 4 tipos primitivos de C
    char letr;       //1 bytes (-128 a 127)
    int idade;        //2 bytes (-32.768 a 32.767)
    float salario;    //4 bytes (3.4e-38 a 3.4e+38)
    double media;     //8 bytes (1.7e-308 a 1.7e+308)
    //  0   0  0  0  O  O  O  O
    // 128 64 32 16  8  4  2  1 = 255

    int soma = 10;
    float money = 2.2;
    char letra = 'A';
    double pi = 3.1415E6;

    printf("\n Valor da soma = %d\n", soma); //d = int
    printf("\n Valor do money = %f", money); //f = float
    printf("\n Valor da letra = %c", letra); //c = char
    printf("\n Valor de Pi = %e", pi); //e = double
    // Sequência de caracteres -> %s


    // SCANF = String de controle e inserção
    // & é usado somente com tipo primitivo! (representa o endereço da unidade idade na memoria)
    scanf("%d",&idade);
    printf("\n Sua idade é: %d \n", idade);

    // %c -> caracter -> &ch
    // %d -> inteiro
    // %e -> notação científica
    // %f -> float
    // %o -> octal
    // %x -> hexadecimal (IMPORTANTE)
    // %s -> string (cadeira de char)
    // %lf -> double

    printf("\n Endereço de %d: %x \n", idade, idade);

    // FOR
    for (int i = 48; i < 125; i++) {
        // printf("\n Letra %c - Código ASC = %d", i, i);
    }

    // ENDEREÇOS
    // célula de memória = espaço que contem (endereço + informação) [1 byte]
    // &idade [ocupa 4 bytes]: 
    // 68e99724 | ---------------|
    // 68e99725 | ---------------| TODA REGIÃO ALOCADA PARA O INTEIRO
    // 68e99726 | ---------------|
    // 68e99727 | ---------------|

    int idade1;
    scanf("%d",&idade1);
    printf("\n Sua idade é: %d \n", idade1);
    printf("\n IDADE esta armazenada memoria = %x \n", &idade1);

    // PONTEIRO É COMO UM LINK DE ENDEREÇO
    // - Te permite navegar para a posição x
    // - Ponteiro de ponteiro = ponteiro que aponta para outro até um fim
    // - PONTEIRO ARMAZENA APENAS ENDEREÇOS DE MEMÓRIA

    // STRING -> Vetor de chars
    char nome[30];
    scanf("%s",nome);
    printf("\n Nome: %s", nome);

    // BOOLEANOS DE C = 1 e 0
    int teste1 = 12 > 20; //0
    int teste2 = 12 < 20; //1
}

// COMO COMPILAR EM C
// 
// -Como compilar o arquivo:
// gcc NomeArquivo.c -0 nomeExecutavel
// 
// Executa o arquivo em C compilado:
// ./nomeExecutavel
// 
// gcc fonte.c <ENTER>
// gcc fonte.c -o nomeexec <ENTER>
// ./nomeexec <ENTER>