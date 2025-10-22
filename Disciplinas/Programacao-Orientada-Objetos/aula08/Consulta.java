import java.sql.Date;

public class Consulta {
    
    private Medico medico;
    private Paciente paciente;
    private Date data;
    private static int codigo;

    public Consulta(Medico m, Pessoa p) {
        this.medico = m;
        this.paciente = p;
        codigo++;
    }

    public void setData(Date d) {
        this.data = d;
    }
    public void setMedico(Medico m) {
        this.medico = m;
    }
    public void setPessoa(Pessoa p) {
        this.pessoa = p;
    }
    public Date getData() {
        return data;
    }
    public Medico getMedico(){
        return medico;
    }
    public Paciente getPaciente() {
        return paciente;
    }

}