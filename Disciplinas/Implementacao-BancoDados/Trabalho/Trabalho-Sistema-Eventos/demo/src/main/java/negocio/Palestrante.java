package negocio;

public class Palestrante {
    private int id;
    private String nome;
    private String biografia;
    private String cpf;
    private int totalPalestras;

    public Palestrante() {

    }

    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getBiografia() {return biografia;}
    public String getCpf() {return cpf;}
    public int getTotalPalestras() { return totalPalestras; }

    public void setId(int i) {
        this.id = i;
    }
    public void setNome(String n) {
        this.nome = n;
    }
    public void setBiografia(String b) {
        this.biografia = b;
    }
    public void setCpf(String cpf) {
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF precisa de 11 dígitiso.");
        }
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("Valor inválido.");
        }
        this.cpf = cpf;
    }
    public void setTotalPalestras(int tPalestras) {
        this.totalPalestras = tPalestras;
    }
}
