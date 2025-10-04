public class Borracha {

    // ALERTA COM SUPER MODELOS
    // - Especificar os atributos dentro do escopo do sistema e do objeto.

    // ATRIBUTOS
    private float tamanhoEmCm3 = 24;
    int resistencia;
    String marca;
    String cor;

    // Ações da classe e acesso aos dados
    // MÉTODOS
    public void apagar (String texto) {
        int textLength = texto.length();
        tamanhoEmCm3 = tamanhoEmCm3 - textLength * 0.01f / resistencia;
    }

    public float verificarTamanho() {
        return tamanhoEmCm3;
    }



}