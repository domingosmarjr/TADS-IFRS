public class Main {
    public static void main(String args[]){
        /*### Exercício 1: Criando uma Classe `Produto` com Getters e Setters

        **Descrição:**
        Crie uma classe chamada `Produto` com os seguintes atributos:
        - `nome` (String)
        - `preco` (double)
        - `estoque` (int)

        Use o modificador **`private`** para os atributos e implemente os métodos **getters** e **setters** para todos os atributos. Além disso, no setter de `preco`, adicione uma validação para garantir que o preço não seja negativo.

        **Tarefa:**
        - Instancie um objeto da classe `Produto`, defina os valores dos atributos usando os setters, e exiba os valores com os getters.
        - Teste a validação do preço tentando definir um valor negativo. */

        Produto p1 = new Produto();

        p1.setNome("Chocolate");
        p1.setPreco(5.99);
        p1.setEstoque(20);

        System.out.println(p1.getNome() == "Chocolate");
        System.out.println(p1.getPreco() == 5.99);
        System.out.println(p1.getEstoque() == 20);

        p1.setPreco(2.99);
        System.out.println(p1.getPreco() == 2.99);
        try {
            p1.setPreco(-2);
        } catch (Exception e) {
            System.out.println(true);
        }       

    }
}