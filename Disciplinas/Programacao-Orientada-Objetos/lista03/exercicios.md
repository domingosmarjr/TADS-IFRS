ASSOCIAÇÃO As classes existem de maneira independente; Uma classe "usa" a outra; - referencia

AGREGAÇÃO Uma classe é parte da outra Uma classe que representa o todo e outra(s) classe(s) que compoe essa classe são a parte - Carro - Pneu / Roda - Motor - Chassi

COMPOSIÇÃO Todo - Parte (mais forte) as partes não deveriam existir fora do todo
--------------------
Lista de Exercícios 03
Exercício 1: Criar um Exemplo de Associação

Crie as classes Biblioteca e Livro. A Biblioteca deve ter uma lista de Livros. Implemente um método para adicionar livros à biblioteca e um método para listar os livros disponíveis.

Exercício 2: Exemplo de Agregação

Crie as classes Escola e Professor. A Escola pode ter vários Professores. Implemente um método para adicionar professores à escola e exibir seus nomes. Os professores podem existir independentemente da escola.

Exercício 3: Exemplo de Composição
Crie as classes Casa e Comodo. Uma Casa é composta por vários Comodos, como sala, cozinha, e quarto. Cada cômodo é criado quando a casa é criada, e se a casa for destruída, os comodos devem ser destruídos.