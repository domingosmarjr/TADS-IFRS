import java.util.ArrayList;
import java.util.List;

public class Medico extends Pessoa{

    private String crm;
    private List<String> especializacao = new ArrayList();

    Medico(String nome, String crm, List<String> n) {
        this.setNome(nome);
        this.crm = crm;
        this.especializacao = n;
    }    
}