#include <stdio.h>
#include <stdlib.h>
#include "Playlist.h"

Playlist *criaPlaylist() {
    Playlist *playlist = (Playlist*)malloc(sizeof(Playlist));
    playlist->inicio = NULL;
    playlist->fim = NULL;
    playlist->musicaAtual = NULL;
}


void insereInicio (Playlist *plys, No *no) {
    no->anterior = NULL;
    if (plys->inicio == NULL) {
        no->proximo = NULL;
        plys->fim = no;
    }
    else {
        no->proximo = plys->inicio;
        plys->inicio->anterior = no;
    }
    plys->inicio = no;
    // Inserir count aqui
}

void insereNoFim (Playlist *plys, No *no) {
    no->proximo = NULL;
    if (plys->inicio == NULL) {
        insereInicio(plys, no);
    } else {
        no->anterior = plys->fim;
        plys->fim->proximo = no;
        plys->fim = no;
        // Inserir count aqui
    }
}



void mostraMusica (No no) {
    exibirMusica(&no.dado);
}


void mostraPlayslistED (Playlist plys) {
    No *aux = plys.inicio;
    if (aux == NULL)  printf("\nLista Vazia.\n");
    else {
        printf("\nPLAYLIST:\n");
        while (aux != NULL) {
            mostraMusica(*aux);
            aux = aux->proximo;
        }
        printf("\n=====\n");
    }
}
