// RECURSIVIDADE

function soma (num1, num2) {
    return num1 + num2
}

// Função Recursiva:
// é uma função que chama ela mesma
// uma chamada recursiva nunca pode ser infinita! (deve ter condição de saída)
function vaiSomando(pulo = 2, vezes = 10, base = 0) {
    base = soma(base, pulo)
    console.log(base)
    if (vezes > 1) vaiSomando(pulo, vezes - 1, base)
}

vaiSomando()
vaiSomando(3, 5)
// Módulo: documento que contem funções
// Funções: executam ações dentro do módulo

// Função Potência enquanto Função Recursiva
function potencia () {
    
}