import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Paciente extends Pessoa{
    
    private static int codigo;

    private Calendar dataNascimento;
    private char sexo;
    private TipoSanguineo tipoSanguineo;
    private List<Consulta> historico = new ArrayList();

    public Paciente (String nome, String cpf, String email, String telefone, String dataNascimento, char sexo, TipoSanguineo tipoSanguineo) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setEmail(email);
        super.setTelefone(telefone);
        this.sexo = sexo;
        this.dataNascimento = converteData(dataNascimento);
        this.tipoSanguineo = tipoSanguineo;
        codigo++;
    }

    // Getters
    public static int getCodigo() { return codigo; }
    public String getDataNascimento() {
        SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy");
        return formatacao.format(this.dataNascimento.getTime());
    }
    public char getSexo() { return sexo; }
    public TipoSanguineo getTipoSanguineo() { return tipoSanguineo; }

    // Setters
    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }


    // ---------- UTIL ------------
    // Função interna de converter String -> Calendar
    private Calendar converteData(String data) {
        String[] parteData = data.split("/");
        int dia = Integer.parseInt(parteData[0]);
        int mes = Integer.parseInt(parteData[1]) - 1; //Mês no Calendar começa com 0, logo Set -> Ago
        int ano = Integer.parseInt(parteData[2]);
        Calendar dataCalendario = Calendar.getInstance();
        dataCalendario.set(ano, mes, dia, 0, 0, 0);
        return dataCalendario;
    }

    @Override
    public String toString() {
        return "----Paciente----\n" + "| Código: " + this.getCodigo() + "\n| Nome: " + this.getNome() + "\n| Data Nascimento: " + this.getDataNascimento() +
        "\n| Tipo Sanguíneo: " + this.getTipoSanguineo() + "\n-----------";
    }
}
