public class Main3 {
    public static void main(String args[]){
        // #### **Exercício 3: Criando Objetos**
        // Crie uma classe `Carro` com os atributos:
        // - Modelo (String)
        // - Cor (String)
        // - Ano (int)

        // Crie dois objetos da classe `Carro` com valores diferentes e exiba as informações de cada carro utilizando um método `exibirDetalhes()`.

        // **Exemplo de uso:**
        // ```java
        Carro carro1 = new Carro("Civic", "Prata", 2021);
        Carro carro2 = new Carro("Corolla", "Preto", 2020);
        System.out.println(carro1.exibirDetalhes());
        System.out.println(carro2.exibirDetalhes());        

    }
}