
public class Forno {
    
    // A1 - Definir os atributos como privados (evitar modificações externas) -> encapsular
    int volume;
    int tensao;
    int potencia;
    int largura;
    int altura;
    int profundidade;

    Forno(int vol, int ten, int pot, int lar, int alt, int pro) {
        if(vol >= 10 && vol <= 90){
            if(ten == 110 || ten == 220) {
                if (pot >= 1000 && pot <= 4500) {
                    if(lar > 0 && lar < 80){
                        if(alt > 0 && alt < 65) {
                            if (pro > 0 && pro < 65) {
                                this.volume = vol;
                                this.tensao = ten;
                                this.potencia = pot;
                                this.largura = lar;
                                this.altura = alt;
                                this.profundidade = pro;
                            } else{
                                throw new IllegalArgumentException("Profundidade inválida.");
                            }
                        } else {
                            throw new IllegalArgumentException("Altura inválida.");
                        }
                    } else {
                        throw new IllegalArgumentException("Largura inválida.");
                    }
                } else {
                    throw new IllegalArgumentException("Potência inválida.");
                }
            } else {
                throw new IllegalArgumentException("Tensão inválida.");
            }
        } else {
            throw new IllegalArgumentException("Volume inválido.");
        }
    }
    // A2 - Criar métodos internos de validação de parâmetros, ao invés de realizar dentro do construtor.

}
