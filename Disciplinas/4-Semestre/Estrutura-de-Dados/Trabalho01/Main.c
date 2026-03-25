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
    strcpy(festa->nome, "Aniversário de Pedro");
    strcpy(festa->local, "Casa do Mario");
    festa->data.dia = 13;
    festa->data.mes = 3;
    festa->data.ano = 2026;
    festa->horario.hora = 18;
    festa->horario.minuto =  30;

    // Cadastra uma festa nova, caso não tenha alguma cadastrada.
    // if(strlen(festa->nome) == 0) cadastraFesta(festa);

    do
    {
        printf("\n|=== BEM-VINDO AO SISTEMA DA SUA FESTA ===\n");
        printf("| Digite a opção para navegar na sua festa:\n");
        printf("|\t1. Cadastrar Convidado\n");
        printf("|\t2. Cadastrar Trabalhador\n");
        printf("|\t3. Criar Convite\n");
        printf("|\t4. Confirmar Presença\n");
        printf("|\t5. Listar Convidados\n");
        printf("|\t6. Listar Trabalhadores\n");
        printf("|\t7. Listar Confirmados\n");
        printf("|\t8. Mostrar Festa\n");
        printf("|\t9. Calcular Total de Pagamentos\n|\n");
        printf("|\t0. Sair do sistema\n|\n");
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
            listarConvidados(festa);
            break;

        case 6:
            listarTrabalhadores(festa);
            break;
        
        case 7:
            listarConfirmados(*festa);
            break;

        case 8:
            mostraFesta(festa);
            break;        
        
        case 9:
            mostrarTotalPagamentos(festa);
            break;        
        
        default:
            break;
        }

    } while (opcao != 0);


    // BATERIA DE TESTES
    printf("\n%s", festa->convidados[0].nome);
    printf("\nCod:%d", festa->convidados[0].codigo);
    printf("\nTel:%s", festa->convidados[0].telefone);
    printf("\nConfirmou: %d", festa->convidados[0].confirmou);

    printf("\n%s", festa->convidados[1].nome);
    printf("\nCod:%d", festa->convidados[1].codigo);
    printf("\nTel:%s", festa->convidados[1].telefone);
    printf("\nConfirmou: %d", festa->convidados[1].confirmou);

    printf("\nTConvidados: %d\n\n", festa->totalConvidados);    

    confirmarPresenca(festa);
    mostrarConvidado(festa->convidados[0]);
    cancelaPresenca(festa);
    // printf("\n|Cod\t|Nome\t\t\t\t|Telefone\t|Confirmou\n");
    printf("\n|Cod|\n");
    mostrarConvidado(festa->convidados[0]);
    mostrarConvidado(festa->convidados[1]);
    mostrarConvidado(festa->convidados[2]);

    
    atualizarTextoConvites(festa);

    mostraConvite(festa->convites[0]);

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