#include <stdio.h>
#include <stdlib.h>

// ENDEREÇO DE MEMÓRIA DE DADO
int main()
{
    int idade;
    printf("Insira sua idade: ");
    scanf("%d", &idade);

    //  nomeVariavel -> consulta valor guardado nela
    // &nomeVariavel -> consulta endereço de memória da variável

    printf("Idade = %d\n", idade);
    printf("Endereço de Idade = %x\n", &idade);
}