public class Funcionario {

    private String nome;
    private double salario;

    Funcionario(String n, double s) {
        if(n.length() >= 4) {
            this.nome = n;
            this.salario = 0;
        } else {
            throw new IllegalArgumentException("Nome inválido.");
        }
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double s) {
        if(s >= 1300) salario = s;
        else throw new IllegalArgumentException("Salário inválido.");
    }

    public void setNome(String n) {
        nome = n;
    }

    public String exibirInformacoes() {
        return nome + " - R$" + salario;
    }

}