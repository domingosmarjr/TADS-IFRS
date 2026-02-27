public class Livro {
    private String titulo;
    private String autor;
    private boolean disponivel = true;

    public Livro(String t, String a) {
        this.titulo = t;
        this.autor = a;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel() {
        if(disponivel) disponivel = false;
        else disponivel = true;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor;
    }
}