package sistema_eventos.negocio;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

public class Palestra {
    private int id;
    private String titulo;
    private int duracao;
    private Timestamp data_hora_inicio;
    private int evento_id;
    private String eventoNome;
    private String dataHoraInput;
    private String dataHora; // formatada para dd/MM/yyyy HH:mm
    private List<Palestrante> palestrantes;

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getDuracao() { return duracao; }
    public Timestamp getData() { return data_hora_inicio; }
    public int getEventoId() { return evento_id; }
    public String getEventoNome() { return eventoNome; }
    public List<Palestrante> getPalestrantes() { return palestrantes; }
    public String getDataHora() { return dataHora; }
    public String getDataHoraInput() { return dataHoraInput; }

    public void setId(int id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDuracao(int duracao) {
        if (duracao < 0) throw new IllegalArgumentException("Duração de palestra inválida.");
        this.duracao = duracao;
    }
    public void setData(Timestamp data) { 
        this.data_hora_inicio = data; 
        if (data != null) {
            this.dataHora = formatarDataHora(data);
            this.dataHoraInput = formatarDataHoraInput(data);
        }
    }
    public void setEventoId(int evento_id) { this.evento_id = evento_id; }
    public void setEventoNome(String eventoNome) { this.eventoNome = eventoNome; }
    public void setPalestrantes(List<Palestrante> palestrantes) { this.palestrantes = palestrantes; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }
    public void setDataHoraInput(String dataHoraInput) { this.dataHoraInput = dataHoraInput; }

 
    private String formatarDataHora(Timestamp ts) {
        LocalDateTime ldt = ts.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return ldt.format(formatter);
    }

    private String formatarDataHoraInput(Timestamp ts) {
        LocalDateTime ldt = ts.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return ldt.format(formatter);
    }
}
