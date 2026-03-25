#ifndef FESTA_H
#define FESTA_H
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include "Data.h"
#include "Horario.h"
#include "Convidado.h"
#include "Trabalhador.h"
#include "Convite.h"

// FESTA
typedef struct festa {
    char nome[100];
    char local[100];
    Data data;
    Horario horario;
    
    Convidado convidados[100];
    int totalConvidados;

    Trabalhador trabalhadores[50];
    int totalTrabalhadores;
    
    Convite *convites;
    int totalConvites;
} Festa;

// Inicializador da Festa
void iniciarFesta (Festa *festa);

// Cadastro nicial da festa
void cadastraFesta (Festa *festa);

// Cadastrar convidados
int cadastraConvidado(Festa *festa);

// Cadastrar trabalhador
int cadastraTrabalhador (Festa *festa);

void editarFesta(Festa *festa);

void mostraFesta(Festa *festa);

void mostrarConvidado (Convidado *con);



int confirmarPresenca (Festa *festa);
int cancelaPresenca (Festa *festa);

#endif