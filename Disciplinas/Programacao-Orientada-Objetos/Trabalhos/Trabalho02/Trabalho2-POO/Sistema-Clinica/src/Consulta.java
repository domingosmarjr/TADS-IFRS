import java.time.LocalDateTime;

public class Consulta implements IAgendavel {

    private static int codConsulta;
    private LocalDateTime dataHora;
    private Medico medico;
    private Paciente paciente;
    private TipoConsulta tipo = TipoConsulta.CONSULTA;
    private boolean consultaAberta;
    private Prontuario prontuario;
    
    public Consulta(int ano, int mes, int dia, int hora, Medico medico, Paciente paciente) {
        if(medico.dataEstaDisponivel(LocalDateTime.of(ano,mes,dia,hora,0))) {
            this.dataHora = LocalDateTime.of(ano, mes, dia, hora, 0);
            this.medico = medico;
            this.paciente = paciente;
            this.consultaAberta = true;
            this.codConsulta = codConsulta;
            codConsulta++;
        } else {
            throw new IllegalArgumentException("Data de consulta indisponível com médico");
        }
    }

    @Override
    public LocalDateTime getDataHora() { return this.dataHora; }
    @Override
    public TipoConsulta getTipo() { return this.tipo; }
    @Override
    public Funcionario getFuncionario() { return this.medico; }
    public Paciente getPaciente() { return this.paciente; }
    public Prontuario getProntuario() { return this.prontuario; }
    public Medico getMedico() { return this.medico; }

    // ------OPERAÇÕES DE AGENDA-------
    // AGENDA CONSULTA NA AGENDACLINICA()
    // A PARTIR DE AGENDA-PACIENTE-MEDICO-DATA
    @Override
    public void agendar(AgendaClinica agenda) {
        agenda.getListaConsultas().add(this);
    }

    // DESAGENDA CONSULTA DA AGENDACLINICA()
    // A PARTIR DE AGENDA-PACIENTE-DATA
    @Override
    public void desagendar(AgendaClinica agenda, Paciente paciente, int ano, int mes, int dia, int hora) {
        LocalDateTime data = LocalDateTime.of(ano, mes, dia, hora, 0);
        if(this == agenda.getPacienteConsulta(paciente, data)) {
            agenda.getListaConsultas().remove(this);
        }
    }
    public void desagendar(AgendaClinica agenda){
        agenda.getListaConsultas().remove(this);
    }

    // ------ENCERRAR CONSULTA -> GERA PRONTUÁRIO--------
    public void encerrar(String sintoma, String medicamento) {
        this.consultaAberta = false;
        this.prontuario = new Prontuario(this, sintoma, medicamento);
    }

    // ---UTIL---
    private String formataHora(LocalDateTime data) {
        return data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear() + " - " + data.getHour() + ":00";
    }

    @Override
    public String toString() {
        return "+==================================================\n|CONSULTA:                  \n|     Nome: " + paciente.getNome() +
        "\n|     Tipo: " +  this.getFuncionario().getTipo() +
        "\n|     Funcionario: " + medico.getNome() +
        "\n|     Hora: " + formataHora(this.getDataHora()) +
        "\n+==================================================\n";
    }
}
