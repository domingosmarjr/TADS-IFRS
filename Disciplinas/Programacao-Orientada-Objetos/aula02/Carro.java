public class Carro {

    // ATRIBUTOS
    private String modelo;
    private int ano;
    private String cor;

    //===================================================    
    // MÉTODO CONSTRUTOR - MESMO NOME DA CLASSE
    // ESSE É O CONSTRUTOR PADRÃO (como meu objeto nasce)
    Carro () {

    }

    // SEGUNDO CONSTRUTOR COM PARÂMETROS
    Carro (String modelo, int ano) {
        this.modelo = modelo;
        this.ano = ano;
        this.cor = "BRANCO";
    }

    // TERCEIRO CONSTRUTOR
    Carro (String modelo, int ano, String cor) {
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
    }

    //===================================================
    // MÉTODOS
    public String informacoes() {
        return "Carro " + modelo + " da cor " + cor + " ano " + ano;
    }

    public void pintar(String cor) {
        this.cor = cor;
    }

}