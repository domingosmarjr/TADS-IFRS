public class Secretaria extends Funcionario {
    
    public Secretaria (String n, String cpf, String email, String telefone) {
        super.setNome(n);
        super.setCpf(cpf);
        super.setEmail(email);
        super.setTelefone(telefone);
    }

    @Override
    public String toString() {
        return "-------Secretaria-------\n| Código: " + this.getCodigo() + 
        "\n| Nome: " + this.getNome() + "\n--------------------";
    }    
}