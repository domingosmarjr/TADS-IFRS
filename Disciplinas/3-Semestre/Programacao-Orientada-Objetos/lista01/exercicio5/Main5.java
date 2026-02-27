public class Main5 {
    public static void main(String args[]){
        // #### **Exercício 5: Comparando Objetos**
        // Crie uma classe `Retangulo` com os seguintes atributos:
        // - Largura (double)
        // - Altura (double)

        // Implemente um método `calcularArea()` que retorne a área do retângulo. Crie dois objetos dessa classe e, em seguida, crie um método `compararArea(Retangulo outro)` que compare a área de dois retângulos e indique qual é maior ou se são iguais.

        // **Exemplo de uso:**
        // ```java
        Retangulo ret1 = new Retangulo(4, 5);
        Retangulo ret2 = new Retangulo(2, 7);
        System.out.println(ret1.calcularArea());
        System.out.println(ret2.calcularArea());
        ret1.compararArea(ret2);
        System.out.println(ret1.compararArea(ret2));
        System.out.println("Oi");
    }
}