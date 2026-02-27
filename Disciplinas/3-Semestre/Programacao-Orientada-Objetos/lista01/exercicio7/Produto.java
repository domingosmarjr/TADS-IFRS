public class Produto {
    private String nome;
    private double preco;

    Produto (String nome, double preco) {
        if(nome.length() > 0 && preco >= 0) {
            this.nome = nome;
            this.preco = preco;
        } else {
            throw new IllegalArgumentException("Dados inválidos.");
        }
    }

    public void setPreco(double novoPreco) {
        if (novoPreco >= 0) {
            preco = novoPreco;
        } else {
            throw new IllegalArgumentException ("Novo preco inválido.");
        }
    }

    public double getPreco(){
        return preco;
    }

    public String exibirInfo(){
        return "Produto: " + nome + "\nPreço: R$" + preco; 
    }
}