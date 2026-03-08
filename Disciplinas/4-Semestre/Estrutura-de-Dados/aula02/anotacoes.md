=======================
MEMÓRIA RAM

- Conjunto de células de 1 byte.
- Composta de endereço e valor

- Célula de Memória (Endereço + Valor)
| Endereço | Valor (1byte = 8bits)
| e94ab    | 01011001

> Endereço sempre em hexadecimal
> Valor sempre em bits, respeitando 8bits(1byte) por célula

1GB => 1bilhão de bytes

Endereço de Memória -> usado pelo SO para alocar memória

===========================
TIPOS DE DADOS NA MEMÓRIA

>TIPO               >TAMANHO                >VALOR MÍN                 >VALOR MAX
char                    8                         -128                        127
short int              16                      -32.768                     32.767
int                    32               -2.147.483.648              2.147.483.647 
long int               32               -2.147.483.648              2.147.483.647
long long int          64   -9.223.372.036.854.775.808  9.223.372.036.854.775.807
unsigned char           8                            0                        255
unsigned short int     16                            0                      65535
unsigned int           32                            0              4.294.967.295
unsigned long int      32                            0              4.294.967.295
unsigned long long int 64                            0 18.446.744.073.709.551.615

===========================
PONTEIROS

- Variáveis capazes de armazenar APENAS endereços de memória (hexadecimal)
- Possível acessar o conteúdo da posição de forma INDIRETAMENTE ou REFERENCIADA
- Pode-se também armazenar o endereço de outra posição de memória

Ponteiro *px -> usa-se * para declarar ponteiro
    int x, *px;
    px = &x;
    *px = x; //equivalente

