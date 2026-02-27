import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private int qntLivros;
    private List<Livro> livros = new ArrayList<>();

    Biblioteca (String t) {
        this.nome = t;
    }

    public void adicionarLivro(Livro n) {
        livros.add(n);
    }

    public String listarLivros() {
        String resultado = "LIVROS DISPONÍVEIS: \n";
        for (int i = 0; i < livros.size(); i++) {
            if(livros.get(i).getDisponivel()) resultado += i + " - " +  livros.get(i).getTitulo() + "\n";
        }
        return resultado;
    }
    
}