public class Pessoa {

    private String nome;
    private int idade;
    private double altura;

    Pessoa (String nome, int idade, double altura) {
        this.nome = nome;
        this.idade = idade;
        this.altura = altura;
    }

    public String exibirInformacoes() {
        return ("Nome: " + nome + "\nIdade: " + idade + "\nAltura: "+  altura);
    }

    public void alterarIdade(int novaIdade){
        if(novaIdade >= 0 && novaIdade <= 100) idade = novaIdade;
    }

}

/*
#### **Exercício 1: Criando uma Classe Simples**
Crie uma classe chamada `Pessoa` que tenha os seguintes atributos:
- Nome (String)
- Idade (int)
- Altura (double)

Implemente um método para exibir as informações da pessoa e um método para alterar o valor da idade.
*/