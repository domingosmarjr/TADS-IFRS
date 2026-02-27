public class Main {
    public static void main(String args[]) {
        // ### Exercício 6: Getters e Setters com Validação na Classe `Pedido`

        // **Descrição:**
        // Crie uma classe `Pedido` com os seguintes atributos privados:
        // - `numeroPedido` (String)
        // - `quantidade` (int)
        // - `valorTotal` (double)

        // Implemente:
        // - **Getters e Setters** para todos os atributos. No setter de `quantidade`, certifique-se de que seja maior que 0. O setter de `valorTotal` deve ser calculado com base na quantidade e no valor unitário de um produto fictício.

        // **Tarefa:**
        // - Crie um objeto `Pedido`, defina os valores com os setters, e exiba os detalhes usando os getters.

        Pedido p1 = new Pedido();
        p1.setNumeroPedido("12323");
        p1.setQuantidade(2);
        p1.setValorUnitario(10.0);

        p1.setValorTotal();

        System.out.println(p1.getNumeroPedido() == "12323");
        System.out.println(p1.getQuantidade() == 2);
        System.out.println(p1.getValorUnitario() == 10.0);
        System.out.println(p1.getValorTotal() == 20.0);
    }
}