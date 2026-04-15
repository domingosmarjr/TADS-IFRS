#include <stdio.h>
#include <stdlib.h>
#include "Musica.h"

void exibirMusica (Musica *musica) {
    printf("%d | %s - %s| %ds\n", musica->id, musica->titulo, musica->artista, musica->duracao);
}