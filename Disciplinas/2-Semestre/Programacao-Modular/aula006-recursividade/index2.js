// Iterativa (Loop/Laço)
// Recursiva

function potencia (base, expoente = 0) {
    var resp = 1
    // Clássico formato usando o 'for'
    for (var i = 0; i < expoente; i++) resp = resp * base
    return resp
}

function potenciaRecursiva (base, exp = 0) {
    // Case Base da Recursão (quando para)
    // 2^0 = 1
    // 
    // 2^1 = 2^0 * 2 = 2
    // 2^2 = 2^1 * 2 = 4
    // 2^3 = 2^2 * 2 = 8

    if (exp == 0) return 1
    return potenciaRecursiva(base, exp - 1) * base
}


console.log(potencia(2,8)) // 2**8 = 256
console.log(potencia(2,8) == 256)

console.log(potenciaRecursiva(2,4))
console.log(potenciaRecursiva(2,4) == 16)