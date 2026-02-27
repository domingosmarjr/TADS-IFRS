// See https://aka.ms/new-console-template for more information
Console.WriteLine("Hello, World!");
// int 32bits (com sinal)
int x = int.MaxValue; // 2^31 a 2^31-1

Console.WriteLine(x);
// int 64bits (com sinal)
long y = (long) x; // houve conversão implícita (type promotion)

Console.WriteLine(y);
// não funciona (pq long não cabe em int)
// int z = y;

// cast (coerção -- type coercion)
// podem incorrer em perda de informação
int z = (int) y; // coerção de long para int

Console.WriteLine(z);

double d = z;

Console.WriteLine(d);

         // cast de double para float
float f = (float) d;

Console.WriteLine(f);
// 764 é um byte, short, int ou long
Console.WriteLine((764).GetType());

long id = 764L; // 764 é um literal long
float preco = 1.99F;

// unsigned integer (uint)
uint semsinal = 2u;
// 64bits
long estrelas = 5_000_000_000;
Console.WriteLine(semsinal + estrelas);

double valor = 0.1;
Console.WriteLine(valor + valor + valor);

decimal pre = (decimal)0.1;
decimal pos = 0.1m; // monetary
// byte é sem sinal
byte red = 121;
byte green = 129;
byte blue = 255;
Console.WriteLine((red, green, blue));

// signaled byte (byte com sinal --- 8 bits)
sbyte ajuste = -10; // -10 a +10    (32 bits)

bool b1 = true; // boolean 1 bit

// Numéricos
// byte, short, int, long (sbyte, ushort, uint, ulong)
// float, double
// decimal



// NULL
// bool? b2 = null;
// THREE-VALUED LOGIC (lógica trivalorada)
// true && true = true
// true || true = true
// true && null = null
// true || null = true
// false || null = null
int b = 7;
// downcasting, type narrowing (estreitamento de tipo)
short res = (short)Aula.Mmc((uint)5.9, (uint)b);
// post morten
Console.WriteLine(res);

