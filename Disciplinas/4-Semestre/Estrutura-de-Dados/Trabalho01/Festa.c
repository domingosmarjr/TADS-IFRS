#include "Festa.h"
#include "Convidado.h"
#include <string.h>

// INICIALIZADOR DE FESTA
void iniciarFesta (Festa *festa) {
    festa->totalConvidados = 0;
    festa->totalConvites = 0;
    festa->totalTrabalhadores = 0;
    festa->nome[0] = '\0';
    festa->local[0] = '\0';
};

// CADASTRA FESTA
void cadastraFesta (Festa *festa) {
    printf("Nome da Festa: ");
    scanf("%s", festa->nome);
    printf("Local da Festa: ");
    scanf("%s", festa->local);
    printf("Dia: ");
    scanf("%d", &festa->data.dia);
    printf("Mês: ");
    scanf("%d", &festa->data.mes);
    printf("Ano: ");
    scanf("%d", &festa->data.ano);
    printf("Hora: ");
    scanf("%d", &festa->horario.hora);
    printf("Minutos: ");
    scanf("%d", &festa->horario.minuto);
};

// MOSTRA DADOS DA FESTA
void mostrarFesta(Festa *festa) {
    if (strlen(festa->nome) == 0) {
        printf("\nDados não cadastrados.\n");
        return;
    } else {
        printf("\n|=== FESTA - %s ===\n", festa->nome);
        printf("|- Local: %s\n", festa->local);
        printf("|- Data: %d/%d/%d\n", festa->data.dia, festa->data.mes, festa->data.ano);
        printf("|- Horário: %d:%d\n", festa->horario.hora, festa->horario.minuto);
        printf("|- Convidados: %d\n", festa->totalConvidados);
        printf("|- Trabalhadores: %d\n", festa->totalTrabalhadores);
        printf("|==============================\n\n");
    }
}

// EDITAR DADOS GERAIS DA FESTA
void editarFesta(Festa *festa) {
    printf("NOME: ");
    scanf("%s", festa->nome);
    printf("LOCAL: ");
    scanf("%s", festa->local);
    printf("DIA: ");
    scanf("%d", &festa->data.dia);
    printf("MÊS: ");
    scanf("%d", &festa->data.mes);
    printf("ANO: ");
    scanf("%d", &festa->data.ano);
    printf("HORAS: ");
    scanf("%d", &festa->horario.hora);
    printf("MINUTOS: ");
    scanf("%d", &festa->horario.minuto);
    mostrarFesta(festa);
}

// CADASTRA CONVIDADO NA FESTA
// - Não verifica duplicatas! (todo)
int cadastraConvidado (Festa *festa) {
    if (festa->totalConvidados >= 100) {
        printf("Limite de convidados atingido.\n");
        return 0;
    }

    Convidado *novo = (Convidado*)malloc(sizeof(Convidado));

    novo->codigo = festa->totalConvidados;
    printf("|\tNome: ");
    // scanf("%s", novo->nome);
    // - Garante que tenha sobrenome no print
    scanf(" %99[^\n]", novo->nome);
    printf("|\tTelefone: ");
    scanf("%s", novo->telefone);
    novo->confirmou = 0;

    festa->convidados[festa->totalConvidados] = *novo;
    festa->totalConvidados++;

    return 1;
};


int cadastraTrabalhador (Festa *festa) {
    if (festa->totalTrabalhadores >= 50) {
        printf("Limite de trabalhadores atingido.");
        return 0;
    }   
    Trabalhador *novo = (Trabalhador*)malloc(sizeof(Trabalhador));

    novo->codigo = festa->totalTrabalhadores;
    printf("|\tNome: ");
    scanf(" %99[^\n]", novo->nome);
    printf("|\tFunção: ");
    scanf(" %99[^\n]", novo->funcao);
    printf("|\tPagamento: ");
    scanf("%f", &novo->pagamento);

    festa->trabalhadores[festa->totalTrabalhadores] = *novo;
    festa->totalTrabalhadores++;

}


void mostrarConvidado (Convidado *con) {
    printf("|CONVIDADO:");
    printf("\n|Cod\t|Nome\t\t\t|Telefone\t|Confirmou\n");
    printf("|%d\t|%s\t|%s\t|%d\n", con->codigo, con->nome, con->telefone, con->confirmou);
}