public class Main7 {
    public static void main(String args[]){
        // #### **Exercício 7: Métodos e Atributos Privados**
        // Crie uma classe `Produto` com os seguintes atributos privados:
        // - Nome (String)
        // - Preço (double)

        // Implemente métodos para:
        // - Definir o preço (com uma verificação para não permitir valores negativos).
        // - Exibir as informações do produto.

        // **Exemplo de uso:**
        // ```java
        Produto produto1 = new Produto("Laptop", 3000.00);
        produto1.setPreco(3200.00);
        System.out.println(produto1.getPreco());
        System.out.println(produto1.exibirInfo());

    }
}