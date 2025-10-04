public class Carro {
    
    private String modelo;
    private String cor;
    private int ano;

    Carro (String m, String c, int a) {
        this.modelo = m;
        this.cor = c;
        this.ano = a;
    }

    public String exibirDetalhes() {
        return "Modelo: " + this.modelo + "\nCor: " + cor + "\nAno: " + ano;
    }
}
