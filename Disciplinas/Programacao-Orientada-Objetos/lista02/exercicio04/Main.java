public class Main {
    public static void main(String args[]){
        // ### Exercício 4: Implementando uma Classe `Funcionario`
        // **Descrição:**
        // Crie uma classe `Funcionario` com os seguintes atributos privados:
        // - `nome` (String)
        // - `salario` (double)
        // Implemente os seguintes métodos:
        // - **Construtor** para inicializar `nome` e `salario`.
        // - **Getter e Setter para o `salario`**: O setter deve garantir que o salário seja maior que o salário mínimo (use um valor fictício).
        // - **Método `exibirInformacoes()`**: Exibe o nome e o salário do funcionário.
        // **Tarefa:**
        // - Instancie objetos da classe `Funcionario`, modifique o salário, e exiba as informações usando o método `exibirInformacoes()`.
        // - Teste a validação do setter de `salario`.
        Funcionario f1 = new Funcionario("Pedro", 2459.20);

        System.out.println(f1.getNome() == "Pedro");
        System.out.println(f1.getSalario() == 2459.20);

        f1.setSalario(1300);

        System.out.println(f1.getSalario() == 1300);

        try {
            f1.setSalario(1000);
        } catch (Exception e) {
            System.out.println(true);
        }

        System.out.println(f1.exibirInformacoes());

    }
}
