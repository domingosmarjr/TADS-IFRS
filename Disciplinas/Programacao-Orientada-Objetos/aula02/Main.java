

public class Main {
    public static void Main(String args[]) {
    
        // Carro carro = new Carro();
        Carro carro = new Carro("TRACKER", 2020);
        // carro.ano = 2020;
        // carro.modelo = "TRACKER";
        // carro.cor = "CINZA";
        // carro.ano = 2025;


        // Cliente pediu que a Tracker fosse cinza.
        // Carro carro2 = new Carro("TRACKER", 2020, "CINZA");
        carro.pintar("CINZA");

        System.out.println(carro.informacoes());

        Carros virtus = new Carro();
        virtus.modelo = "VIRTUS";
        virtus.ano = 2022;
        virtus.cor = "AZUL";

    }
}