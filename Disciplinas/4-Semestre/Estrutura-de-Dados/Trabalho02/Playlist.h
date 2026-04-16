#ifndef PLAYLIST_H
#define PLAYLIST_H
#include <stdio.h>
#include <stdlib.h>
#include "No.h"

// ====== STRUCTS ======
typedef struct playlist {
    No *inicio;
    No *fim;
    No *musicaAtual;
} Playlist;

// ====== MÉTODOS ======
Playlist *criaPlaylist();
void insereInicio (Playlist *plys, No *no);
int inserePosicao (Playlist *plys, No *no, int p);
void insereFim (Playlist *plys, No *no);

No* removeInicio (Playlist *plys);
No* removePosicao (Playlist *plys, int p);
No* removeFim (Playlist *plys);

Musica* play(Playlist *plys);

void mostraMusica (No no);
void mostraMusicaAtual (Playlist *plys);

void mostraPlayslistED (Playlist plys);
int qntMusicas (Playlist *plys);
#endif