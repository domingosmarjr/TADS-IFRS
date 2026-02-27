// See https://aka.ms/new-console-template for more information

//012345678901234
using SixLabors.ImageSharp;
using SixLabors.ImageSharp.PixelFormats;

string nome = "Ricardo Vergara";
// string é posicional, ordenada, é possível obter
// um caractere de uma certa posição
// é imutável, mas compartilha comportamento de array
// é um array especializado (array imutável de caracteres)

// alguns são objetos e outros são primitivos
// criamos um array de 7 posições
char[] palavra = new char[7];
Console.WriteLine(palavra[0]);
Console.WriteLine((int)palavra[0]);
palavra[0] = 'm'; // mutável
palavra[1] = 'o'; // mas o tamanho é imutável
palavra[2] = 'd';
palavra[3] = 'u';
palavra[4] = 'l';
palavra[5] = 'a';
palavra[6] = 'r';
// palavra[7] = 'e';
Console.WriteLine(palavra[3]); // u
Console.WriteLine(palavra); // u

// iterar um array (percorrer um array)
for (var i = 0; i < palavra.Length; i++)
{
    Console.Write(palavra[i] + ", ");
}

Console.WriteLine('\n');

foreach (var letra in palavra)
{
    Console.Write(letra + ", ");
}

Console.WriteLine('\n');
// array é mutável
// array é ordenado (indexado)
// array tem comprimento (length) fixo
// array é contíguo na memória
// array é uma estrutura de dados homogênea
byte[] idades = new byte[9]; // array vazio (tudo zero)
idades[0] = 48; // cálculo do índice na mem: 0 x 8
idades[1] = 49; // 1 x 8
idades[2] = 45; // 2 x 8
idades[3] = 49; // 3 x 8
idades[4] = 21; // ...
idades[5] = 34;
idades[6] = 28;
idades[7] = 31;
idades[8] = 72;
                   //  0     1     2     3     4
int[] laboratorios = { 1001, 1002, 1003, 1004, 1005 };
Console.WriteLine(laboratorios[2]); // 1003
Console.WriteLine(laboratorios.Length); // 5

// array literal => posições e valores
string[] nomes = { "Ricardo", "Roberto", "Janaiton" };
Console.WriteLine(nomes);
Console.WriteLine(nomes.Length);
Console.WriteLine(nomes[1]);

/*
jagged array
[[3, 4, 5],
 [4, 8, 1, 5],
 [5, 6]]
matriz (i linhas e j colunas)
[ 4, 5, 6,
  6, 2, 5,
  5, 3, 1 ]
*/

// array multidimensional
// - jagged array
// - matriz
// 3 linhas
int[][] jagged = new int[3][];
jagged[0] = new int[3]; 
jagged[1] = new int[5];
jagged[2] = new int[2];
/*
     0  1  2  3  4
0 [ [0, 0, 0],
1   [0, 0, 23, 0, 0],
2   [0, 0]]
*/
jagged[1][2] = 23;

Console.WriteLine('\n');

// i = linha
for (var i = 0; i < jagged.Length; i++)
{
    // j = coluna
    for (var j = 0; j < jagged[i].Length; j++)
    {
        Console.Write(jagged[i][j] + " - ");
    }
    Console.WriteLine();
}


// jagged array, array multimensional de colunas diferentes
int[][] jagged2 = [[2, 3, 4],
                   [4, 5, 6, 8],
                   [9, 3, 4, 1, 9]];

Console.WriteLine();
foreach (var linha in jagged2)
{
    foreach (var valor in linha)
    {
        Console.Write(valor + " / ");
    }
    Console.WriteLine();
}


// matriz:
int[][] jagged3x3 = [[0, 0, 0],
                     [0, 0, 0],
                     [0, 0, 0]];

// dimensões (geralmente usamos 2, mas podem ter 3, n)
// tensor (matriz com diversas dimensões) o tipo de 
// estrutura de dados usada na Inteligência Artificial

int[,] matriz3x4 = new int[3, 4];
/*
 [0, 0, 0, 0,
  0, 0, 0, 23,
  0, 0, 0, 0]
*/
System.Console.WriteLine();
System.Console.WriteLine();
matriz3x4[1, 3] = 23; // comprimento da primeira dimensão
for (var i = 0; i < matriz3x4.GetLength(0); i++)
{
    for (var j = 0; j < matriz3x4.GetLength(1); j++)
    {
        Console.Write(matriz3x4[i, j] + " * ");
    }
    System.Console.WriteLine();
}

foreach (var v in matriz3x4)
{
    Console.WriteLine(v);
}

// no terminal:
// dotnet add package SixLabors.ImageSharp
byte[,] imagem =
  {{0,  0,  0,   0,   0,   0, 0,   0,  0,  0},
   {0, 23, 67, 255, 255, 255, 34, 98, 34,  0},
   {0, 23, 67, 255, 255, 255, 34, 98, 34,  0},
   {0, 23, 67, 255, 255, 255, 34, 98, 34,  0},
   {0, 23, 67, 255, 255, 255, 34, 98, 34,  0},
   {0, 23, 67, 255, 255, 255, 34, 98, 34,  0},
   {0, 23, 67, 255, 255, 255, 34, 98, 34,  0},
   {0, 23, 67, 255, 255, 255, 34, 98, 34,  0},
   {0, 23, 67, 255, 255, 255, 34, 98, 34,  0},
   {0, 23, 67, 255, 255, 255, 34, 98, 34,  0} };

int altura = imagem.GetLength(0);
int largura = imagem.GetLength(1);

var bitmap = new Image<L8>(largura, altura);
for (var i = 0; i < altura; i++)
{
    for (var j = 0; j < largura; j++)
    {
        bitmap[i, j] = new L8(imagem[i, j]);
    }
}

bitmap.Save("minha-imagem.png");

/*
using System.Drawing;
using SixLabors.ImageSharp;
using SixLabors.ImageSharp.PixelFormats;

Console.WriteLine("Hello, World!");

//int[,] matriz = new int[5, 5];

//Console.WriteLine(matriz.GetLength(0));
//Console.WriteLine(matriz.GetLength(1));
int[,] matriz = new int[,]
        {
            { 0,  64, 128 },
            { 192, 255, 128 },
            { 64, 32, 0 }
        };

int altura = matriz.GetLength(0);
int largura = matriz.GetLength(1);

using (var imagem = new Image<L8>(largura, altura)) // L8 = 8-bit grayscale
{
    for (int y = 0; y < altura; y++)
    {
        for (int x = 0; x < largura; x++)
        {
            imagem[x, y] = new L8((byte)matriz[y, x]);
        }
    }

    imagem.Save("imagem_cinza.png");
}
*/