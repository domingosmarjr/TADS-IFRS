public class Main {
    public static void main(String args[]) {
        // ### Exercício 2: Classe `Carro` com Modificadores de Acesso
        // **Descrição:**
        // Crie uma classe `Carro` com os seguintes atributos:
        // - `marca` (String) - público
        // - `modelo` (String) - protegido
        // - `ano` (int) - privado
        // Implemente getters e setters para o atributo `ano`. O setter de `ano` deve garantir que o valor seja maior que 1885 (primeiro carro foi criado em 1886).
        // **Tarefa:**
        // - Crie dois objetos da classe `Carro`.
        // - Defina os valores dos atributos e exiba-os usando métodos e acesso direto onde permitido.
        // - Teste a validação do ano.

        Carro c2 = new Carro();
        c2.setMarca("Volkswagen");
        c2.setModelo("T-Cross");
        c2.setAno(2020);

        System.out.println(c2.getMarca() == "Volkswagen");
        System.out.println(c2.getModelo() == "T-Cross");
        System.out.println(c2.getAno() == 2020);
        
        try {
            c2.setAno(1700);
        } catch(Exception e) {
            System.out.println(true);
        }

        System.out.println(c2);
    }
}