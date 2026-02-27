
console.log("Hello World")
console.log("Aula");

// Declarando variável de JS
var x = 5
var nome = 'Domingos'
var LetraGrande = 'ABC' //não afeta tratando em Classe (não usado)
var nomeCompleto = 'Isso aqui é camelcase'
// nomeCompleto -> camel case
// NomeCompleto -> Pascal case
// nome_completo -> snake case

// Print na tela
console.log(x)
console.log(nome)

// Descobrir o tipo do dado dinâmico do JS
console.log(typeof(x))
console.log(typeof(nome))

// As variáveis em JS não são, elas estão num tipo!!
x = 'TADS'
console.log(x)
console.log(typeof(x))

// Concatenação
x = x + 'SSS'
console.log(x)

// String contamina o seu somatório!
// Quando soma String com algo = String
// Quando diminui uma String e um Numero = numero
var a = '10'
var b = 2

console.log(a + b) //102
console.log(typeof(a+b)) //String
console.log(a - b) //8

// Condicionais
if (b > 0){
    console.log('b é maior que 0: ' + b)
}

// For
for (var i = 0; i < 10; i++) {
    console.log('i é ' + i)
    if (i == 8) {
        break
    }
}

// While
var u = 9
while (u > 0) {
    console.log('maior')
    u--
}

// Vetor
var idades = [22, 48, 24, 36, 65]

