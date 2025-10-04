public class ContaBancaria {

    private final String numeroConta;
    private double saldo;

    ContaBancaria (String numConta){
        this.numeroConta = numConta;
        this.saldo = 0.0f;
    }

    ContaBancaria (String numConta, double saldo) {
        if (numConta.length()-1 < 6 || numConta.length()-1 > 6) {
            throw new IllegalArgumentException("Conta inválida");
        } else if (saldo < 0) {
            throw new IllegalArgumentException("Saldo inválido");
        } else {
            this.numeroConta = numConta;
            this.saldo = saldo;
        }
    }

    public double exibirSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if(valor >= 0) saldo += valor;
        else throw new IllegalArgumentException("Valor inserido inválido.");
    }

    public void sacar(double valor) {
        if(valor <= saldo) saldo -= valor;
        else throw new IllegalArgumentException("Valor inserido inválido");
    }
}