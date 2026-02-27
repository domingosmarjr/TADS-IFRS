public class Main {
    public static void main(String args[]){
        // ### Exercício 5: Modificadores de Acesso em uma Classe `Aluno`

        // **Descrição:**
        // Crie uma classe `Aluno` com os seguintes atributos:
        // - `nome` (public)
        // - `matricula` (protected)
        // - `notaFinal` (private)

        // Implemente os seguintes métodos:
        // - **Getters e Setters** para o atributo `notaFinal` (o valor da nota deve ser entre 0 e 10).
        // - **Método `exibirDados()`**: Mostra o nome, matrícula e nota final.

        // **Tarefa:**
        // - Crie objetos da classe `Aluno` e teste o acesso a `nome`, `matricula`, e `notaFinal` conforme os modificadores de acesso permitirem.
        // - Tente acessar diretamente o atributo `notaFinal` e veja o que acontece.

        Aluno a1 = new Aluno("Luana",123456);
        System.out.println(a1.matricula);

        a1.setNotaFinal(9.9);
        a1.setNotaFinal(1.2);
        System.out.println(a1.getNotaFinal() == 1.2);

        try {
            a1.setNotaFinal(11);
        } catch (Exception e) {
            System.out.println(true);
        }

        // System.out.println(a1.nome == "Luana");
        System.out.println(a1.getNome() == "Luana");
        System.out.println(a1.matricula == 123456);
        // System.out.println(a1.notaFinal);
        System.out.println(a1.getNotaFinal() == 1.2);

        System.out.println(a1.exibirDados());

    }
}