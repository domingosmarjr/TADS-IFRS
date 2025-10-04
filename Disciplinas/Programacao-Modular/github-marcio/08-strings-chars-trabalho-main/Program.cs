Console.WriteLine("Hello, World!");

string nada = null;
            // 012345
string nome = "Marcio";
// inferência de tipo: descobre pela atribuição
var sobrenome = "Torres";

Console.WriteLine(nome);
Console.WriteLine(nome.Length); // comprimento

Console.WriteLine(nome[1]); // segundo caractere (char)
// char -> short (inteiro de 16bits => tabela ascii + unicode)
char letra = nome[1];
Console.WriteLine(letra);
Console.WriteLine( (int) letra);
int num = 100;
Console.WriteLine( (char) num);
Console.WriteLine( (char) (num + 1));
// for para percorrer a string
for (int i = 0; i < nome.Length; i++) {
    Console.WriteLine(  (char)   (nome[i] + 1) );
}
// paracada (percorre até a exaustão)
foreach (char caractere in nome) {
    Console.WriteLine(caractere);
}

var curso = "analise";

string curso2 = Strings.Capitalizar(curso);

Console.WriteLine(curso2); // Analise

Console.WriteLine(Strings.Capitalizar("TADS"));

// Console.WriteLine(Strings.Capitalizar(null));

// TRABALHO, IMPLEMENTAR CONFORME A SAÍDA ESPERADA:

Console.WriteLine(Strings.Reverse("analise")); // esilana

Console.WriteLine(Strings.Rotate("analise")); // eanalis
// seanali
Console.WriteLine(Strings.Rotate(Strings.Rotate("analise")));

Console.WriteLine(Strings.Repeat("tads", 3)); // tadstadstads