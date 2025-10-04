function soma(num1, num2) {
    var resp
    resp = num1 + num2
    return resp
}
// função recursiva: função que chama à ela mesma
// nunca é infinita: *deve* ter uma condição de saída

// base e cont são variáveis do módulo
// e compartilhados por todas as funções
// var base = 0 // efeito colateral (tendem a ser evitados)
// var cont = 0

// ESCOPO DA FUNÇÃO
function vaiSomando(pulo = 2, vezes = 10, base = 0) {
    base = soma(base, pulo)
    console.log(base)
    if (vezes > 1) 
        vaiSomando(pulo, vezes - 1, base)
}

vaiSomando()
vaiSomando(3, 5)

// while (1) {
//    console.log(soma(2, soma(soma(4, 9), 5)))
// }
