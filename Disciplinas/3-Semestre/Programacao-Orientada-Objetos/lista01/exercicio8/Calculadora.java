public class Calculadora {

    double valor1;
    double valor2;

    Calculadora () {

    }

    public double somar(double n1, double n2) {
        this.valor1 = n1;
        this.valor2 = n2;
        return valor1 + valor2;
    }

    public double subtrair(double n1, double n2) {
        this.valor1 = n1;
        this.valor2 = n2;
        return valor1 - valor2;
    }

    public double multiplicar(double n1, double n2){
        this.valor1 = n1;
        this.valor2 = n2;
        return valor1 * valor2;
    }

    public double dividir(double n1, double n2) {
        this.valor1 = n1;
        this.valor2 = n2;
        if(valor2 != 0) return valor1/valor2;
        throw new IllegalArgumentException("Divisor não pode ser 0.");
    }
}