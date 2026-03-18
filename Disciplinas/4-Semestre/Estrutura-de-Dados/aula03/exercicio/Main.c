#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Apartamentos.h"

// typedef struct Apartamento {
//     char condominio[30];
//     int num;
//     int andar;
//     int qtd_comodos;
//     int box;
//     double v_aluguel;
//     double v_condominio;
// } Apartamento;

// typedef struct proprietario {
//     char nome[30];
//     char cpf[14];
// } Proprietario;

int main() {

    printf("\n****** Início da Programa Apartamento  *******\n");

    // 1) Criar apartamentos
    Apartamento ap1 = {"Trevo", 402, 4, 4, 13, 600.00, 200.00};
    Apartamento ap2 = {"Trevo", 302, 3, 4, 11, 500.00, 250.00};

    // 2) Função imprir valores do apartamento
    mostraDadosApartamento(ap1);
    mostraDadosApartamento(ap2);

    // 3) Função de reajuste em 10%
    alteraAluguelEm10Porcento(&ap1);
    mostraDadosApartamento(ap1);

    // 4) Criar ponteiros apartamento e alocar memória
    Apartamento *ap3 = (Apartamento*)malloc(sizeof(Apartamento));
    Apartamento *ap4 = (Apartamento*)malloc(sizeof(Apartamento));

    strcpy(ap3->condominio, "Marcílio Dias");
    ap3->num = 301;
    ap3->andar = 3;
    ap3->qtd_comodos = 3;
    ap3->box = 1;
    ap3->v_aluguel = 700;
    ap3->v_condominio = 220;

    strcpy(ap4->condominio, "Regines");
    ap4->num = 201;
    ap4->andar = 2;
    ap4->qtd_comodos = 5;
    ap4->box = 2;
    ap4->v_aluguel = 900;
    ap4->v_condominio = 150;

    mostraDadosApartamento(*ap3);
    mostraDadosApartamento(*ap4);
    
    free(ap3);
    free(ap4);

    // 5) Função recebe apartamento e retorna seu box.
    retornaBoxApartamento(ap1);
    retornaBoxApartamento(ap2);
    
    Apartamento *ap5 = criaNovoApartamento("BGV", 203, 2, 30, 150.2, 233.2);
    mostraDadosApartamento(*ap5);


    // 6) Adicionar proprietário
    cadastraProprietario(&ap1, "Pedro Mathias");
    mostraDadosApartamento(ap1);
    mostrarProprietario(ap1);
    
    cadastraProprietario(&ap2, "Lucas Tadeu");
    mostraDadosApartamento(ap2);
    mostrarProprietario(ap2);

    // 7) Alocar espaço de novo proprietário e trocar o dono do 101
    char *novoProprietario = (char*)malloc(30 * sizeof(char));
    strcpy(novoProprietario,"Matheus Silva");
    strcpy(ap1.proprietario, novoProprietario);
    mostraDadosApartamento(ap1);
    free(novoProprietario);
    




    printf("\n****** Fim do Programa Apartamento  *******\n\n");
    exit(0);

}