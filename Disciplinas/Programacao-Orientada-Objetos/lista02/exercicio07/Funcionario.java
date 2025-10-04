public class Funcionario {
    
    private String nome;
    private String departamento = "";
    private double salario = 0.0f;


    Funcionario(String nome, String dpt, double s) { 
        this.nome = nome;
        this.departamento = dpt;
        this.salario = s;
    }

    Funcionario(String nome) { 
        this.nome = nome;
    }


    public String getNome(){
        return nome;
    }
    public String getDepartamento() {
        return departamento;
    }
    public double getSalario() {
        return salario;
    }

    public void aumentarSalario(double s){
        if (s > 0) salario += (salario * (s / 100));
        else throw new IllegalArgumentException("Porcentagem inválida.");
    }

    public void transferir(String novoDepartamento) {
        departamento = novoDepartamento;
    }



}