#include <stdio.h>
#include <stdlib.h>
#include "Playlist.h"

Playlist *criaPlaylist() {
    Playlist *playlist = (Playlist*)malloc(sizeof(Playlist));
    playlist->inicio = NULL;
    playlist->fim = NULL;
    playlist->musicaAtual = NULL;
    return playlist;
}

// ============== INSERIR ==============
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

int inserePosicao (Playlist *plys, No *no, int p) {
    int tam = qntMusicas(plys);

    if (p <= 0) {
        insereInicio(plys, no);
        return 1;
    } else if (p >= tam) {
        insereFim(plys, no);
        return 1;
    } else {
        No *aux = plys->inicio;
        for (int i = 0; i < p - 1; i++) {
            aux = aux->proximo;
        }

        no->proximo = aux->proximo;
        no->anterior = aux;

        if (aux->proximo != NULL) {
            aux->proximo->anterior = no;
        }

        plys->fim = no;
        aux->proximo = no;
        // Count aqui
        return 1;
    }
}

void insereFim (Playlist *plys, No *no) {
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


// ============== REMOVER ==============
No* removeInicio (Playlist *plys) {
    if(plys->inicio == NULL) {
        return NULL;
    } else {
        No *aux = plys->inicio;
        plys->inicio = aux->proximo;

        if (plys->inicio != NULL) {
            plys->inicio->anterior = NULL;
        } else {
            plys->fim = NULL;
        }

        aux->proximo = NULL;
        aux->anterior = NULL;

        return aux;
    }
}

No* removePosicao (Playlist *plys, int p) {
    if(plys->inicio == NULL) return NULL;

    int tam = qntMusicas(plys);
    if (p < 0 || p >= tam) return NULL;

    if (p == 0) return removeInicio(plys);
    if (p == (tam - 1)) return removeFim(plys);

    No *aux = plys->inicio;
    for (int i = 0; i < p; i++) {
        aux = aux->proximo;
    }

    aux->anterior->proximo = aux->proximo;
    aux->proximo->anterior = aux->anterior;

    aux->proximo = NULL;
    aux->anterior = NULL;

    return aux;
}

No* removeFim (Playlist *plys) {
    if(plys->fim == NULL) {
        return NULL;
    } else {
        No *aux = plys->fim;
        plys->fim = aux->anterior;

        if (plys->fim != NULL) {
            plys->fim->proximo = NULL;
        } else {
            plys->inicio = NULL;
        }

        aux->proximo = NULL;
        aux->anterior = NULL;

        return aux;
    }
}



// ============== COUNT MUSICAS ==============
int qntMusicas (Playlist *plys) {
    int n = 0;
    No *aux = plys->inicio;
    while(aux != NULL) {
        aux = aux->proximo;
        n++;
    }
    return n;
}

// ============== GETMUSICA ==============
void mostraMusica (No no) {
    exibirMusica(&no.dado);
}

void mostraMusicaAtual (Playlist *plys) {
    printf("\nMÚSICA ATUAL: ");
    exibirMusica(&plys->musicaAtual->dado);
}

// ============== MOSTRA PLAYLIST ==============
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

Musica* play(Playlist *plys) {
    if (plys->inicio == NULL) {
        printf("Não é possível tocar, a playlist está vazia.\n");
        return NULL;
    }

    if(plys->musicaAtual == NULL) {
        plys->musicaAtual = plys->inicio;

    }
    
    return &plys->musicaAtual->dado;
    
}
