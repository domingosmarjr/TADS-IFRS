public class Main4 {
    public static void main(String args[]){

        // #### **Exercício 4: Métodos que Alteram Atributos**
        // Implemente uma classe `ContaBancaria` com os seguintes atributos:
        // - Número da conta (String)
        // - Saldo (double)

        // Implemente os seguintes métodos:
        // - `depositar(double valor)` para adicionar dinheiro ao saldo.
        // - `sacar(double valor)` para subtrair dinheiro do saldo, se houver saldo suficiente.
        // - `exibirSaldo()` para mostrar o saldo atual.

        // **Exemplo de uso:**
        // ```java
        ContaBancaria conta1 = new ContaBancaria("12345-6", 500.00);
        
        conta1.depositar(150.00);
        conta1.sacar(100.00);
        conta1.exibirSaldo();

        System.out.println(conta1.exibirSaldo());
        conta1.depositar(150.00);
        System.out.println(conta1.exibirSaldo());
        conta1.sacar(500.00);
        System.out.println(conta1.exibirSaldo());
    }
}
