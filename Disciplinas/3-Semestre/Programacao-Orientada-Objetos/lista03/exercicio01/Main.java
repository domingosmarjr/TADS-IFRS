public class Main {
    public static void main(String args[]) {
        System.out.println("Oi");

        // Crie as classes Biblioteca e Livro. A Biblioteca deve ter uma lista de Livros. Implemente um método para adicionar livros à biblioteca e um método para listar os livros disponíveis.

        Livro l1 = new Livro("Azincourt", "Bernard Conrwell");
        Livro l2 = new Livro ("Assassin's Creed Renascença", "Oliver Bowden");
        Livro l3 = new Livro ("Sapiens","Harari");
        Livro l4 = new                  Livro("Piratas no Brasil", "Jean Marcel");

        System.out.println(l1.getTitulo() == "Azincourt");
        System.out.println(l2.getAutor() == "Oliver Bowden");

        // System.out.println(l3);


        Biblioteca b1 = new Biblioteca("História");
        b1.adicionarLivro(l3);
        b1.adicionarLivro(l4);

        System.out.println(b1.listarLivros());

        l3.setDisponivel();
        System.out.println(b1.listarLivros());

        l3.setDisponivel();
        System.out.println(b1.listarLivros());
        

    }
}