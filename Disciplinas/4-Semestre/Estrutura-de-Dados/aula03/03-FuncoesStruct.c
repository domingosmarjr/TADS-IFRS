#include <stdio.h>
#include <stdlib.h>

typedef struct pessoa {
    int cod;
    char nome[15];
    char sobrenome[15];
    int idade;
    char telefone[10];
} Pessoa;


// PASSAGEM POR VALOR
// - Uma cópia de Pessoa é armazenada em p
void mostrarDadosPessoa(Pessoa p) {
    printf("\nPessoa:\n\tNome: %s %s\n", p.nome, p.sobrenome);
    printf("\tCodigo: %d\tIdade: %d\n", p.cod, p.idade);
    printf("\tTelefone: %s\n\n", p.telefone);
}

// PASSAGEM POR REFERÊNCIA
// - Acessa diretamente p, sem criar cópia.
// - O endereço de p é enviado para a função.
void lerDadosPessoa (Pessoa *p, int cod) {
    p->cod = cod;
    printf("Nome:");
    scanf("%s", p->nome);
    printf("Sobrenome:");
    scanf("%s", p->sobrenome);
    printf("Idade:");
    scanf("%d", &p->idade);
    printf("Telefone:");
    scanf("%s", p->telefone);
}

int main() {

    int codigo = 1;
    // Alocação estática de pessoa
    Pessoa maria;
    Pessoa joao;

    // IMPORTANTE
    // - Como leitura recebe um ponteiro, se coloca o endereço da variável
    // - Incrementa-se o código após inserção.
    lerDadosPessoa(&maria, codigo++);
    // Chama de função com struct
    mostrarDadosPessoa(maria);

    lerDadosPessoa(&joao, codigo++);
    mostrarDadosPessoa(joao);

}