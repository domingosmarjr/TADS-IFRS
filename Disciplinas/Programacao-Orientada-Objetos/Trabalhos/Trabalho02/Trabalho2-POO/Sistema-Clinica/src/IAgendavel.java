
import java.time.LocalDateTime;

public interface IAgendavel {
    public LocalDateTime getDataHora();
    public TipoConsulta getTipo();
    public Funcionario getFuncionario();

    // Alteraria a assinatura desses métodos de agendamento com calma.
    // public void agendar(AgendaClinica a, Paciente paciente, Medico medico, int ano, int mes, int dia, int hora);
    public void agendar(AgendaClinica a);
    public void desagendar(AgendaClinica a, Paciente p, int ano, int mes, int dia, int hora);

}