public class MainConta {
    public static void main(String args[]){

        Conta c1 = new Conta("VINICIUS");
        // c1.titular = "Pedro"; -> NÃO PODE ALTERAR PORQUE É PRIVADO!
        // c1.saldo = 999; -> NÃO PODE PORQUE É PRIVADO

        c1.depositar(200);
        System.out.println(c1.verConta());


        c1.sacar(50);
        System.out.println(c1.verConta()); //150

        boolean sucesso = c1.sacar(200);
        if (sucesso) {
            System.out.println("SAQUE BEM SUCEDIDO");
        } else {
            System.out.println("NÃO FOI POSSÍVEL REALIZAR O SAQUE");
        }
        
        System.out.println(c1.verConta());

    }
}