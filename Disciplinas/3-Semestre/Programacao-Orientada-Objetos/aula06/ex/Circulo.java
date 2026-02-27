package ex;
import java.awt.Color;
import java.awt.Graphics;

public class Circulo extends FormaGeometrica {
    
    private float raio;

    Circulo (float r, Color c) {
        this.raio = r;
        setCor(c);
        setNome();
    }

    @Override
    public void desenhar(int x, int y, Graphics g) {
        // TODO Auto-generated method stub
        
    }

    @Override
    float calculoArea() {
        return (3.14f * (raio*raio));
    }

    @Override
    void setNome() {
        this.nome = "CIRCULO";
    }

    
}
