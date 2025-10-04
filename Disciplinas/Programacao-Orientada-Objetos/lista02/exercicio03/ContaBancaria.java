public class ContaBancaria {
    
    private String titular;
    private double saldo;

    ContaBancaria (String nome) {
        this.titular = nome;
        this.saldo = 0.0f;
    }

    public void depositar(double valor) {
        if(valor >= 1) saldo += valor;
        else throw new IllegalArgumentException("Valor inválido.");
    }

    public void sacar(double valor) {
        if(valor <= saldo) saldo -= valor;
        else throw new IllegalArgumentException("Valor de saque acima do saldo.");
    }

    public String getTitular() {
        return titular;
    }

    public double consultarSaldo() {
        return saldo;
    }
}