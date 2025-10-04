public class Aluno {
    
    // ATRIBUTO ESTÁTICO É COMPARTILHADO ENTRE OS OBJETOS DA CLASSE
    // ELE PERTENCE A CLASSSE COMO UM TODO (como um contador de objetos instanciados)
    private static int geradorDeInscricao = 0;
    private String nome;
    private String numInscricao;

    public Aluno(String nome) {
        geradorDeInscricao++;
        this.nome = nome;
        this.numInscricao = String.format("2025%05d", geradorDeInscricao);
    }

    public String getNum() {
        return numInscricao;
    }

    // OVERRIDE = SOBRESCREVER
    @Override
    public String toString() {
        return this.numInscricao + " - " + this.nome;
    }

}
