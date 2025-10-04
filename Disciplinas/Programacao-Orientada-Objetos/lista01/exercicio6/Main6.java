public class Main6 {
    public static void main(String args[]){
        // #### **Exercício 6: Instâncias Múltiplas**
        // Crie uma classe chamada `Aluno` com os seguintes atributos:
        // - Nome (String)
        // - Nota 1 (double)
        // - Nota 2 (double)

        // Implemente um método `calcularMedia()` que calcule e retorne a média do aluno. Depois, crie três instâncias dessa classe e exiba a média de cada aluno.

        // **Exemplo de uso:**
        // ```java
        Aluno aluno1 = new Aluno("Ana", 7.5, 8.0);
        Aluno aluno2 = new Aluno("Pedro", 6.0, 5.5);
        Aluno aluno3 = new Aluno("Maria", 9.0, 8.5);
        System.out.println(aluno1.calcularMedia());
        System.out.println(aluno2.calcularMedia());
        System.out.println(aluno3.calcularMedia());


    }
}