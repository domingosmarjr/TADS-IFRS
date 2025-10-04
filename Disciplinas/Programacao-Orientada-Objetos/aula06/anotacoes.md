
Ditado de POO:
-"Programe para Interfaces e não para Classes Concretas"

HERANÇA:
    Herança permite reutilizar atributos e métodos de classes "pai"
    estendendo o comportamento.

INTERFACE:
    Definir contratos (assinatura de métodos)
        nome do método + parâmetros + o que ele retorna
    Se usa INTERFACE para FAZER alguma coisa
    Se usa HERANÇA quando se quer SER alguma coisa.

    Interface em JAVA começa com I -> IDirigir.java
    interface IDirigir{ }

    Ps: interface permite agrupar classes diferentes na mesma lista.

HERANÇA (SER) X INTERFACE(FAZER):

    PESSOA -> FUNCIONARIO
    PAI -> FILHO (FUNCIONARIO É UMA PESSOA)

    PESSOA -> DIRIGIR
    (DANDO A CAPACIDADE DE DIRIGIR PARA A PESSOA)
    DIRIGIR É ALGO QUE A PESSOA FAZ!
        LOGO -> DIRIGIR = INTERFACE
        PESSOA IMPLEMENTA A INTERFACE

