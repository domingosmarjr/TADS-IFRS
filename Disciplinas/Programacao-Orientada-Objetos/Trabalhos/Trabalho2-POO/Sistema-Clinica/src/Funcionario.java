abstract class Funcionario extends Pessoa{
    protected  static int codFunc;

    protected int getCodigo() {
        return this.codFunc;
    }

    protected void proximoCodigo() {
        codFunc++;
    }
}
