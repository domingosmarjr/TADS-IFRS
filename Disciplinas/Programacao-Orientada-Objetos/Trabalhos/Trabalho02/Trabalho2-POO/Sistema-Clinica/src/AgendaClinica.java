import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendaClinica {

    private List<Consulta> consultasMarcadas = new ArrayList();
    public List<Exame> examesMarcados = new ArrayList();

    public AgendaClinica() {

    }

    // GET EXAME (Funcionario, Paciente, Data)
    public Exame getExame(Medico medico, Paciente paciente, int dia, int mes, int ano, int hora) {
        for (Exame exame : examesMarcados) {
            if((exame.getFuncionario() == medico) && 
            (exame.getPaciente() == paciente) && 
            (exame.getDataHora().getYear() == ano) && 
            (exame.getDataHora().getMonthValue() == mes) && 
            (exame.getDataHora().getDayOfMonth() == dia) && 
            (exame.getDataHora().getHour() == hora)) {
                return exame;
            }
        }
        return null;
    }

    // GET LIST<CONSULTA>
    public List<Consulta> getListaConsultas() {
        return this.consultasMarcadas;
    }

    // GET LIST<EXAME>
    public List<Exame> getListaExames() {
        return this.examesMarcados;
    }

    // LISTA TODAS AS CONSULTAS COMO UMA STRING
    public String listarConsultas() {
        String n = "";
        for (Consulta consulta : consultasMarcadas) {
            n+= consulta.toString();
        }
        return n;
    }
    // LISTA TODOS OS EXAMES COMO UMA STRING
    public String listarExames() {
        String n = "";
        for (Exame exame : examesMarcados) {
            n += exame;
        }
        return n;
    }
    // LISTAR TODAS AS CONSULTAS E TODOS OS EXAMES
    public String listarConsultasExames() {
        String n = "";
        n += listarConsultas();
        n += listarExames();
        return n;
    }


    // Pega consulta com data específica
    public Consulta getConsulta(LocalDateTime data) {
        for (Consulta consulta : consultasMarcadas) {
            if(consulta.getDataHora().isEqual(data)) {
                return consulta;
            }
        }
        return null;
    }

    // Pega consulta com Paciente - Data específica
    public Consulta getPacienteConsulta(Paciente paciente, LocalDateTime data) {
        for (Consulta consulta : consultasMarcadas) {
            if((consulta.getPaciente() == paciente) && (consulta.getDataHora() == data)) {
                return consulta;
            }
        }
        return null;
    }

    // VERIFICAR SLOT AGENDA DISPONIVEL
    private boolean verificarDataAgenda() {
        return true;
    }

}
