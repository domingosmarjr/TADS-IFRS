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

// INSERE DADOS TESTE NO SISTEMA
void insereDadosTeste (Festa *festa) {
    // == PRESET FESTA
    strcpy(festa->nome, "Aniversário de Pedro");
    strcpy(festa->local, "Casa do Mario");
    festa->data.dia = 13;
    festa->data.mes = 3;
    festa->data.ano = 2026;
    festa->horario.hora = 18;
    festa->horario.minuto =  30;

    // == PRESET CONVIDADOS
    festa->convidados[0].codigo = 0;
    strcpy(festa->convidados[0].nome, "Leticia Silva");
    strcpy(festa->convidados[0].telefone, "5332330001");
    festa->totalConvidados++;

    festa->convidados[1].codigo = 1;
    strcpy(festa->convidados[1].nome, "Matheus Braga");
    strcpy(festa->convidados[1].telefone, "5332330001");
    festa->totalConvidados++;

    festa->convidados[2].codigo = 2;
    strcpy(festa->convidados[2].nome, "José Amorim");
    strcpy(festa->convidados[2].telefone, "5332330001");
    festa->totalConvidados++;

    festa->convidados[3].codigo = 3;
    strcpy(festa->convidados[3].nome, "Cecília Gomes");
    strcpy(festa->convidados[3].telefone, "5332330001");
    festa->totalConvidados++;

    festa->convidados[4].codigo = 4;
    strcpy(festa->convidados[4].nome, "Thomas Mathias");
    strcpy(festa->convidados[4].telefone, "5332330001");
    festa->totalConvidados++;

    festa->convidados[5].codigo = 5;
    strcpy(festa->convidados[5].nome, "Eduarda Ritt");
    strcpy(festa->convidados[5].telefone, "5332330001");
    festa->totalConvidados++;

    // == PRESET TRABALHADORES
    festa->trabalhadores[0].codigo = 0;
    strcpy(festa->trabalhadores[0].nome,"Patrick Garcia");
    strcpy(festa->trabalhadores[0].funcao, "DJ");
    festa->trabalhadores[0].pagamento = 790.20;
    festa->totalTrabalhadores++;

    festa->trabalhadores[1].codigo = 1;
    strcpy(festa->trabalhadores[1].nome, "Pedro Lopes");
    strcpy(festa->trabalhadores[1].funcao, "Segurança");
    festa->trabalhadores[1].pagamento = 300;
    festa->totalTrabalhadores++;

    festa->trabalhadores[2].codigo = 2;
    strcpy(festa->trabalhadores[2].nome, "Felipe Treicha");
    strcpy(festa->trabalhadores[2].funcao, "Cozinheiro");
    festa->trabalhadores[2].pagamento = 500;
    festa->totalTrabalhadores++;

    festa->trabalhadores[3].codigo = 3;
    strcpy(festa->trabalhadores[3].nome, "Larissa Peres");
    strcpy(festa->trabalhadores[3].funcao, "Operador de Som");
    festa->trabalhadores[3].pagamento = 400;
    festa->totalTrabalhadores++;

    festa->trabalhadores[4].codigo = 4;
    strcpy(festa->trabalhadores[4].nome, "Atila Lemes");
    strcpy(festa->trabalhadores[4].funcao, "Logística");
    festa->trabalhadores[4].pagamento = 300.50;
    festa->totalTrabalhadores++;
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

    Convidado *novo = &festa->convidados[festa->totalConvidados];

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
            festa->convites[festa->totalConvites] = *convite;

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
        printf("|\t%s\n", conv.mensagem);
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
    Trabalhador *novo = &festa->trabalhadores[festa->totalTrabalhadores];

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



//================== MEMÓRIA ==================
void liberarMemoriaConvites(Festa *festa) {
    if (festa->convites != NULL) {
            memset(festa->convites, 0, sizeof(Convite) * festa->totalConvites);
            free(festa->convites);
            festa->convites = NULL;
        }
    festa->totalConvites = 0;
};

void liberarMemoria(Festa *festa) {

    // OBSERVAÇÃO IMPORTANTE SOBRE A FUNÇÃO!
    // 
    // Tive que usar o memset() pois Convidados[] e Trabalhadores[] estão com memória alocada estaticamente. Sei que isso não "apaga" a memória, então acabo "resetando" ela toda para 0.
    // Já com convites, está alocado dinamicamente, logo consigo usar o free() sem problema algum.
    // 
    // Segui o enunciado do trabalho, para ser condizente com o mesmo. Entretanto, particularmente eu alteraria a estrutura de Trabalhadores e Convidados para uma alocação dinâmica. Assim facilitaria o processo de liberar memória, e defintivamente liberaria esse espaço para o sistema.

    // Liberar trabalhadores
    memset(festa->trabalhadores, 0, sizeof(festa->trabalhadores));
    festa->totalTrabalhadores = 0;

    // Liberar convidados
    memset(festa->convidados, 0, sizeof(festa->convidados));
    festa->totalConvidados = 0;

    // Liberar dos convites convites
    liberarMemoriaConvites(festa);    

    // Libera memória da festa
    free (festa);

};




