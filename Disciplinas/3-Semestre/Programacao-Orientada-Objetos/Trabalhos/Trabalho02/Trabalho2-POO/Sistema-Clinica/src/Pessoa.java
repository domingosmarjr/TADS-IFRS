
abstract class Pessoa {

    private String nome;
    private String cpf;
    private String email;
    protected String telefone;

    // GETTERS
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }

    // SETTERS
    public void setNome(String nome) {
        if(validaNome(nome)) this.nome = nome;
        else throw new IllegalArgumentException("Nome inválido.");
    }
    public void setCpf(String cpf) {
        if(validaCpf(cpf)) this.cpf = cpf;
        else throw new IllegalArgumentException("CPF inválido.");
    }
    public void setEmail(String email) {
        if(validaEmail(email)) this.email = email;
        else throw new IllegalArgumentException("E-mail inválido.");
    }
    public void setTelefone(String telefone) {
        if(validaTelefone(telefone)) this.telefone = telefone;
        else throw new IllegalArgumentException("Telefone inválido.");
    }

    // VALIDADORES
    private boolean validaNome(String nome) {
        return (nome.length() >= 2);
    }
    private boolean validaCpf(String cpf) {
        return (cpf.matches("\\d{11}"));
    }
    private boolean validaEmail(String email) {
        return (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"));
    }
    private boolean validaTelefone(String telefone) {
        return telefone.matches("\\d{13}");
    }
}
