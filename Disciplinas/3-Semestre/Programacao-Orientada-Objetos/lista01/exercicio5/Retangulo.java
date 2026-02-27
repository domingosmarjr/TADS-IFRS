public class Retangulo {
    private double largura;
    private double altura;

    Retangulo (double l, double a){
        if (l > 0 && a > 0){
            this.largura = l;
            this.altura = a;
        } else {
            throw new IllegalArgumentException("Medida inválida.");
        }
    }

    public double calcularArea(){
        return largura * altura;
    }

    public String compararArea(Retangulo r1){
        double area1 = this.calcularArea();
        double area2 = r1.calcularArea();

        if(area1 > area2) {
            return ("Primeiro retângulo é maior: " + area1 + " > " + area2);
        } else if (area2 > area1) {
            return ("Segundo retângulo é maior: " + area2 + " > " + area1);
        } else {
            return ("Os dois retângulos tem a mesma área: " + area1 + " = " + area2);
        }

    }

}