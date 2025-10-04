public class Conta {

    private String titular;
    private float saldo;

    // ESTADO INICIAL DO MEU OBJETO QUANDO CRIADO
    Conta(String titular) {
        this.titular = titular;
        this.saldo = 0;
    }



    public String verConta() {
        return "O TITULAR " + titular + " TEM SALDO R$" + saldo;
    }

    public void depositar (float valor) {
        this.saldo += valor;
    }

    public boolean sacar (float valor) {

        if (valor > this.saldo) { //EARLY RETURN -> retorna o ruim antes de fazer qualquer cálculo
            return false;
        }
        this.saldo -= valor;
        return true;
    }
}