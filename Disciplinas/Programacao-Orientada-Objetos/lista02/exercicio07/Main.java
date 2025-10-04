import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String args[]){
        // ### Exercício 7: Criando um Sistema de Funcionários
        // **Descrição:**
        // Crie uma classe `Funcionario` com os seguintes atributos:
        // - `nome` (String)
        // - `departamento` (String)
        // - `salario` (double)
        // Implemente métodos:
        // - `aumentarSalario(double porcentagem)`: Aumenta o salário em uma determinada porcentagem.
        // - `transferir(String novoDepartamento)`: Altera o departamento do funcionário.
        // **Tarefa:**
        // - Crie uma lista de objetos `Funcionario` e implemente um menu para o usuário:
        //   1. Adicionar funcionário.
        //   2. Aumentar salário.
        //   3. Transferir funcionário.
        //   4. Listar funcionários.

        Funcionario f1 = new Funcionario("Luana","Arquitetura",3000);
        Funcionario f2 = new Funcionario("Domingos","TI",3000);
        Funcionario f3 = new Funcionario("Patricia","Segurança",3000);
        BancoFuncionarios b1 = new BancoFuncionarios();

        Scanner in = new Scanner(System.in);
        int t = 0;

        while (t != 0) {
            System.out.println("SELECIONE A FUNÇÃO");
            System.out.println("1. Adicionar funcionário.");
            System.out.println("2. Aumentar salário.");
            System.out.println("3. Transferir funcionário.");
            System.out.println("4. Listar funcionários.");
            t = in.nextInt();

            switch (t) {
                case 1:
                    System.out.println("Digite o nome do funcionário.");
                    String nome = in.next();
                    b1.adicionarFuncionario(new Funcionario(nome));
                    break;
                case 2:
                    System.out.println("Digite o nome do funcionário.");
                    String nome2 = in.next();
                    System.out.println("Digite o valor do salário em %.");
                    double porcentagem = in.nextDouble();
                    int numFuncionario = b1.numFuncionario(nome2);
                    b1.get(numFuncionario).aumentarSalario(porcentagem);
                    break;
                case 3:
                    System.out.println("Digite o nome do funcionário:");
                    String nome3 = in.next();
                    System.out.println("Digite o nome do setor.");
                    String setor = in.next();
                    int numFuncionar = b1.numFuncionario(nome3);
                    b1.get(numFuncionar).transferir(setor);
                case 4:
                    System.out.println(b1.listarFuncionario());
                    break;
                case 0:
                    System.out.println("Operação finalizada.");
                    break;
            }
        }
    }
}