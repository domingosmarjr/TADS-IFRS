#ifndef NO_H
#include <stdio.h>
#include <stdlib.h>
#include "Musica.h"

typedef struct No {
    Musica dado;
    struct No *proximo;
    struct No *anterior;
} No;

#endif