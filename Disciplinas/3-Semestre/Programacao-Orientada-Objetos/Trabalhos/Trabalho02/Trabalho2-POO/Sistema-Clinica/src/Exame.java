
import java.time.LocalDateTime;

public class Exame implements IAgendavel {
    private String nome;
    private TipoConsulta tipoConsulta = TipoConsulta.EXAME;
    private LocalDateTime dataHora;
    private Funcionario funcionario;
    private Paciente paciente;
    private String laudo = "";
    private boolean exameAberto;

    public Exame (String nome, Funcionario funcionario, Paciente paciente, LocalDateTime data) {
        this.nome = nome;
        this.funcionario = funcionario;
        this.paciente = paciente;
        this.dataHora = data;
        this.exameAberto = true;
        this.tipoConsulta = TipoConsulta.EXAME;
    }

    // ----GETTERS-----
    public String getNome() { return this.nome; }
    public Paciente getPaciente() { return this.paciente; }
    private boolean getStatus() { return this.exameAberto; }
    public String getLaudo() { return this.laudo; }
    @Override
    public LocalDateTime getDataHora() { return this.dataHora; }
    @Override
    public TipoConsulta getTipo() { return this.tipoConsulta; }
    @Override
    public Funcionario getFuncionario() { return this.funcionario; }

    // -----SETTERS-----
    private void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    // ----OPERAR AGENDA-----
    @Override
    public void agendar(AgendaClinica agenda) {
        agenda.getListaExames().add(this);
    }
    @Override
    public void desagendar(AgendaClinica agenda, Paciente p, int ano, int mes, int dia, int hora) {
        agenda.getListaExames().remove(this);
    }
    
    // ----GERAR LAUDO-----
    public void encerrarExame(String laudo) {
        this.exameAberto = false;
        this.setLaudo(laudo);
    }

    // ----UTIL-----
    private String formataHora(LocalDateTime data) {
        return data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear() + " - " + data.getHour() + ":00";
    }

    @Override
    public String toString() {
        return "+==================================================\n|EXAME:                            \n|     Nome: " + this.getNome() +
        "\n|     Tipo: " + this.getFuncionario().getTipo()+ 
        "\n|     Funcionario: " + this.getFuncionario().getNome() + 
        "\n|     Hora: " + formataHora(this.getDataHora()) +
        "\n|     Status: " + this.getStatus() +
        "\n|     Laudo: " + this.getLaudo() +
        "\n+==================================================\n";
    }
}
