
public class Main2 {
    public static void main(String args[]){
        /*
         * #### **Exercício 2: Métodos com Retorno**
            Crie uma classe chamada `Círculo` com:
            - Um atributo `raio` (double).
            - Um método `calcularArea()` que retorna a área do círculo.
            - Um método `calcularPerimetro()` que retorna o perímetro do círculo.

            **Dica:** A fórmula da área do círculo é `π * raio^2` e o perímetro é `2 * π * raio`.

            **Exemplo de uso:**
            ```java
            Circulo circulo1 = new Circulo(5);
            double area = circulo1.calcularArea();
            double perimetro = circulo1.calcularPerimetro();
         */

         Circulo circulo1 = new Circulo(5);

         System.out.println(circulo1.getRaio());
         System.out.println(circulo1.calcularArea());
         System.out.println(circulo1.calcularPerimetro());
    }
}
