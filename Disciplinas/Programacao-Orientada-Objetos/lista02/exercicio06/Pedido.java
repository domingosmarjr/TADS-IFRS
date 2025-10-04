public class Pedido {
    
    private String numeroPedido;
    private int quantidade;
    private double valorUnitario = 0.0f;;
    private double valorTotal = 0.0f;

    Pedido() { }

    public void setNumeroPedido(String n) {
        if(n.length() == 5) numeroPedido = n;
        else throw new IllegalArgumentException("Número inválido.");
    }
    public void setQuantidade(int n) {
        if(n>0) quantidade = n;
        else throw new IllegalArgumentException("Valor inválido.");
    }
    public void setValorUnitario(double n) {
        if(n>0) valorUnitario = n;
        else throw new IllegalArgumentException("Valor inválido.");
    }
    public void setValorTotal() {
        valorTotal = quantidade * valorUnitario;
    }

    public String getNumeroPedido() { return numeroPedido; }
    public int getQuantidade() { return quantidade; }
    public double getValorUnitario() { return valorUnitario; }
    public double getValorTotal() { return valorTotal; }
}