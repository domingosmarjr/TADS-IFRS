package sistema_eventos.negocio;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Participante {
    private int id;
    private String nome;
    private Date data_nascimento;
    private String cpf;
    private String email;
    private int usuario_id;
    private List<Evento> eventos = new ArrayList<>();
    private Usuario usuario;

    public int getUsuarioId() { return usuario_id; }
    public int getId() { return id; }
    public String getNome() { return nome; }
    public Date getNascimento() { return data_nascimento; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public Usuario getUsuario() { return usuario; }
    public List<Evento> getEventos() { return eventos; }
    public String getData_nascimento() {
        if (data_nascimento == null) return "";
        return new SimpleDateFormat("dd/MM/yyyy").format(data_nascimento);
    }
    public String getNascimentoParaInput() {
        if (data_nascimento == null) return "";
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(data_nascimento);
    }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setUsuarioId(int usuarioId) {this.usuario_id = usuarioId;}
    public void setId(int i) { this.id = i; }
    public void setNome(String n) { this.nome = n;}
    public void setNascimento(Date d) { this.data_nascimento = d; }
    public void setEmail(String n) { this.email = n; }
    public void setEventos(List<Evento> eventos) { this.eventos = eventos; }
    public void setCpf(String n) {
        if(n == null) throw new IllegalArgumentException("CPF não pode ser nulo");
        String cpfApenasNumeros = n.replaceAll("\\D", "");
        if (cpfApenasNumeros.length() != 11) {
            throw new IllegalArgumentException("11 dígitos no CPF.");
        }
        this.cpf = cpfApenasNumeros;
    }


    
}
