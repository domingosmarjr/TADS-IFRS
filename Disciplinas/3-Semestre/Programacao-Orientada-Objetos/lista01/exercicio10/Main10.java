public class Main10 {
    public static void main(String args[]){
        // #### **Exercício 10: Criando um Banco de Dados Simples**
        // Crie uma classe `BancoDeDadosSimples` que armazene uma lista de objetos `Pessoa`. Implemente métodos para:
        // - Adicionar uma nova pessoa à lista.
        // - Remover uma pessoa pela nome.
        // - Exibir todas as pessoas cadastradas.

        // **Exemplo de uso:**
        // ```java
        BancoDeDadosSimples bd = new BancoDeDadosSimples();
        System.out.println(bd);
        bd.adicionarPessoa(new Pessoa("João", 30, 1.80));
        bd.adicionarPessoa(new Pessoa("Maria", 25, 1.65));
        bd.exibirPessoas();
        bd.removerPessoa("João");
        bd.exibirPessoas();

    }
}