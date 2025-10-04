Console.WriteLine("Hello, World!");

// C# .NET
// Estrutura base (3 formas de criar)
// List<tipo> nome = new List<tipo>();
List<string> nomes1 = new List<string>();
List<string> nomes2 = new();
var nomes3 = new List<string>();
List<string> nomes4 = []; //lista vazia

Console.WriteLine(nomes1);

// Add (append)
// Remove (remover)
// Count (tamanho)
// Insert (inserir em índice)
// Clear (limpar)
// IndexOf (índice de elemento)
// Contains (checar se há um elemento)

Console.WriteLine(nomes1.Count); //contagem
nomes1.Add("Domingos");
nomes1.Add("Carlos");
Console.WriteLine(nomes1[0]);
Console.WriteLine(nomes1.Count);

// Carlos tá na lista?
if (nomes1.Contains("Carlos")) Console.WriteLine("O Carlos está na lista.");

nomes1.Insert(0, "Janaiton");

Console.WriteLine(nomes1.IndexOf("Domingos"));

if (nomes1.Remove("Carlos"))
{
    Console.WriteLine("Carlos foi removido");
}
else
{
    Console.WriteLine("Carlos não foi encontrado.");
}

nomes1.Clear();

Console.WriteLine(nomes1.Count);

var idades1 = new List<int>();
idades1.Add(48);

// Criar com valores já!
// var idades2 = new List<int> { 48, 22 };
// List<int> idades3 = { 48, 22 };
List<int> idades4 = new() { 48, 22 };

var matriculas = new List<int> { 2025123, 2025124 };

// Precisamos guardar um número de matrícula e nomes;
// Precisamos de uma estrutura de dados heterogênea;
// Possa misturar possíveis diferentes tipos de dados;
// classes, estruturas (structs), registros (records), e tuplas

// RECORD
//      estrutura de dados que permite ter um conjunto de dados diferentes
Aluno a1 = new Aluno(12345, "Pedro");
var a2 = new Aluno(3214, "Jorge");
Aluno a3 = new(444333222, "Matheus");

Console.WriteLine(a1);
Console.WriteLine(a1.Matricula);
Console.WriteLine(a1.Nome);

// Lista de alunos que contem o Record Aluno
List<Aluno> Alunos = new() { a1, a3, new Aluno(2025567, "Janaiton") };

foreach (var aluno in alunos)
{
    Console.WriteLine(aluno.Nome);
}

alunos.Add(new Aluno(2024567, "Vergada"));

// Aninhamento de Estruturas de Dados

//      Turma:
//          - Disciplina: Programação Modular
//          - Sala: Laboratório 5
//          - Período: 2025-1
//          - Alunos:
//              Aluno 0
//              Aluno 1
//              Aluno 2
//          - Professor: Marcio, Engenharia de Software

// estrutura de dados heterogênea aninhada!
Turma pm = new Turma("Programacao Modular",
                     "2025-1",
                     new Professor("Marcio", "Engenharia de Software"),
                     new List<Aluno>
                     {
                        new Aluno(202532123,"Vergara"),
                        new Aluno(202543232, "Janaiton"),
                        new Aluno(202543222, "Domingos")
                     }
);

Console.WriteLine(pm.Aluno[1].Nome);