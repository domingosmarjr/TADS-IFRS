console.log('Ok');

console.log(Math.random())

//--- OBJETO MATH
console.log(Math);

// Potência (igual java)
console.log(Math.pow(2,2));

// Random
console.log(Math.random)
console.log(Math.random())
Math.floor(Math.random() * 3) + 5

// Math.floor(n.n)
Math.floor(8.3) //8

// DOCUMENTAÇÃO DO JAVASCRIPT
// Mozila -> responsável por manter a documentação do JS
// Mozila Development Network - MDN

// CLASSE NUMBER
Number

// Declaração Global de Variável
x = Math.random() * 13
Math.floor(x)

x = Math.floor(Math.random()*13)
console.log(x)

// -----------------------------------------------

var inicio = 12
var fim = 16
var dif = fim - inicio
var resp = Math.floor((Math.random() * dif ) + inicio)
// 0 * 4 = 0
// 0 + 12 =
// 0.9 * 4 = 3.6
// 3.6 + 12 = 15.6
console.log(resp) //[12 13 14 15 16]

var a = 3
var b = 9
var c = Math.floor(Math.random() * (b - a + 1)) + a
console.log(c)


// ---- FUNÇÃO NO JAVASCRIPT

// Estrutra base da função:
// function nome () {...}

function aleatorio (inicio, fim) {
    var dif = fim - inicio
    var resp = Math.floor(Math.random() * dif) + inicio
    return resp
}

