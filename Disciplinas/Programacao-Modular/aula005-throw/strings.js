// JS recebe argumentos, mesmo não estando declarados na função
// e nisso, o arguments é uma array

// function substring() {
//     console.log(arguments)
// }
// 
// function soma() {
//     return arguments[0] + arguments[1]
// }
// 
// console.log(soma(45, 65))
// 
// -------------------------------------------------------------
//                       define um tamnho padrão
function substring (str, ini = 0, tam) { //API (fica imutável depois que cria [para produção])
    var nova = ''

    if (typeof(str) != 'string') throw 'O primeiro parâmetro deve ser uma string'
    if (ini < 0) throw 'Início não pode ser negativo: ' + ini
    if (tam < 0) throw 'Tamanho não pode ser negativo: ' + tam
    if (tam == undefined) tam = str.length - ini

    for (var i = ini; i < ini+tam; i++) {
        nova += str[i]
    }

    return nova
}

// ini = 3
// tam = 4
// 0123456789
// tecnologia
console.log(substring('tecnologia', 3, 4))
console.log(substring('tecnologia', /*-3*/ 2, 4))

// FORMA DE VALIDAR ERROS É COM EXCEÇÕES (Exception)
// JS se chama "exceções de erros"
// "Throw" -> lançar, atirar

// Fail Safe: resistente a falhas (tentar manter operando o sistema)
// Fail Fast: encontrou falhas, então para

// TENTAR PEGAR UM ERRO (THROW)
// TRY (IF) CATCH (ELSE)
// Se funciona, se não pega erro!
// "Te lancei um erro, tu pega aí!"

try { //TENTAR
    console.log(substring('tecnologia', -8, 4))
} catch (erro) { //PEGAR
    console.log('Ocorreu um problema: ', erro)
}

console.log(substring('tecnologia', 3))

// TRUE e FALSE
// TRUTHY e FALSY (verídico e inverídico)

console.log(substring('tecnologia'))
