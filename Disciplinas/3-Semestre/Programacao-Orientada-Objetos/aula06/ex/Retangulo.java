package ex;
import java.awt.Color;
import java.awt.Graphics;

public class Retangulo extends FormaGeometrica {

    private float lado;
    private float altura;

    public Retangulo(float l, float a, Color c){
        this.lado = l;
        this.altura = a;
        setCor(c);
        setNome();
    }

    @Override
    void setNome() {
        this.nome = "RETANGULO";
    }

    @Override
    float calculoArea() {
        return lado * altura;
    }

    @Override
    public void desenhar(int x, int y, Graphics g) {
        g.setColor(this.getCor());
        g.fillRect(x, y, x, y);

    }

}