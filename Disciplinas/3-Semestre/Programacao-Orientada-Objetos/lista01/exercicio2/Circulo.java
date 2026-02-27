public class Circulo {
    private double raio;
    private static final double pi = 3.14;

    Circulo(double r) {
        if (r > 0) {
            this.raio = r;
        } else { 
            throw new IllegalArgumentException("Número de raio inválido.");
        }
    }

    public double getRaio() {
        return raio;
    }
    
    public double calcularArea() {
        double var1 = 3.14 * this.raio * this.raio;
        return var1;
    }

    public double calcularPerimetro() {
        double var1 = 6.28 * this.raio;
        return var1;
    }
}
