
import java.util.Calendar;

public class Horario {
    
    private Calendar dataHora;
    private Consulta consulta;
    private boolean disponivel;

    public Horario (int dia, int mes, int ano, int hora) {
        dataHora = Calendar.getInstance();
        dataHora.set(ano, mes, dia, hora, 0, 0);
        this.consulta = null;
        this.disponivel = true;
    }

    public boolean isDisponivel() {
        return this.disponivel;
    }
    public Calendar getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return 
        String.format("%02d/%02d/%04d - %02d:00 - %s - %s", 
        dataHora.get(Calendar.DAY_OF_MONTH),
        dataHora.get(Calendar.MONTH),
        dataHora.get(Calendar.YEAR),
        dataHora.get(Calendar.HOUR_OF_DAY),
        this.consulta, this.disponivel);
    }
}
