#ifndef CONVIDADO_H
#define CONVIDADO_H
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

// CONVIDADO
typedef struct convidado {
    int codigo;
    char nome[100];
    char telefone[20];
    int confirmou; //1 - true | 0 - false
} Convidado;

#endif