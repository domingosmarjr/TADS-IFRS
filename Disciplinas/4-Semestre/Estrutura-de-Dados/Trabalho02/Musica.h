#ifndef MUSICA_H
#define MUSICA_H
#include <stdio.h>
#include <stdlib.h>

// ====== STRUCTS ======
typedef struct musica {
    int id;
    char titulo[50];
    char artista[50];
    int duracao; //segundos
} Musica;

// ====== MÉTODOS ======
void exibirMusica (Musica *musica);

#endif