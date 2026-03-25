
// Arquivo de prototipos das funções e definicao das estruturas

// Estrutura para armazenar alunos
typedef struct aluno
{
    char nome[100];
    int idade;
    int matricula;
    struct aluno *proximo;
} Aluno;

// Estrutura para gerenciar a lista
typedef struct lse
{
    Aluno *primeiro;
    int n_elementos;
    char nome[50];
} LSE;

void criaLista(LSE **lista, char nome[]);

void inicializaLista(LSE *lista, char nome[]);

void insereInicio(LSE *lista, Aluno *novo);

void insereInicioReduzida(LSE *lista, Aluno *novo);

void insereFim(LSE *lista, Aluno *novo);

void inserePosicao(LSE *lista, Aluno *novo, int posicao);
// inserir um elemento em qualquer posição da lista (posicao)

Aluno *removeInicio(LSE *lista);

Aluno *removeFim(LSE *lista);

Aluno *removePosicao(LSE *lista, int posicao);

void cadastraAluno(Aluno *aluno, char nome[], int idade, int matricula);

Aluno *informaNovoAluno();

void mostraAluno(Aluno novo);

void mostraLista(LSE lista);

void mostraLista2(Aluno *aluno);

void menuTesteLista(LSE *lista);