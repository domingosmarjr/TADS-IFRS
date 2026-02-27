public class Main {
    public static void main(String args[]){

        Pessoa pessoa1 = new Pessoa("João", 25, 1.75);
        System.out.println(pessoa1.exibirInformacoes());
        pessoa1.alterarIdade(26);
        System.out.println(pessoa1.exibirInformacoes());

    }
}