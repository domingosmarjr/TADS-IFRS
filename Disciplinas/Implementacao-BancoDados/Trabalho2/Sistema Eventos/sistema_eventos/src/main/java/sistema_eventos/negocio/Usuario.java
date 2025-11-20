package sistema_eventos.negocio;

public class Usuario {
    private int id;
    private String login;
    private String senha;
    private int tipo;
    
    public int getId() { return id; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public int getTipo() { return tipo; }

    public void setTipo(int tipo) { this.tipo = tipo; }
    public void setLogin(String login) { this.login = login; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setId(int id) { this.id = id; }
    
}
