// function nome parênteses chaves
// (parametro1, parametro2, parametroN)
// contrato: (toda função)
// inicio <= fim     10      5


/**
 * Retorna um número aleatório dentro do intervalo de início e fim.
 * 
 * @param {*} inicio número inteiro indicando o início do intervalo
 * @param {*} fim número inteiro maior ou igual ao início que indica o fim do intervalo
 * @returns Um número aleatório entre o início e fim inclusive, ex.: (5..7), pode retornar 5, 6 ou 7, ou retorna `undefined` se o fim for menor que o início
 */
function aleatorio(inicio, fim) { // tem dois parâmetros
    if (inicio <= fim) {
        var dif = fim - inicio + 1
        console.log(dif)
        var rand = Math.random() * dif
        console.log(rand)
        var inteiro = Math.floor(rand)
        console.log(inteiro)
        var resp = inteiro + inicio
        return resp
    }
}

console.log(aleatorio(5, 10)) // 5..10
console.log(aleatorio(10, 5)) // undefined

console.log(aleatorio(7, 10))
/*
var inicio = 12
var fim = 16
var resp = aleatorio(inicio, fim)
console.log(resp) // [12, 13, 14, 15, 16] 

console.log(aleatorio(3, 9))
*/
