// See https://aka.ms/new-console-template for more information
Console.WriteLine("Hello, World!");

// int 32bits (com sinal)
int x = int.MaxValue;
Console.WriteLine(x); //2^31 a 2^31-1

// int 64bits (com sinal)
long y = x;
Console.WriteLine(y);

int z = (int) y; //coerção (cast)

double d = z;

// float é menor que double = logo, preciso fazer cast
float f = (float) d;

long id = 764L; //764 é um literal long
float preco = 1.99F;

// uint -> unsignal int (+4KK de espaços)
uint estrelas = 3_000_000_000; //underline não conta no cálculo

//MOEDA EM C#
//  Específico de .NET e usado totalmente para dinheiro!
decimal pre = (decimal) 0.1m;
decimal pos = 0.1m; //monetary

// Byte
byte green = 129;
byte blue = 255;

// Byte com Sinal (signal byte)
sbyte ajuste = -10;

// Boolean -> 1 bit
bool b1 = true; 

