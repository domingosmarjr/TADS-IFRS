// BIBLIOTECA APARTAMENTO
#ifndef APARTAMENTO_H
#define APARTAMENTO_H
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <string.h>

typedef struct apartamento {
    char condominio [30];
    int num;
    int andar;
    int qtd_comodos;
    int box;
    double v_aluguel;
    double v_condominio;
    Proprietario *dono;
} Apartamento;

void mostraDadosApartamento (Apartamento ap) {
    printf("\n|=== APARTAMENTO ===\n");
    printf("|Condomínio: %s\n", ap.condominio);
    printf("|Número: %d \t\tAndar: %d\n", ap.num, ap.andar);
    printf("|Cômodos: %d \t\tBox: %d\n", ap.qtd_comodos, ap.box);
    printf("|Aluguel: %.2f \tCondomínio: %.2f\n\n", ap.v_aluguel, ap.v_condominio);
}

void alteraAluguelEm10Porcentos (Apartamento *ap) {
    ap->v_aluguel = ap->v_aluguel * 1.1;
}

int retornaBoxApartamento (Apartamento ap) {
    return ap.box;
}

Apartamento* criaNovoApartamento (char *condominio, int num, int andar, double v_aluguel, double v_condominio, int box) {
    Apartamento *ap = (Apartamento*)malloc(sizeof(Apartamento));
    strcpy(ap->condominio, condominio);
    ap->num = num;
    ap->andar = andar;
    ap->v_aluguel = v_aluguel;
    ap->v_condominio = v_condominio;
    ap->box = box;
    return ap;
}

#endif