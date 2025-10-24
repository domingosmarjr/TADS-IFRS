public class Tecnico extends Funcionario {
    private int codTecnico;

    public Tecnico (String nome, String cpf, String email, String telefone, int codTec) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setEmail(email);
        super.setTelefone(telefone);
        this.codTecnico = codTec;
        this.proximoCodigo();
    }

    public int getCodTecnico() { return codTecnico; }

    public void setCodTecnico(int codTecnico) {
        this.codTecnico = codTecnico;
    }

    // TODO: darLaudo(String descricao)

    // TODO: realizarExame()

}
