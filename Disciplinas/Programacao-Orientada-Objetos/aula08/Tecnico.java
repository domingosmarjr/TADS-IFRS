import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Pessoa{

    private String ctm;
    private List<String> especializacao = new ArrayList();

    Tecnico (String nome, String ctm, List<String> p) {
        this.setNome(nome);
        this.ctm = ctm;

    }
}