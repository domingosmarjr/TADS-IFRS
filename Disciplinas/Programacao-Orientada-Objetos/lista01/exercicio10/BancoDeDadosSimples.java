import java.util.ArrayList;
import java.util.List;

public class BancoDeDadosSimples {

    private List<Pessoa> pessoas = new ArrayList<>();

    public void adicionarPessoa(Pessoa p){
        pessoas.add(p);
    }

    public void removerPessoa(String nome) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getNome().equals(nome)){
                pessoas.remove(i);
            }
        }
    }

    public void exibirPessoas() {
        for (int i = 0; i < pessoas.size(); i++) {
            System.out.println("[" + i + "] - " + pessoas.get(i).getNome());
        }
    }
}