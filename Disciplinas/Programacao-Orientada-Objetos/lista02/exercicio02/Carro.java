public class Carro {
    private  String marca;
    private  String modelo;
    private  int ano;

    Carro () { }

    // Métodos

    // toString
    @Override
    public String toString() {
        return "Marca: " + marca + "\nModelo: " + modelo + "\nAno: " + ano;
    }

    public String getMarca(){
        return marca;
    }

    public String getModelo(){
        return modelo;
    }

    public int getAno(){
        return ano;
    }

    public void setMarca(String m) {
        marca = m;
    }

    public void setModelo(String m) {
        modelo = m;
    }

    public void setAno(int n) {
        if (n>=1885) ano = n;
        else throw new IllegalArgumentException("Ano inválido.");
    }


    

}