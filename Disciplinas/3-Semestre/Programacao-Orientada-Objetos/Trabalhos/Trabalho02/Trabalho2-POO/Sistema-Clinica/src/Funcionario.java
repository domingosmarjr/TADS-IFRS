
abstract class Funcionario extends Pessoa{
    protected static int codFunc;
    protected String tipo;

    protected int getCodigo() { return this.codFunc; }

    protected String getTipo() {
        return this.tipo;
    }

    protected void proximoCodigo() {
        codFunc++;
    }
}
