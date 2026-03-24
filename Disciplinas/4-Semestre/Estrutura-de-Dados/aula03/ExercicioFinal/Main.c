#include <stdio.h>
#include <stdlib.h>
#include "Apartamento.h"
#include <string.h>

int main() {
// Crie dois apartamentos, atribua valores aos seus membros (ap101, ap201);
    Apartamento ap101 = {"Marcílio Dias", 101, 1, 3, 10, 1000.0, 200.0};
    Apartamento ap201 = {"Marcílio Dias", 201, 2, 2, 10, 620.20, 150.2};

// Crie uma função para imprimir os valores de um apartamento (passagem por valor)    
    mostraDadosApartamento(ap101);
    mostraDadosApartamento(ap201);

// Crie uma função para alterar o valor do aluguel de um apartamento (passagem por referência);
    alteraAluguelEm10Porcentos(&ap101);
    mostraDadosApartamento(ap101);

// Crie dois ponteiros de apartamentos, utilize alocação dinâmica (malloc). Após, atribua valores aos campos dos apartamentos (ex. 301 e 401). Após mostre o apartamento criado (301);
    Apartamento *ap301 = (Apartamento*)malloc(sizeof(Apartamento));
    Apartamento *ap401 = (Apartamento*)malloc(sizeof(Apartamento));

    strcpy(ap301->condominio, "Trevo");
    ap301->num = 301;
    ap301->andar = 3;
    ap301->qtd_comodos = 4;
    ap301->box = 43;
    ap301->v_aluguel = 578.32;
    ap301->v_condominio = 200.0;

    strcpy(ap401->condominio, "Jockey");
    ap401->num = 401;
    ap401->andar = 4;
    ap401->qtd_comodos = 3;
    ap401->box = 32;
    ap401->v_aluguel = 654.23;
    ap401->v_condominio = 200.0;

    mostraDadosApartamento(*ap301);
    mostraDadosApartamento(*ap401);

// Defina uma função que recebe um apartamento e retorna o box do apartamento;
    printf("\nBox do Ap %d: %d", ap101.num, retornaBoxApartamento(ap101));
    printf("\nBox do Ap %d: %d", ap201.num, retornaBoxApartamento(ap201));
    printf("\nBox do Ap %d: %d", ap301->num, retornaBoxApartamento(*ap301));
    printf("\nBox do Ap %d: %d\n\n", ap301->box, retornaBoxApartamento(*ap401));

// Defina um função para criar um novo apartamento, a função recebe por parâmetro os dados do apartamento(Condomínio, andar, número, etc) e retorna o ponteiro do novo apartamento (atribua ao 401);
    Apartamento *ap501;
    ap501 = criaNovoApartamento("BGV", 401, 4, 154.2, 20.2, 93);
    mostraDadosApartamento(*ap501);

    
}

