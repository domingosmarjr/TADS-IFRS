
public class Paciente extends Pessoa{
    private String dataNascimento;
    private String numero;

    Paciente (String nome, String dNascimento, String num) {
        this.dataNascimento = dNascimento;
        this.numero = num;
        this.setNome(nome);
    }

}