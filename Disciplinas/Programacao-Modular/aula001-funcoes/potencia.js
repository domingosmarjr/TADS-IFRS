/**
 * Retorna o resultado de uma potência de números positivos > 0.
 * @param {*} base Número inteiro base do cálculo.
 * @param {*} expoente Número inteiro do expoente.
 * @returns O resultado de uma potência entre base e expoente.
 */
function potencia(base, expoente) {
    var calculo = base;
    for (var i = expoente; i > 1; i--) {
      calculo = base * calculo;
    }
    return calculo;
}

var result = potencia(2,8)
console.log(result === 256);

result = potencia (5,9)
console.log(result === 1953125)

console.log(potencia(2,3) === 8);
console.log(potencia(2.3,5.85) === 148.03588899999994);
console.log(potencia(-2,4) === 16);
console.log(potencia(2,-4) === 2);
console.log(potencia(-2,-2) === -2);
