#ifndef TRABALHADOR_H
#define TRABALHADOR_H
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

// TRABALHADOR
typedef struct trabalhador {
    int codigo;
    char nome[100];
    char funcao[50];
    float pagamento;
} Trabalhador;

#endif