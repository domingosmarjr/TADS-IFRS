// programa principal para teste
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "LSE.h"

int main()
{

    printf("Exemplo - Lista Simplesmente Encadeada - Lista de Alunos\n");

    // declaração de ponteiro de Lista
    LSE *matematica;
    // inicialização da Lista
    criaLista(&matematica, "Matematica");

    // declara e inicializa um novo elemento da lista de alunos
    Aluno joao = {"Joao Pedro", 23, 12345};
    joao.proximo = NULL;
    insereInicio(matematica, &joao);
    mostraLista(*matematica);

    // novo aluno declarado estaticamente
    Aluno paulo;
    cadastraAluno(&paulo, "Paulo Roberto", 34, 232323);
    insereInicio(matematica, &paulo);
    mostraLista(*matematica);

    Aluno maria;
    cadastraAluno(&maria, "Maria", 21, 334343);
    insereInicio(matematica, &maria);
    mostraLista(*matematica);

    // // //Aloca dinamicamente o espaço para um novo Aluno e atribui o endereço a um ponteiro
    // menuTesteLista(&matematica);

    exit(0);
}