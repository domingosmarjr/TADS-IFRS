public class Aluno {

    private String nome;
    private double nota1;
    private double nota2;

    Aluno (String nome, double n1, double n2){
        if(n1 >= 0 && n2 >= 0 && n1 <= 10 && n2 <= 10) {
            this.nome = nome;
            this.nota1 = n1;
            this.nota2 = n2;
        } else {
            throw new IllegalArgumentException("Notas inválidas.");
        }
    }

    public double calcularMedia(){
        double media = (nota1 + nota2) / 2;
        return media;
    }

}