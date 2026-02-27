import java.util.ArrayList;

public class BancoFuncionarios {
    
    ArrayList<Funcionario> lista1 = new ArrayList(Funcionario);

    BancoFuncionarios() { }

    public void adicionarFuncionario(Funcionario f) {
        if(!funcionarioJaAdicionado(f)) {
            lista1.add(f);
        } else {
            throw new IllegalArgumentException("Funcionario já adicionado.");
        }
    }

    private boolean funcionarioJaAdicionado(Funcionario f) {
        boolean verificador = false;
        for (int i = 0; i < lista1.size(); i++) {
            if(lista1.get(i).getNome() == f.getNome()) verificador = true;
        }
        return verificador;
    }

    public String listarFuncionario(){
        for (int i = 0; i < lista1.size(); i++) {
            System.out.println("Funcionario: " + lista1.get(i).getNome() + " - Setor: " + lista1.get(i).getDepartamento() + "Salário: R$" + lista1.get(i).getSalario() + "\n");
        }
    }

    public int numFuncionario(String n) {
        for(int i = 0; i < lista1.size(); i++) {
            if(lista1.get(i).getNome() == n) return i;
        }
    }
    // public int get(int n) {
    //     return lista1.indexOf(n);
    // }

}