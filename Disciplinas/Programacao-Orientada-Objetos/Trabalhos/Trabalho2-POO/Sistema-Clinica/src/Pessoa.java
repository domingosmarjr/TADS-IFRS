abstract class Pessoa {

    private String nome;
    private String cpf;
    private String email;
    protected String telefone;

    // Getters
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }

    // Setters
    public void setNome(String nome) {
        // TODO: validar nome
        this.nome = nome;
    }
    public void setCpf(String cpf) {
        // TODO: fazer um validador de CPF
        this.cpf = cpf;
    }
    public void setEmail(String email) {
        // TODO: fazer um validador de e-mail
        this.email = email;
    }
    public void setTelefone(String telefone) {
        // TODO: fazer um validador de telefone
        this.telefone = telefone;
    }
}
