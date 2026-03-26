#include "Festa.h"
#include "Convidado.h"
#include "Convite.h"
#include <string.h>

//================== FESTA ==================

// INICIALIZADOR DE FESTA [REVISADO]
void iniciarFesta (Festa *festa) {
    festa->totalConvidados = 0;
    festa->totalConvites = 0;
    festa->totalTrabalhadores = 0;
    festa->nome[0] = '\0';
    festa->local[0] = '\0';
    festa->convites = (Convite*)malloc(sizeof(Convite) * 50);
};

// CADASTRAR FESTA NOVA [REVISADO] 
void cadastraFesta (Festa *festa) {
    printf("|===== INSIRA OS DADOS BASE DA FESTA =======\n");
    printf("|Nome da Festa: ");
    scanf("%s", festa->nome);
    
    printf("|Local da Festa: ");
    scanf("%s", festa->local);
    
    printf("|Dia: ");
    scanf("%d", &festa->data.dia);
    
    printf("|Mês: ");
    scanf("%d", &festa->data.mes);
    
    printf("|Ano: ");
    scanf("%d", &festa->data.ano);
    
    printf("|Hora: ");
    scanf("%d", &festa->horario.hora);
    
    printf("|Minutos: ");
    scanf("%d", &festa->horario.minuto);
};

// MOSTRA DADOS DA FESTA [REVISADO]
void mostraFesta(Festa *festa) {
    if (strlen(festa->nome) == 0) {
        printf("\nDados não cadastrados.\n");
        return;
    } else {
        printf("\n|===== %s\n", festa->nome);
        printf("|- Local: %s\n", festa->local);
        printf("|- Data: %d/%d/%d\n", festa->data.dia, festa->data.mes, festa->data.ano);
        printf("|- Horário: %d:%d\n", festa->horario.hora, festa->horario.minuto);
        printf("|- Convidados: %d\n", festa->totalConvidados);
        printf("|- Trabalhadores: %d\n", festa->totalTrabalhadores);
        printf("|- Convites: %d\n", festa->totalConvites);
        printf("|- Total da Festa: %.2f", calcularTotalPagamentos(festa));
        listarConvidados(festa);
        listarTrabalhadores(festa);
    }
}

// RETORNA VALOR TOTAL DE PAGAMENTOS [REVISADO]
float calcularTotalPagamentos (Festa *festa) {
    float valor = 0.0;

    for (int i = 0; i < festa->totalTrabalhadores; i++) {
        valor += festa->trabalhadores[i].pagamento;
    }
    return valor;
}

// MOSTRAR TOTAL DE PAGAMENTOS [REVISADO]
void mostrarTotalPagamentos (Festa *festa) {
    printf("\n>Total Pagamentos: %.2f\n", calcularTotalPagamentos(festa));
}

//================== CONVIDADO ==================

// CADASTRA CONVIDADO NA FESTA [REVISADO]
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

    printf("\n> Convidado cadastrado.\n");

    return 1;
};

// CONFIRMAR PRESENÇA DE CONVIDADO [REVISADO]
int confirmarPresenca (Festa *festa) {
    int cod;
    printf(">Código do convidado: ");
    scanf("%d", &cod);

    for (int i = 0; i < festa->totalConvidados; i++) {
        if (festa->convidados[i].codigo == cod) {
            festa->convidados[i].confirmou = 1;
            printf("\nPresença confirmada!\n");
            return 1;
        }
    }
    printf("Convidado não encontrado.");
    return 0;
}

// CANCELA PRESENÇA DE CONVIDADO
int cancelaPresenca (Festa *festa) {
    int cod;
    printf(">Código do convidado: ");
    scanf("%d", &cod);

    for (int i = 0; i < festa->totalConvidados; i++) {
        if (festa->convidados[i].codigo == cod) {
            festa->convidados[i].confirmou = 0;
            printf("\nPresença cancelada!\n");
            return 1;
        }
    }
    printf("Convidado não encontrado.");
    return 0;
}

// MOSTRAR O CONVIDADO [REVISADO]
void mostrarConvidado (Convidado con) {
    printf("| %d |Nome: %s\n|   |\tTelefone: %s\n|   |\tConfirmado: %d\n", con.codigo, con.nome, con.telefone, con.confirmou);
}

// LISTAR CONVIDADOS [REVISADO]
void listarConvidados (Festa *festa) {
    printf("\n|====================\n| CONVIDADOS: \n");
    if (festa->totalConvidados <= 0) {
        printf("| Sem convidados cadastrados.\n");
    } else {
        for (int i = 0; i < festa->totalConvidados; i++) {
            mostrarConvidado(festa->convidados[i]);
        }
    }
    printf("|====================\n");
}

// RETORNA QUANTIDADE DE CONFIRMADOS [REVISADO]
int calcularConfirmados (Festa *festa) {
    int confirmados = 0;
    for (int i = 0; i < festa->totalConvidados; i++) {
        if (festa->convidados[i].confirmou == 1) confirmados++;
    }
    return confirmados;
}

// LISTA OS CONFIRMADOS NA FESTA [REVISADO]
void listarConfirmados (Festa festa) {
    printf("\n|====================\n| CONVIDADOS CONFIRMADOS: \n");
    if (calcularConfirmados(&festa) <= 0) {
        printf("| Sem convidados confirmados.\n");
    } else {
        for (int i = 0; i < festa.totalConvidados; i++) {
            if (festa.convidados[i].confirmou == 1) {
                mostrarConvidado(festa.convidados[i]);
            }
        }
    }
    printf("|====================\n");
};

// PEGA O COD DO CONVIDADO NA FESTA
int buscarConvidado (Festa *festa, int codigo) {

    for (int i = 0; i < festa->totalConvidados; i++) {
        if (festa->convidados[i].codigo == codigo) {
            return i;
        }
    }
    return -1;
}

// PRINTA DADOS DO CONVIDADO NA TELA
void getConvidado (Festa *festa) {
    int cod;
    printf("| Insira o código do convidado: ");
    scanf("%d", &cod);
    mostrarConvidado(festa->convidados[cod]);
}

//================== CONVITE ==================
// CRIA CONVITE
int criarConvite (Festa *festa) {

    int cod;
    printf("Insira o código do usuário:");
    scanf("%d", &cod);

    for (int i = 0; i < festa->totalConvidados; i++) {
        
        if (festa->convidados[i].codigo == cod) {
            Convite *convite = &festa->convites[festa->totalConvites];
            
            convite->convidado = &festa->convidados[i];
            convite->codigo = festa->totalConvites;
            strcpy(convite->mensagem,"");
            
            festa->totalConvites++;

            printf("Convite criado para o convidado.\n");

            return 1;
        }
    }
    printf("Convidado não encontrado ou já possui convite.\n");
    return 0;
};

// ADICIONA DATA DA FESTA NO CONVITE
void adicionarDataConvite (Convite *convite, Festa *festa) {
    char info[100];
    snprintf(info, sizeof(info), "\n|\tData: %02d/%02d/%0d \n|\tHorário: %02d:%02d", festa->data.dia, festa->data.mes, festa->data.ano, festa->horario.hora, festa->horario.minuto);

    strncat (convite->mensagem, info, sizeof(convite->mensagem) - strlen(convite->mensagem) -1);
}

// ATUALIZA TEXTO MENSAGEM DE TODOS CONVITES
void atualizarTextoConvites(Festa *festa) {
    char msg[200];
    printf("Insira novo texto do convite:");
    scanf(" %[^\n]", msg);

    for (int i = 0; i < festa->totalConvites; i++) {
        strcpy(festa->convites[i].mensagem, msg);
        adicionarDataConvite(&festa->convites[i], festa);
    }

    printf("Mensagens atualizadas com sucesso.\n");
}

// IMPRIME DETERMINADO CONVITE
void mostraConvite (Convite conv) {
    printf("| CONVITE\n");

    if(conv.convidado != NULL) {
        printf("|\tOlá %s!\n", conv.convidado->nome);
        printf("\t%s\n", conv.mensagem);
    } else {
        printf("|\tConvidado não encontrado.");
    }
}

// MOSTRA O CONVITE A PARTIR DE CÓDIGO
void mostraConvitePorCodigo (Festa *festa) {
    int cod;
    printf("Insira o código de convidado: ");
    scanf("%d", &cod);

    int getCod = (buscarConvidado(festa, cod));

    if (getCod == -1) {
        printf("\n| Convidado não encontrado.\n");
        return;
    }

    for (int i = 0; i < festa->totalConvites; i++) {
        if (festa->convites[i].convidado == &festa->convidados[getCod]) {
            mostraConvite(festa->convites[i]);
            return;
        }
    }
    printf("\n| Convite não encontrado.\n");
}



//================== TRABALHADOR ==================

// CADASTRA NOVO TRABALHADOR [REVISADO]
// - Não verifica duplicatas
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

// MOSTRAR O TRABALHADOR [REVISADO]
void mostraTrabalhador (Trabalhador trabalhador) { 
    printf("| %d |Nome: %s\n|   |\tFunção: %s\n|   |\tValor: %.2f\n", trabalhador.codigo, trabalhador.nome, trabalhador.funcao, trabalhador.pagamento);
};

// LISTAR OS TRABALHADORES [REVISADO]
void listarTrabalhadores (Festa *festa) {
    printf("|====================\n| TRABALHADORES: \n");
    if (festa->totalTrabalhadores <= 0) {
        printf("| Sem trabalhadores cadastrados.\n");
    } else {
        for (int i = 0; i < festa->totalTrabalhadores; i++) {
            mostraTrabalhador(festa->trabalhadores[i]);
        }
    }
    printf("|====================\n");

};

// PEGA O COD DO TRABALHADOR NA FESTA
int buscarTrabalhador (Festa *festa, int codigo) {
    for (int i = 0; i < festa->totalTrabalhadores; i++) {
        if (festa->trabalhadores[i].codigo == codigo) {
            return i;
        }
    }
    return -1;
}

// PRINTA DADOS DO TRABALHADOR NA TELA
void getTrabalhador (Festa *festa) {
    int cod;
    printf("| Insira o código do trabalhador.");
    scanf("%d", &cod);
    mostraTrabalhador(festa->trabalhadores[cod]);
}


// void liberarMemoria(Festa *festa) {}
//  - liberar memória para os convites