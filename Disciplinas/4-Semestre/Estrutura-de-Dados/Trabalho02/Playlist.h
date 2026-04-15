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
void insereNoFim (Playlist *plys, No *no);
void mostraMusica (No no);
void mostraPlayslistED (Playlist plys);

#endif