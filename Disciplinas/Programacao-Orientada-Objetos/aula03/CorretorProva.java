import java.util.List;

public class CorretorProva {

    public void corrigir(List<Provas> provas, Gabarito gabarito){
            for (Prova prova : provas) {
            int nota = 0;
            for (int i = 0; i < 10; i++) {
                if (gabarito.getRespostaQuestao(i).equals(prova.getRespostaQuestao(i))) {
                    nota++;
                }
            }
        }
    }

}