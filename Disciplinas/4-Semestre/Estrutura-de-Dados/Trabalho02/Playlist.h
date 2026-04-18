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

// - Inicializar playlist
Playlist *criaPlaylist();

// - Preset para testes
void carregarPreset(Playlist *plys);

// - Interface do terminal
void pausa();
void limparTela();
void interfacePlayer(Playlist *plys);

// - Operações de inserção na LDE
void insereInicio(Playlist *plys, No *no);
int inserePosicao(Playlist *plys, No *no, int p);
void insereFim(Playlist *plys, No *no);
void adicionarMusicaUsuario(Playlist *plys, char tipo);

// - Operações de remoção na LDE
No* removeInicio(Playlist *plys);
No* removePosicao(Playlist *plys, int p);
No* removeFim(Playlist *plys);
void removerMusicaUsuario(Playlist *plys, char tipo);

// - Operações do Player por Usuário
Musica* play(Playlist *plys);
void avancarMusica(Playlist *plys);
void retrocederMusica(Playlist *plys);

// - Quantificador de Playlist
int qntMusicas(Playlist *plys);
int qntMusicasPrint(Playlist *plys);

// - Print das Músicas
void mostraMusica (No no);
void mostraMusicaAtual (Playlist *plys);
void mostraPlaylistED(Playlist plys);

// - Operações de limpeza de memória
No* deletarMusica(Playlist *plys);
void limparPlaylist(Playlist *plys);
void deletarPlaylist(Playlist *plys);

#endif
