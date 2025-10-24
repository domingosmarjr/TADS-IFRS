import java.util.Calendar;

public class Consulta {

    private Calendar dataHora;
    private Medico medico;
    private Paciente paciente;
    // private Prontuario prontuario;

    Consulta(Paciente paciente, Medico medico, Calendar data) {
        if(medico.dataConsultaDisponivel(data)) {
            this.medico = medico;
            this.paciente = paciente;
            this.dataHora = data;
            medico.marcarConsulta(this, data);
        }
    }

    public Calendar getDataHora() { return dataHora; }
    public Medico getMedico() { return medico; }
    public Paciente getPaciente() { return paciente; }


    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora;
    }
    public void setMedico(Medico medico) { 
        this.medico = medico;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
}
