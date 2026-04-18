#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Playlist.h"

// ========== CRIAR PLAYLIST ==========
Playlist *criaPlaylist() {
    Playlist *playlist = (Playlist*)malloc(sizeof(Playlist));
    playlist->inicio = NULL;
    playlist->fim = NULL;
    playlist->musicaAtual = NULL;
    return playlist;
}

// ======= PRESET DE TESTE =======
void carregarPreset (Playlist *plys) {
    No *novo;

    novo = (No*) malloc(sizeof(No));
    novo->dado.id = 0;
    strcpy(novo->dado.titulo, "In the End");
    strcpy(novo->dado.artista, "Linkin Park");
    novo->dado.duracao = 120;
    novo->proximo = NULL;
    novo->anterior = NULL;
    insereFim(plys, novo);

    novo = (No*) malloc(sizeof(No));
    novo->dado.id = 1;
    strcpy(novo->dado.titulo, "Paradise");
    strcpy(novo->dado.artista, "Stratovarius");
    novo->dado.duracao = 121;
    novo->proximo = NULL;
    novo->anterior = NULL;
    insereFim(plys, novo);

    novo = (No*) malloc(sizeof(No));
    novo->dado.id = 2;
    strcpy(novo->dado.titulo, "Se o Mundo Acabar");
    strcpy(novo->dado.artista, "Rashid");
    novo->dado.duracao = 122;
    novo->proximo = NULL;
    novo->anterior = NULL;
    insereFim(plys, novo);

    novo = (No*) malloc(sizeof(No));
    novo->dado.id = 3;
    strcpy(novo->dado.titulo, "Orientai-me");
    strcpy(novo->dado.artista, "Oriente");
    novo->dado.duracao = 123;
    novo->proximo = NULL;
    novo->anterior = NULL;
    insereFim(plys, novo);

    novo = (No*) malloc(sizeof(No));
    novo->dado.id = 4;
    strcpy(novo->dado.titulo, "Pantera Negra");
    strcpy(novo->dado.artista, "Emicida");
    novo->dado.duracao = 124;
    novo->proximo = NULL;
    novo->anterior = NULL;
    insereFim(plys, novo);
}

// ========== UI/UX INTERFACE ==========
void pausa() {
    printf("\nPressione ENTER para continuar.");
    getchar();
    getchar();
}
void limparTela() {
    system("clear");
}
void interfacePlayer(Playlist *plys) {
    limparTela();

    printf("========================================\n");
    printf("🎵 PLAYLIST - USUÁRIO\n");
    printf("========================================\n\n");

    printf("▶ Tocando agora:\n");
    if (plys->musicaAtual == NULL) printf("Nenhuma música\n");
    else mostraMusica(*plys->musicaAtual);

    printf("\n----------------------------------------\n");
    printf(" Lista:\n");

    No *aux = plys->inicio;
    int i = 0;

    while (aux != NULL) {
        if (aux == plys->musicaAtual) printf("→ ");
        else printf("  ");

        printf("%d. %s - %s\n", i, aux->dado.titulo, aux->dado.artista);
        aux = aux->proximo;
        i++;
    }

    printf("\n----------------------------------------\n");
    printf("Controles:\n");
    printf("[P] Play\t\t[>] Próxima\t\t[<] Anterior\n");
    printf("[I] Inserir Início\t[F] Inserir Fim\t\t[N] Inserir Posição\n");
    printf("[D] Deletar\t\t[L] Limpar\t\t[M] Mostrar\n");
    printf("[R] Remover Início\t[T] Remover Fim\t\t[X] Remover Posição\n");
    printf("[S] Sair\n");
    printf("========================================\n");
    printf("Opção: ");
}


// ========== INSERE INICIO ==========
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
}

// ========== INSERE N ==========
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

        if (aux->proximo != NULL) aux->proximo->anterior = no;
        
        aux->proximo = no;

        return 1;
    }
}

// ========== INSERE FIM ==========
void insereFim (Playlist *plys, No *no) {
    no->proximo = NULL;
    if (plys->inicio == NULL) insereInicio(plys, no);
    else {
        no->anterior = plys->fim;
        plys->fim->proximo = no;
        plys->fim = no;
    }
}

// ========== ADICIONAR MÚSICA PELO USUÁRIO ==========
void adicionarMusicaUsuario (Playlist *plys, char tipo) {
    int id;
    int duracao;
    int pos = 0;
    char titulo[50];
    char artista[50];

    printf("Insira os dados da música: \n");
    printf("ID: ");
    scanf("%d", &id);

    getchar();
    printf("Título: ");
    fgets(titulo, 50, stdin);
    titulo[strcspn(titulo, "\n")] = '\0';

    printf("Artista: ");
    fgets(artista, 50, stdin);
    artista[strcspn(artista, "\n")] = '\0';

    printf("Duração: ");
    scanf("%d", &duracao);

    No *novo = (No*)malloc(sizeof(No));

    if (novo == NULL) {
        printf("Erro de memória!\n");
        return;
    }

    novo->dado.id = id;
    strcpy(novo->dado.titulo, titulo);
    strcpy(novo->dado.artista, artista);
    novo->dado.duracao = duracao;
    novo->proximo = NULL;
    novo->anterior = NULL;

    if (tipo == 'I') insereInicio(plys, novo);
    else if (tipo == 'F') insereFim(plys, novo);
    else if (tipo == 'N') {
        printf("Posição: ");
        scanf("%d", &pos);
        inserePosicao(plys, novo, pos);
    }
}


// ========== REMOVE INICIO ==========
No* removeInicio (Playlist *plys) {
    if(plys->inicio == NULL) return NULL;
    else {
        No *aux = plys->inicio;
        plys->inicio = aux->proximo;

        if (plys->inicio != NULL) plys->inicio->anterior = NULL;
        else plys->fim = NULL;

        aux->proximo = NULL;
        aux->anterior = NULL;

        return aux;
    }
}

// ========== REMOVE N ==========
No* removePosicao (Playlist *plys, int p) {
    if(plys->inicio == NULL) return NULL;

    int tam = qntMusicas(plys);
    if (p < 0 || p >= tam) return NULL;

    if (p == 0) return removeInicio(plys);
    if (p == (tam - 1)) return removeFim(plys);

    No *aux = plys->inicio;
    for (int i = 0; i < p; i++)  aux = aux->proximo;

    aux->anterior->proximo = aux->proximo;
    aux->proximo->anterior = aux->anterior;

    aux->proximo = NULL;
    aux->anterior = NULL;

    return aux;
}

// ========== REMOVE FIM ==========
No* removeFim (Playlist *plys) {
    if(plys->fim == NULL) return NULL;
    else {
        No *aux = plys->fim;
        plys->fim = aux->anterior;

        if (plys->fim != NULL) plys->fim->proximo = NULL;
        else plys->inicio = NULL;

        aux->proximo = NULL;
        aux->anterior = NULL;

        return aux;
    }
}

// ========== REMOVE MÚSICA PELO USUÁRIO ==========
void removerMusicaUsuario (Playlist *plys, char tipo) {
    int pos = 0;
    No *removido = NULL;
    No *novoAtual = NULL;

    if (plys->musicaAtual != NULL) {
        if (plys->musicaAtual->proximo != NULL) novoAtual = plys->musicaAtual->proximo;
        else novoAtual = plys->inicio;
    }

    if (tipo == 'I') removido = removeInicio(plys);
    else if (tipo == 'F') removido = removeFim(plys);
    else if (tipo == 'N') {
        printf("Posição: ");
        scanf("%d", &pos);
        removido = removePosicao(plys, pos);
    }

    if (removido == NULL) printf("Erro ao remover música.\n");
    else {
        printf("Música removida: ");
        mostraMusica(*removido);

        // Só atualiza se removeu a atual
        if (removido == plys->musicaAtual) {
            if (plys->inicio == NULL) plys->musicaAtual = NULL;
            else plys->musicaAtual = novoAtual;
        }

        free(removido);
    }

}

// ============== PLAY =========================
Musica* play(Playlist *plys) {
    if (plys->inicio == NULL) {
        printf("Não é possível tocar, a playlist está vazia.\n");
        return NULL;
    }

    if(plys->musicaAtual == NULL) plys->musicaAtual = plys->inicio;

    printf("\nReproduzindo: ");
    mostraMusica(*plys->musicaAtual);
    printf("\n"); 
    return &plys->musicaAtual->dado;    
}

// ============== AVANCAR MÚSICA ==============
void avancarMusica (Playlist *plys) {
    if (plys->musicaAtual == NULL) return;

    if (plys->musicaAtual == plys->fim) {
        printf("Chegou no fim da playlist. Voltando ao primeiro.\n");
        plys->musicaAtual = plys->inicio;
        return;
    }
    plys->musicaAtual = plys->musicaAtual->proximo;
}

// ============== RETROCEDER MÚSICA ==============
void retrocederMusica (Playlist *plys) {
    if (plys->musicaAtual == NULL) return;

    if (plys->musicaAtual == plys->inicio) {
        printf("Chegou no início da playlist.\n");
        return;
    }
    plys->musicaAtual = plys->musicaAtual->anterior;
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
int qntMusicasPrint(Playlist *plys) {
    printf("\nQuantidade: %d\n", qntMusicas(plys));
}

// ============== MOSTRAR MUSICAS ==============
void mostraMusica (No no) {
    printf("%s - %s\n", no.dado.titulo, no.dado.artista);
}

void mostraMusicaAtual (Playlist *plys) {
    if (plys->musicaAtual == NULL) printf("Nenhuma.\n");
    else mostraMusica(*plys->musicaAtual);
}

// ============== MOSTRA PLAYLIST ==============
void mostraPlaylistED (Playlist plys) {
    No *aux = plys.inicio;
    if (aux == NULL)  printf("\nLista Vazia.\n");
    else {
        printf("\nPLAYLIST ORDENADA:\n");
        while (aux != NULL) {
            mostraMusica(*aux);
            aux = aux->proximo;
        }
        printf("\n=====\n");
    }
}

// ========= LIMPEZA =========
// == DELETAR MÚSICA DA PLAYLIST ==
No* deletarMusica (Playlist *plys) {
    if (plys->musicaAtual == NULL) {
        printf("Playlist já vazia.\n");
        return NULL;
    }
    No *removido = plys->musicaAtual;

    if (plys->inicio == plys->fim) {
        plys->inicio = NULL;
        plys->fim = NULL;
        plys->musicaAtual = NULL;
    }

    // Primeiro da playlist
    else if (removido == plys->inicio) {
        plys->inicio = removido->proximo;
        plys->inicio->anterior = NULL;
        plys->musicaAtual = plys->inicio;
    }

    // Último da playlist
    else if (removido == plys->fim) {
        plys->fim = removido->anterior;
        plys->fim->proximo = NULL;
        plys->musicaAtual = plys->fim;
    }

    // Miolo
    else {
        removido->anterior->proximo = removido->proximo;
        removido->proximo->anterior = removido->anterior;
        plys->musicaAtual = removido->proximo;
    }

    removido->proximo = NULL;
    removido->anterior = NULL;

    free(removido);

    return removido;
}

// == EXCLUIR AS MÚSICAS DA PLAYLIST DA MEMÓRIA ==
void limparPlaylist (Playlist *plys) {
    No *aux = plys->inicio;
    No *proximo;

    while (aux != NULL) {
        proximo = aux->proximo;
        free(aux);
        aux = proximo;
    }

    plys->inicio = NULL;
    plys->fim = NULL;
    plys->musicaAtual = NULL;
}

// == EXCLUIR A PLAYLIST DA MEMÓRIA ==
void deletarPlaylist (Playlist *plys) {
    limparPlaylist(plys);
    free(plys);
}