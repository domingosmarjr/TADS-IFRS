// iterativa (loop/laço)
// recursiva
// (2 ^ 0) = 1 (caso base (base case))
// (2 ^ 1) = (2 ^ 0) * 2
// (2 ^ 2) = (2 ^ 1) * 2
// (2 ^ 3) = (2 ^ 2) * 2
// (2 ^ 4) = (2 ^ 3) * 2
// 1 * 2 * 2 * 2 * 2

function pot(base, exp = 0) {
    if (exp == 0) return 1 // caso base
    var resp = pot(base, exp - 1)
    return resp * base
}

console.log(pot(2, 4)) // 16

function potencia(base, exp = 0) { // 1, 2, 3
    var resp = 1
    
    // iterativo (iteração => volta)
    for (var i = 0; i < exp; i++) {
        resp = resp * base
    }
    return resp
}


//console.log(potencia(2, 8)) // 2 ** 8 = 256
//console.log(potencia(2, 8) == 256) // true