import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AgendaMedico {
    
    private Medico medico;
    private List<Horario> horarios = new ArrayList();

    public AgendaMedico(Medico m) {
        this.medico = m;
    }

    // Abrir uma data para consultas
    public void abrirAgenda(int dia, int mes, int ano) {
        for (int hora = 9; hora <= 17; hora++){
            horarios.add(new Horario(dia, mes, ano, hora));
        }
    }

    // LISTAR AGENDA COMPLETA - TUDO (DISPONIVEL/INDISPONIVEL)
    public String listarAgendaCompleta() {
        String retorno = "";
        for (Horario horario : horarios) {
            retorno += horario +"\n";
        }
        return retorno;
    }

    // LISTAR AGENDA COMPLETA - SOMENTE CONSULTAS
    public String listarAgendaConsultas() {
        String retorno = "";
        for (Horario horario : horarios) {
            if(!horario.isDisponivel()) retorno += horario + "\n";
        }
        return retorno;
    }

    // LISTAR AGENDA DE CONSULTA NUM DIA ESPECÍFICO
    public String listarAgendaConsultas(int dia, int mes, int ano) {
        String retorno = "";
        for (Horario horario : horarios) {
            if((horario.getDataHora().get(Calendar.DAY_OF_MONTH) == dia) &&
            (horario.getDataHora().get(Calendar.MONTH) == mes) && 
            (horario.getDataHora().get(Calendar.YEAR) == ano) && (!horario.isDisponivel())) {
                retorno += horario + "\n";
            }
        }
        return retorno;
    }

    private boolean verificaHora(int hora) {
        // if(hora >= 9 && hora <= 17) return true;
        // return false;
        return (hora >= 9 && hora <= 17) ? true : false;
    }

}
