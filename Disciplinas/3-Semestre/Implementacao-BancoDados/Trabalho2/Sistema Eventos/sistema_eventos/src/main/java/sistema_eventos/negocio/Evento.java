package sistema_eventos.negocio;

import java.sql.Date;
import java.sql.Time;

public class Evento {

    private int id;
    private String nome;
    private Date data_inicio;
    private Time hora_inicio;
    private Date data_fim;
    private Time hora_fim;
    private String local;
    private String status;
    private String detalhes;
    private byte[] imagemBanner;
    private int organizadorId;
    private boolean selecionado; 

    public int getId() { return id; }
    public String getNome() { return nome; }
    public Date getData_inicio() { return data_inicio; }
    public Time getHora_inicio() { return hora_inicio; }
    public Date getData_fim() { return data_fim; }
    public Time getHora_fim() { return hora_fim; }
    public String getLocal() { return local; }
    public String getStatus() { return status; }
    public String getDetalhes() { return detalhes; }
    public byte[] getImagemBanner() { return imagemBanner; }
    public int getOrganizadorId() { return organizadorId; }
    public boolean isSelecionado() { return selecionado; }

    public void setSelecionado(boolean selecionado) { this.selecionado = selecionado; }
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setData_inicio(java.util.Date dataInicioSQL) {
        if (dataInicioSQL != null) {
            this.data_inicio = new java.sql.Date(dataInicioSQL.getTime());
        } else {
            this.data_inicio = null;
        }
    }
    public void setHora_inicio(Time hora_inicio) { this.hora_inicio = hora_inicio; }
    public void setData_fim(Date data_fim) { this.data_fim = data_fim; }
    public void setHora_fim(Time hora_fim) { this.hora_fim = hora_fim; }
    public void setLocal(String local) { this.local = local; }
    public void setStatus(String status) { this.status = status; }
    public void setDetalhes(String detalhes) { this.detalhes = detalhes; }
    public void setImagemBanner(byte[] imagemBanner) { this.imagemBanner = imagemBanner; }
    public void setOrganizadorId(int organizadorId) { this.organizadorId = organizadorId; }
}
