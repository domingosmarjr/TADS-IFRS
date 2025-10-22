public class Pessoa {
    private String nome;

    Pessoa () {        
    }

    Pessoa (String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}