#include "Apartamentos.h"
#include <string.h>

void cadastraApartamento (Apartamento *novo);

void mostraDadosApartamento (Apartamento ap) {
    printf("\n |-----------------------------------------------------------------------");
    printf("\n |Condomínio: %s", ap.condominio);
    printf("\n |Apartamento: %d", ap.num);
    printf("\n |Andar: %d", ap.andar);
    printf("\n |Cômodos: %d", ap.qtd_comodos);
    printf("\n |Box: %d", ap.box);
    printf("\n |Valor Aluguel: %.2f", ap.v_aluguel);
    printf("\n |Valor Condomínio: %.2f", ap.v_condominio);
    printf("\n |Proprietário: %s", ap.proprietario);
    printf("\n |-----------------------------------------------------------------------");
}

void alteraAluguelEm10Porcento(Apartamento *ap) {
    // """Aumentar em 10% o valor do alguel"""
    ap->v_aluguel = ap->v_aluguel * 1.1;
}

int retornaBoxApartamento (Apartamento ap){
    printf("\nBox: %d\n", ap.box);
}

Apartamento* criaNovoApartamento (char *condo, int num, int andar, int box, double v_aluguel, double v_condominio) {
    Apartamento *novo = (Apartamento*)malloc(sizeof(Apartamento));
    strcpy(novo->condominio, condo);
    novo->num = num;
    novo->andar = andar;
    novo->box = box;
    novo->v_aluguel = v_aluguel;
    novo->v_condominio = v_condominio;
    return novo;
}

void cadastraProprietario (Apartamento *ap, char proprietario[30]) {
    strcpy(ap->proprietario, proprietario);
}

void mostrarProprietario (Apartamento ap) {
    printf("\nProprietário: %s\n", ap.proprietario);
}
