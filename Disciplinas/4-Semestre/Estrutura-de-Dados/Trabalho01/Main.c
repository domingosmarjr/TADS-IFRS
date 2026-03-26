#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Festa.h"

int main()
{
    int opcao;
    Festa *festa = (Festa*)malloc(sizeof(Festa));

    // INSERE OS DADOS INICIAS DA FESTA NO TERMINAL
    iniciarFesta(festa);

    // FESTA TESTE
    // - Comente para iniciar uma festa do zero, sem convidados.
    insereDadosTeste(festa);

    // NOVA FESTA
    // - Comente para quando for usar o "insdereDadosTeste()"
    // if(strlen(festa->nome) <= 0) cadastraFesta(festa);

    do
    {
        printf("\n|=== BEM-VINDO AO SISTEMA DA SUA FESTA ===\n");
        printf("| Digite a opção para navegar na sua festa:\n");
        printf("|\n|CONVIDADO:\n");
        printf("|\t1. Cadastrar Convidado\n");
        printf("|\t2. Confirmar Presença\n");
        printf("|\t3. Cancelar Presença\n");
        printf("|CONVITE:\n");
        printf("|\t4. Criar Convite\n");
        printf("|\t5. Atualizar Texto do Convite\n");
        printf("|TRABALHADOR:\n");
        printf("|\t6. Cadastrar Trabalhador\n");
        printf("|LISTAR:\n");
        printf("|\t7. Listar Convidados\n");
        printf("|\t8. Listar Trabalhadores\n");
        printf("|\t9. Listar Confirmados\n");
        printf("|MOSTRAR:\n");
        printf("|\t10. Mostrar Convite por Código\n");
        printf("|\t11. Mostrar Convidado por Código\n");
        printf("|\t12. Mostrar Trabalhador por Código.\n");
        printf("|FESTA:\n");
        printf("|\t13. Mostrar Dados da Festa.\n");
        printf("|\t14. Mostrar Valor Total da Festa.\n");
        printf("|\t15. Excluir todos os convites.\n");
        printf("|\n|\t0. Sair.\n|\n");
        printf("|Opção: ");
        scanf("%d", &opcao);

        switch (opcao) {
            case 1:
                cadastraConvidado(festa);
                break;
            
            case 2:
                confirmarPresenca(festa);
                break;

            case 3:
                cancelaPresenca(festa);
                break;

            case 4:
                criarConvite(festa);
                break;
                
            case 5:
                atualizarTextoConvites(festa);        
                break;

            case 6:
                cadastraTrabalhador(festa);
                break;

            case 7:
                listarConvidados(festa);
                break;
            
            case 8:
                listarTrabalhadores(festa);
                break;

            case 9:
                listarConfirmados(*festa);
                break;        
            
            case 10:
                mostraConvitePorCodigo(festa);
                break;
                
            case 11:
                getConvidado(festa);
                break;
            
            case 12:
                getTrabalhador(festa);
                break;

            case 13:
                mostraFesta(festa);
                break;
            
            case 14:
                mostrarTotalPagamentos(festa);
                break;

            case 15:
                liberarMemoriaConvites(festa);
                break;
            
            default:
                break;
        }

    } while (opcao != 0);

    liberarMemoria(festa);

    exit(0);
}
