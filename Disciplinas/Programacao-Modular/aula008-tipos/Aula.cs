// Em C#, Java,... as funções devem ser envelopadas em um tipo.
// Funções devem viver dentro de módulos (como Classes)
// Em C# não consigo criar uma função se não estiver envelopada numa classe

// Classe para envelopar = nome == nomeDoArquivo
// static diz que a classe NÃO GERA OBJETOS!!!
// impossível realizar um new Aula()
static class Aula {

    // MÉTODO -> função que pertence a um escopo (função de uma classe)
    // publica static(sem gerar obj) (retorna tipo uint) nome (tipo parametro1, tipo parametro2) { }
    // EM C# todos os métodos iniciam com letra maiúscula!!
    public static uint MMC(int a, int b) {
        uint n = Max((uint)a, (uint)b); // declarar n antes de usar
        for (uint i = n; ; i += n) {
            if (i % a == 0 && i % b == 0) return i; // corrigido: i % b == b → i % b == 0
        }
    }

    private static uint Max(uint a, uint b) {
        return a > b ? a : b;
    }

    // VOID -> função que não retorna nada!
    private static void Mmc2(uint a, uint b) {
        // implementação opcional
    }

    // PUBLIC -> método que pode ser usado fora da classe
    // PRIVATE -> método que só pode ser usado dentro da classe [padrão do C#]
}
