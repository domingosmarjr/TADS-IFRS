#include <stdio.h>
#include <stdlib.h>

// 1) Crie uma Struct para armazenar dados de um Carro:
typedef struct carro {
    char nome[20];
    char marca[20];
    int ano;
} Carro;

// 2) Crie uma Struct para armazenar dados de um filme;
typedef struct filme {
    char nome[20];
    int ano;
} Filme;

int main() {
// 3) Defina duas variáveis para armazenar os dados de dois carros e dois filmes.
    Carro c1 = {"Fusca 1600", "Volkswagen", 1978};
    Carro c2 = {"Chevette", "Chevrolet", 1995};
    Filme f1 = {"Karate Kid", 1999};
    Filme f2 = {"IT - A Coisa", 2014};

// 4) Mostre os dados armazenados dos carros e filmes;
    printf("\nNome do Carro: %s\n", c1.nome);
    printf("Marca do Carro: %s\n", c1.marca);
    printf("Ano do Carro: %d\n", c1.ano);
    printf("Nome do Carro: %s\n", c2.nome);
    printf("Marca do Carro: %s\n", c2.marca);
    printf("Ano do Carro: %d\n", c2.ano);

    printf("\nFilme: %s\n", f1.nome);
    printf("Ano: %d\n", f1.ano);
    printf("Filme: %s\n", f2.nome);
    printf("Ano: %d\n\n", f2.ano);

// 5) Troque o ano de fabricação dos carros;
    c1.ano = 1980;
    c2.ano = 2000;

// 6) Troque o ano de lançamento dos filmes;
    f1.ano = 2001;
    f2.ano = 1960;

// 7) Mostre os dados atualizados.
    printf("\nNome do Carro: %s\n", c1.nome);
    printf("Marca do Carro: %s\n", c1.marca);
    printf("Ano do Carro: %d\n", c1.ano);
    printf("Nome do Carro: %s\n", c2.nome);
    printf("Marca do Carro: %s\n", c2.marca);
    printf("Ano do Carro: %d\n", c2.ano);

    printf("\nFilme: %s\n", f1.nome);
    printf("Ano: %d\n", f1.ano);
    printf("Filme: %s\n", f2.nome);
    printf("Ano: %d\n\n", f2.ano);
}
