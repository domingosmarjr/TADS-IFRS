package negocio;

import java.sql.Date;

public class Participante {
    private int id;
    private String nome;
    private Date data_nascimento;
    private String cpf;
    private String email;


    public Participante() {

    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public Date getNascimento() { return data_nascimento; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }

    public void setId(int i) {
        this.id = i;
    }
    public void setNome(String n) {
        this.nome = n;
    }
    public void setNascimento(Date d) {
        this.data_nascimento = d;
    }
    public void setCpf(String n) {
        if (n.length() != 11) {
            throw new IllegalArgumentException("11 dígitos no CPF.");
        }
        if (!n.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = n;
    }
    public void setEmail(String n) {
        this.email = n;
    }

}
