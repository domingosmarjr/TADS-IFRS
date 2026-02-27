package sistema_eventos.negocio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Organizador {
    private int id;
    private String nome;
    private Date data_nascimento;
    private String cpf;
    private String email;
    private int usuario_id;
    private List<Evento> eventos = new ArrayList<>();

    public int getUsuarioId() { return usuario_id; }
    public int getId() { return id; }
    public String getNome() { return nome; }
    public Date getNascimento() { return data_nascimento; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public List<Evento> getEventos() { return eventos; } // novo getter

    public void setUsuarioId(int usuarioId) { this.usuario_id = usuarioId; }
    public void setId(int i) { this.id = i; }
    public void setNome(String n) { this.nome = n; }
    public void setNascimento(Date d) { this.data_nascimento = d; }
    public void setEmail(String n) { this.email = n; }
    public void setCpf(String n) {
        if (n.length() != 11) {
            throw new IllegalArgumentException("11 dígitos no CPF.");
        }
        if (!n.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = n;
    }
    public void setEventos(List<Evento> eventos) { this.eventos = eventos; } // novo setter
}
