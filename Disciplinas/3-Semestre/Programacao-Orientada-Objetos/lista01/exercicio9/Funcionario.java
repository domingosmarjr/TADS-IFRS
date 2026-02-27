public class Funcionario {

    private String nome;
    private double salario;

    Funcionario (String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }

    public void aumentarSalario(double percentual) {
        if(percentual > 0) {
            salario = salario + (salario * (percentual/100));
        } else throw new IllegalArgumentException("Percentual inválido.");
    }

    public double getSalario(){
        return salario;
    }

    public void setSalario(double n){
        if(salario >= 0) salario = n;
    }
}