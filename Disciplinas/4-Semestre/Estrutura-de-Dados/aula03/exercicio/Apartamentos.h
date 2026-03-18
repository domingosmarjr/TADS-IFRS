// PROTO BIBILIOTECA EM C [nome.h]

#ifndef APARTAMENTO_H
#define APARTAMENTO_H
#include <stdio.h>
#include <stdlib.h>
#include <locale.h> //Permite Acentuar
#include "Proprietario.h"

// ESTRUTURAS

typedef struct {
    char condominio[30];
    int num, andar, qtd_comodos, box;
    double v_aluguel, v_condominio;
    char proprietario[30];
    Proprietario *dono;
} Apartamento;

void cadastraApartamento (Apartamento *novo);

void cadastraProprietario (Apartamento *ap, char proprietario[30]);

void mostrarProprietario (Apartamento ap);

void mostraDadosApartamento (Apartamento ap);
int retornaBoxApartamento (Apartamento ap);
Apartamento* criaNovoApartamento (char *condo, int num, int andar, int box, double v_aluguel, double v_condominio);

#endif