#ifndef CONVITE_H
#define CONVITE_H
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include "Convidado.h"

// CONVITE 
typedef struct convite {
    int codigo;
    Convidado *convidado;
    char mensagem[200];
} Convite;

#endif