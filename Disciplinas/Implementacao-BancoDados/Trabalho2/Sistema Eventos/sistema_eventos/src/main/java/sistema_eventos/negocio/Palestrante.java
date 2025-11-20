package sistema_eventos.negocio;

public class Palestrante {
    private int id;
    private String nome;
    private String biografia;
    private String cpf;
    private String email;
    private String usuario;
    private int totalPalestras;
    private String senha;
    private int usuarioId;
    private boolean selecionado;

    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getBiografia() {return biografia;}
    public String getCpf() {return cpf;}
    public int getTotalPalestras() { return totalPalestras; }
    public String getEmail() { return email; }
    public boolean isSelecionado() { return selecionado; }
    public String getUsuario() { return usuario; }
    public String getSenha() { return senha; }
    public int getUsuarioId() { return usuarioId; }

    public void setSelecionado(boolean s) { this.selecionado = s; }
    public void setId(int i) { this.id = i; }
    public void setNome(String n) { this.nome = n; }
    public void setEmail(String email) { this.email = email; }
    public void setBiografia(String b) { this.biografia = b; }
    public void setTotalPalestras(int tPalestras) { this.totalPalestras = tPalestras; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public void setCpf(String cpf) {
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF precisa de 11 dígitiso.");
        }
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("Valor inválido.");
        }
        this.cpf = cpf;
    }

}
