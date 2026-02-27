import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gabarito {

    private List<String> respostas;

    Gabarito(String valoresRespostas) {
        // split() da string transofmra texto em array "abc" -> "a","b","c"
        this.respostas = Arrays.asList(valoresRespostas.split(""));
        // for (int i = 0; i < respostas.size(); i++) {
        //     System.out.println("QUESTAO " + (i+1) + " ALTERNATIVA " + respostas.get(i));
        // }

    }

    public String getRespostaQuestao(int numero){
        if(numero > respostas.size() || numero < 0) {
            System.out.println("Resposta não existe.");
            return null;
        }
        return respostas.get(numero-1);
    }

}