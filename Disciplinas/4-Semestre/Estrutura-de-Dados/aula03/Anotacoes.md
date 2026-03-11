PONTEIRO DE PONTEIRO

x = 5;
*px = &x;
**px = &px; -> ponteiro de ponteiro (notação **px)


x = x + 5;
*px = *px + 5;
**px = **px + 5

===============================
STRUCT
> Armazenar mais informações
> Ex: nome, idade, telefone

Struct = um grupo ou conjunto de itens
- é uma estrutura de dados
- struct considerado um novo tipo de dados
- struct é uma versão arcaica do objeto
- struct não tem métodos (ele é a vó das classes)
- ARMAZENA TIPOS E NOMES, MAS NÃO TEM NOMES

struc nome_struct{
    //tipo nome_item ou membro
    //tipo nome_item ou membro
}

//Estrutura PEssoa
struct pessoa{
    int cod;
    char nome[15];
    int idade;
    float salario;
};

//
joao.cod = 1;
joao.idade = 29;

//ATRIBUI VALORES PARA STRING [string copy]
strcpy(joao.nome, "Joao Carlos");

//TYPEDEF -> RENOMEAR ESTRUTURAS
typedef struct nome_struct nome_tipo(apelido)
struct pessoa {...};
typedef struct pessoa Pessoa;

    struct pessoa maria = {...}
    struct pessoa joa = {...}

    typedef struct pessoa Pessoa;

    Pessoa joao = {...};

    //NO ESCOPO DE STRUCT (FORA DO MAIN)
    typedef struct pessoa {
        ...
    }Pessoa;

