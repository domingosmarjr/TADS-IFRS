class Produto {
    
    private String nome;
    private double preco;
    private int estoque;

    // Produto(String n, double p, int e) {
    //     this.nome = n;
    //     this.preco = p;
    //     this.estoque = e;
    // }

    Produto() {

    }

    public void setNome(String n) {
        if(n.length() >= 4) nome = n;
        else throw new IllegalArgumentException("Nome inválido.");
    }


    public void setPreco(double n) {
        if(n >= 0) preco = n;
        else throw new IllegalArgumentException("Preco inválido.");
    }

    public void setEstoque(int n) {
        if(n >= 0) estoque = n;
        else throw new IllegalArgumentException("Valor inválido.");
    }

    public String getNome() {
        return nome;
    }

    public double getPreco(){
        return preco;
    }

    public int getEstoque(){
        return estoque;
    }

    @Override
    public String toString() {
        return nome + " - Preco: R$" + preco + " - Estoque: " + estoque;
    }
}