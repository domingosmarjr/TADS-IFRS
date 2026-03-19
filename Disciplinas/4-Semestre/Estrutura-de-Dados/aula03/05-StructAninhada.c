#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct endereco {
    char nomeRua[15];
    int numero;
    int cep;
} Endereco;

typedef struct pessoa {
    int cod;
    char nome[15];
    Endereco endereco;
    // - Ligação entre estruturas
    // - Endereço é composição de Pessoa
    // - Endereço precisa ser declarado como struc antes de Pessoa
} Pessoa;

void mostrarDadosPessoa(Pessoa p) {
    printf("\nPessoa:\n\tNome: %s\n", p.nome);
    printf("\tEndereço: %s \tNúmero: %d \tCEP: %d\n\n", p.endereco.nomeRua, p.endereco.numero, p.endereco.cep);                          //PASSAGEM VALOR DIRETO
}

void lerDadosPessoa (Pessoa *p, int cod) {
    p->cod = cod;
    printf("\nNome:");
    scanf("%s", p->nome);             //pon->membro.membro
    printf("\nRua:");
    scanf("%s", p->endereco.nomeRua); //pon->membro.membro
    printf("\nNúmero Casa:");
    scanf("%d", &p->endereco.numero); //&pon->membro.membro
    printf("\nCEP:");
    scanf("%d", &p->endereco.cep);    //&pon->membro.membro

}

int main () {
    Pessoa joao;
    Pessoa maria;

    Endereco minhaCasa;

    joao.cod = 1;
    strcpy(joao.nome, "João");

    maria.cod = 2;
    strcpy(maria.nome, "Maria");

    strcpy(minhaCasa.nomeRua, "Marcilio Dias");
    minhaCasa.cep = 96345098;
    minhaCasa.numero = 32;

    // ASSOCIAÇÃO PESSOA-ENDEREÇO
    joao.endereco = minhaCasa;
    maria.endereco = minhaCasa;

    printf("\nRua de %s: %s", joao.nome, joao.endereco.nomeRua);
    printf("\nRua de %s: %s\n", maria.nome, maria.endereco.nomeRua);

    mostrarDadosPessoa(joao);

    Pessoa *p;
    p = (Pessoa*)malloc(sizeof(Pessoa)); 
    lerDadosPessoa(p, 3);

    mostrarDadosPessoa(*p);
}


