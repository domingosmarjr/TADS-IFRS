public class Main8 {
    public static void main(String args[]){
        System.out.println("Oi");
        // #### **Exercício 8: Calculadora Simples**
        // Crie uma classe `Calculadora` com métodos para:
        // - Somar dois números.
        // - Subtrair dois números.
        // - Multiplicar dois números.
        // - Dividir dois números (com uma verificação para evitar divisão por zero).

        // Crie um programa que use essa calculadora para realizar algumas operações simples.

        // **Exemplo de uso:**
        // ```java
        Calculadora calc = new Calculadora();
        double soma = calc.somar(10, 5);
        double divisao = calc.dividir(10, 2);
        
        System.out.println(calc.somar(2,2));
        System.out.println(calc.somar(-3,2));
        System.out.println(calc.somar(-1,-1));

        System.out.println(calc.subtrair(2,2)); //0
        System.out.println(calc.subtrair(-3,1)); //-2
        System.out.println(calc.subtrair(-2,-1)); //-1

        System.out.println(calc.multiplicar(1,2)); //2 
        System.out.println(calc.multiplicar(-1,3)); //-3
        System.out.println(calc.multiplicar(-3,-3)); //9
        System.out.println(calc.multiplicar(0,1)); //0

        System.out.println(calc.dividir(10,5)); //2
        System.out.println(calc.dividir(2,3)); //0.6
        System.out.println(calc.dividir(-20,5)); //-4
        System.out.println(calc.dividir(10,-2)); //-5
        System.out.println(calc.dividir(1,0)); //undefined

    
    }
}