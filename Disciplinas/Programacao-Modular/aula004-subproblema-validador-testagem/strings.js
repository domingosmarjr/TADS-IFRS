// ABSTRAÇÃO
// 
// Pensar num problema generalizado, ao invés de um problema especializado.
// 

// subproblema
function contem (conjunto, elemento) {
    for (var i = 0; i < conjunto.length; i++) {
        if(conjunto[i] == elemento) return true
    }
    return false;
}

// problema específico
export function contaVogais(string) {
    var cont = 0
    for (let i = 0; i < string.length; i++) {
        if(string[i] == 'a' || string[i] == 'e' || string[i] == 'i' || string[i] == 'o' || string[i] == 'u') cont++
    }
    return cont;
}

// Abstração -> caracteres
// problema genérico
export function contaCaracteres(string, caracteres) {
    var cont = 0
    for (var i = 0; i < string.length; i++) {
        if(contem(caracteres, string[i])) cont++
    }
    return cont
}