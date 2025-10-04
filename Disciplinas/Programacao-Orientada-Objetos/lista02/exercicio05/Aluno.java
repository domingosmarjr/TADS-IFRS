public class Aluno {
    private String nome;
    protected int matricula;
    private double notaFinal;

    Aluno (String n, int m) {
        this.nome = n;
        this.matricula = m;
    }

    public String getNome() {
        return nome;
    }
    public int getMatricula() {
        return matricula;
    }
    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double n) {
        if (n>=0 && n<=10) notaFinal = n;
        else throw new IllegalArgumentException("Nota inválida.");
    }

    public String exibirDados() {
        return "Nome: " + nome + "\nMatrícula: " + matricula + "\nNota Final: " + notaFinal;
    }
}