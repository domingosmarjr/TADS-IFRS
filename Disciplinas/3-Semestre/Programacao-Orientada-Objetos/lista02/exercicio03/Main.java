public class Main {
    public static void main(String args[]){
        // Descrição:
        // Crie uma classe chamada `ContaBancaria` com os seguintes atributos privados:
        // - `titular` (String)
        // - `saldo` (double)

        // Implemente os seguintes métodos:
        // - Construtor para inicializar o titular com saldo inicial.
        // - Método `depositar(double valor)`**: adiciona o valor ao saldo.
        // - Método `sacar(double valor)`**: subtrai o valor do saldo, se houver saldo suficiente.
        // - Getters e setters** para o atributo `titular`. O saldo só pode ser acessado através do método `consultarSaldo()`.

        // Tarefa:
        // - Crie uma conta bancária para um titular, faça depósitos e saques.
        // - Use os getters e setters para modificar e acessar os valores.
        // - Garanta que o saldo não possa ser diretamente acessado ou modificado fora da classe.

        ContaBancaria c1 = new ContaBancaria("Pedro");
        ContaBancaria c2 = new ContaBancaria("Matheus");

        System.out.println(c1.getTitular() == "Pedro");
        System.out.println(c2.getTitular() == "Matheus");
        System.out.println(c1.consultarSaldo() == 0.0);

        c1.depositar(1000);
        System.out.println(c1.consultarSaldo() == 1000);
        c1.sacar(999);
        System.out.println(c1.consultarSaldo() == 1);

        try {
            c1.sacar(2);
        } catch (Exception e) {
            System.out.println(true);
        }
    }
}