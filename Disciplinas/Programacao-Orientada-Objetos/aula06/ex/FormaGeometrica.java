package ex;

import java.awt.Color;

// Impossível instanciar abstrato
abstract class FormaGeometrica implements IDesenhavel {
    
    private int x, y;


    protected String nome;
    private Color cor;

    public String getNome() { return this.nome; }
    public Color getCor() { return cor; }

    abstract void setNome();

    public void setCor(Color cor) {
        this.cor = cor;
    }

    // cada forma geométrica deve implementar sua área
    abstract float calculoArea();

}
