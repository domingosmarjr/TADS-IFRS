// Fail-Safe: resistente a falhas (tentar manter operando)
// Fail-Fast: encontrou falhas, então para
function substring(str, ini = 0, tam) {
    if (typeof(str) != 'string') {
        throw 'O primeiro parâmetro deve ser uma string'
    }
    if (ini < 0) {
        throw 'Início não pode ser negativo: ' + ini
    }
    if (tam == undefined) {
        tam = str.length - ini
    }
    var nova = ''
    for (var i = ini; i < ini + tam; i++) {
        nova += str[i]
    }
    return nova
}

// ini = 3
// tam = 4
// 0123456789
// tecnologia

try { // tentar
    console.log(substring('tecnologia', -8, 4))
} catch (erro) { // pegar e tratar a exceção
    console.log('Ocorreu um problema', erro)
}

// console.log(substring(3, 'tecnologia', 4))

console.log(substring('tecnologia', 3, 4)) // nolo

console.log(substring('tecnologia', 3)) // nologia
console.log(substring('tecnologia')) // nologia

// TRUE e FALSE
// TRUTHY e FALSY
/*
var x = undefined
if (x) {
    console.log('true')
} else {
    console.log('false')
}
*/


// forma estabelecida de validar é com Exceções (Exception)
// JS: chama exceções de erros: throw (lançar, atirar)

/*
console.log(substring(23, 45, 65, 23,45,45,56,56,56))

function soma(x, y, z) { // args[0], args[1], args[2] 
    return arguments[0] + arguments[1]
}

console.log(soma(45, 55))
*/

