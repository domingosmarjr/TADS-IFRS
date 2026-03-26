#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Festa.h"

int main()
{
    int opcao;
    Festa *festa = (Festa*)malloc(sizeof(Festa));
    iniciarFesta(festa);

    // FESTA TESTE - Descomente para não ter que inserir dados para testagem.
    // 
    // strcpy(festa->nome, "Aniversário de Pedro");
    // strcpy(festa->local, "Casa do Mario");
    // festa->data.dia = 13;
    // festa->data.mes = 3;
    // festa->data.ano = 2026;
    // festa->horario.hora = 18;
    // festa->horario.minuto =  30;

    // Cadastra uma festa nova, caso não tenha alguma cadastrada.
    if(strlen(festa->nome) <= 0) cadastraFesta(festa);

    do
    {
        printf("\n|=== BEM-VINDO AO SISTEMA DA SUA FESTA ===\n");
        printf("| Digite a opção para navegar na sua festa:\n");
        printf("|\t1. Cadastrar Convidado\n");
        printf("|\t2. Cadastrar Trabalhador\n");
        printf("|\t3. Criar Convite\n");
        printf("|\t4. Confirmar Presença\n");
        printf("|\t5. Cancelar Presença\n");
        printf("|\t6. Listar Convidados\n");
        printf("|\t7. Listar Trabalhadores\n");
        printf("|\t8. Listar Confirmados\n");
        printf("|\t9. Mostrar festa\n");
        printf("|\t10. Mostrar Total Pagamento\n");
        printf("|\t11. Criar Convite de Convidado\n");
        printf("|\t12. Atualizar texto dos convites.\n");
        printf("|\t13. Mostrar Convite por código.\n");
        printf("|\t14. Mostrar Convidado por Código.\n");
        printf("|\t15. Mostrar Trabalhador por Código.\n");                
        printf("|\t0. Sair.\n|\n");
        printf("|Opção: ");
        scanf("%d", &opcao);

        switch (opcao)
        {
        case 1:
            cadastraConvidado(festa);
            break;
        
        case 2:
            cadastraTrabalhador(festa);
            break;

        case 3:
            criarConvite(festa);
            break;

        case 4:
            confirmarPresenca(festa);
            break;
        case 5:
            cancelaPresenca(festa);
            break;

        case 6:
            listarConvidados(festa);
            break;

        case 7:
            listarTrabalhadores(festa);
            break;
        
        case 8:
            listarConfirmados(*festa);
            break;

        case 9:
            mostraFesta(festa);
            break;        
        
        case 10:
            mostrarTotalPagamentos(festa);
            break;
            
        case 11:
            criarConvite(festa);
            break;
        
        case 12:
            atualizarTextoConvites(festa);
            break;

        case 13:
            mostraConvitePorCodigo(festa);
            break;
        
        case 14:
            getConvidado(festa);
            break;
        
        case 15:
            getTrabalhador(festa);
            break;
        
        default:
            break;
        }

    } while (opcao != 0);

    free(festa);
    exit(0);
}

/*
    DATA
    HORARIO
    CONVIDADO
    - int cadastraConvidado(Festa *festa)
        cadastra novo convidado na festa
        insere os dados do convidado terminal
    TRABALHADOR
    CONVITE
    FESTA
*/