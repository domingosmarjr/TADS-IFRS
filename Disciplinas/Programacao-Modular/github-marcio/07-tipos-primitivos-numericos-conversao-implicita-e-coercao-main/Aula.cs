// Em C#, Java, ..., as funções DEVEM ser envelopadas em um Tipo

// Toda linguagem em que a função pode ser declarada sem um tipo, a função é um cidadão de primeira-classe (First-Class Citizen)

// Funções que devem viver dentro de módulos (class, struct, record, enum, etc)

// no "mundo" das classes existem as que
// geram objetos (new) e as que não geram
// no caso de classes sem objetos,
// chamamos isso de CONTEXTO ESTÁTICO

// static: essa classe não gera objetos 
// (não posso fazer new Aula())
static class Aula
{
    // método (função que pertence a um escopo)
    // que recebe dois inteiros sem sinal
    // e devolve um inteiro sem sinal
    // por padrão, o método é privado (private)
    public static uint Mmc(uint a, uint b) // 0 a 4.2bi
    {
        uint n = Max(a, b);
        for (uint i = n; ; i += n)
        {
            if (i % a == 0 && i % b == 0)
            {
                return i;
            }
        }
    }

    private static uint Max(uint a, uint b)
    {
        return a > b ? a : b;
    }


}
