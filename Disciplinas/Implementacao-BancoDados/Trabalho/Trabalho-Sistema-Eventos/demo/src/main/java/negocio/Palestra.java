package negocio;

import java.security.Timestamp;

public class Palestra {
    private int id;
    private String titulo;
    private int duracao;
    private Timestamp data_hora_inicio;
    private int evento_id;

    public Palestra() {

    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getDuracao() { return duracao; }
    public Timestamp getData() { return data_hora_inicio; }
    public int getEventoId() { return evento_id; }

    public void setId(int i) {
        this.id = i;
    }
    public void setTitulo(String n) {
        this.titulo = n;
    }
    public void setDuracao(int n) {
        if(n<0) throw new IllegalArgumentException("Duração de palestra inválido.");
        this.duracao = n;
    }
    public void setData(Timestamp t) {
        this.data_hora_inicio = t;
    }
    public void setEventoId(int i) {
        this.evento_id = i;
    }
}
