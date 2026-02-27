public class Main9{
    public static void main(String args[]){

        // #### **Exercício 9: Construtores**
        // Crie uma classe `Funcionario` com:
        // - Nome (String)
        // - Salário (double)

        // Implemente um construtor que inicialize esses atributos e um método `aumentarSalario(double percentual)` para aumentar o salário com base em um percentual.

        // **Exemplo de uso:**
        // ```java
        Funcionario func1 = new Funcionario("Carlos", 2500.00);
        System.out.println(func1.getSalario());
        func1.setSalario(1000.00);
        System.out.println(func1.getSalario());
        func1.aumentarSalario(10); // Aumenta 10% no salário
        System.out.println(func1.getSalario());

    }
}