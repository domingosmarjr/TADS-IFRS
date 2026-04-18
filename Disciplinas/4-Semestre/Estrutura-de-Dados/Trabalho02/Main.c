#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Musica.h"
#include "Playlist.h"

int main() {

    char opcao;
    Playlist *p1 = criaPlaylist();
    
    // Preset de músicas na playlist
    carregarPreset(p1);

    do {
        interfacePlayer(p1);

        scanf(" %c", &opcao);

        switch (opcao) {
            case 'P':
                play(p1);
                break;
            case '>':
                avancarMusica(p1);
                pausa();
                break;
            case '<':
                retrocederMusica(p1);
                pausa();
                break;
            case 'I':
                adicionarMusicaUsuario(p1, 'I');
                break;
            case 'F':
                adicionarMusicaUsuario(p1, 'F');
                break;
            case 'N':
                adicionarMusicaUsuario(p1, 'N');
                break;
            case 'D':
                deletarMusica(p1);
                break;
            case 'M':
                mostraPlaylistED(*p1);
                pausa();
                break;
            case 'L':
                limparPlaylist(p1);
                break;
            case 'Q':
                qntMusicasPrint(p1);
                break;
            case 'R':
                removerMusicaUsuario(p1, 'I');
                pausa();
                break;

            case 'T':
                removerMusicaUsuario(p1, 'F');
                pausa();
                break;

            case 'X':
                removerMusicaUsuario(p1, 'N');
                pausa();
                break;
            case 'S':
                break;
            default:
                break;
        }
    } while (opcao != 'S');

    deletarPlaylist(p1);
    return 0;
}
