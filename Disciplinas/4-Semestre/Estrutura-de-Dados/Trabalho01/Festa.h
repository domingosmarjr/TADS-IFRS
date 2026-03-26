#ifndef FESTA_H
#define FESTA_H
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include "Data.h"
#include "Horario.h"
#include "Convidado.h"
#include "Trabalhador.h"
#include "Convite.h"

// STRUCT
typedef struct festa {
    char nome[100];
    char local[100];
    Data data;
    Horario horario;
    
    Convidado convidados[100];
    int totalConvidados;

    Trabalhador trabalhadores[50];
    int totalTrabalhadores;
    
    Convite *convites;
    int totalConvites;
} Festa;



// ====== FESTA ======
// Inicializador da Festa
void iniciarFesta (Festa *festa);
// Cadastro inicial da festa
void cadastraFesta (Festa *festa);
// Mostra dados da festa
void mostraFesta(Festa *festa);
// Retorna valor total de pagamentos
float calcularTotalPagamentos (Festa *festa);
// Mostra dado de pagamento total
void mostrarTotalPagamentos (Festa *festa);



// ====== CONVIDADO ======
// Cadastrar convidados
int cadastraConvidado(Festa *festa);
int confirmarPresenca (Festa *festa);
int cancelaPresenca (Festa *festa);
void mostrarConvidado (Convidado con);
void listarConvidados (Festa *festa);
int calcularConfirmados (Festa *festa);
void listarConfirmados (Festa festa);
int buscarConvidado(Festa *festa, int codigo);
void getConvidado (Festa *festa);



// ====== CONVITE ======
int criarConvite (Festa *festa);
void adicionarDataConvite (Convite *convite, Festa *festa);
void atualizarTextoConvites(Festa *festa);
void mostraConvite (Convite conv);
void mostraConvitePorCodigo (Festa *festa);



// ====== TRABALHADOR ======
int cadastraTrabalhador (Festa *festa);
void mostraTrabalhador (Trabalhador trabalhador);
void listarTrabalhadores (Festa *festa);
int buscarTrabalhador (Festa *festa, int codigo);
void getTrabalhador (Festa *festa);



// ====== MEMÓRIA ======
void liberarMemoria(Festa *festa);



#endif