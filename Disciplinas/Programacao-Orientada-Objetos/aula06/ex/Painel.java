package ex;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class Painel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        // g.setColor(Color.red);
        // g.fillRect(50,100,300,200);
        Quadrado q = new Quadrado(50, Color.blue);
        q.desenhar(200, 50, g);
        q.desenhar(300, 250, g);
        q.desenhar(100, 250, g);
        
        Retangulo r = new Retangulo(100, 50, Color.yellow);
        r.desenhar(400, 300, g);


    }
}