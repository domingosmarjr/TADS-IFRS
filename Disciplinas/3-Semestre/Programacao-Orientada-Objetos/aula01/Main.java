import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    
        // Declaração de uma variável do tipo Borracha
        Borracha b1;

        List<Borracha> borrachas = new ArrayList<Borracha>();

        b1 = new Borracha();
        // b1.cor = "VERDE";
        // b1.marca = "MERCUR";
        // b1.tamanhoEmCm3 = 24;
        b1.resistencia = 2;

        b1.apagar("QUERO APAGAR ESTE CONTEXTO PARA VER COM QUE TAMANHO A BORRACHA VAI FICAR");
        System.out.println(b1.verificarTamanho());

    }
}