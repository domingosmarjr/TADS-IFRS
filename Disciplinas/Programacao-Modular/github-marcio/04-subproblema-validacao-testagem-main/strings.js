// contém em [32, 34, 78] o elemento 32
// contém em "tads" o elemento s

// função agnóstica: depende de duas abstrações
// conjunto (de quê?)
// elemento (que elemento?)
// conjunto = "tads" (4)
//               ^   
// elemento = "d"

// Single Responsibility Principle
// Princípio da Responsabilidade Única

// subproblema
function contem(conjunto, elemento) {
    for (var i = 0; i < conjunto.length; i++) {
        if (conjunto[i] == elemento) {
            return true
        }
    }
    return false
}

// problema genérico
// abstração: caracteres 
export function contaCaracteres(str, caracteres) {
    var cont = 0
    for (var i = 0; i < str.length; i++) {
        // str[i]
        if (contem(caracteres, str[i])) {
            cont++
        }
    }
    return cont
}

export function contaEspacos(str) {
    return contaCaracteres(str, ' ')
}

// problema específico
export function contaVogais(str) {
    return contaCaracteres(str, 'aeiou')
}

// ABSTRAÇÃO: pensar num problema generalizado em vez
// de especializado.

export function contaNeM(str) {
    var cont = 0
    for (var i = 0; i < str.length; i++) {
        switch (str[i]) { // fallback
            case 'n':
            case 'm': cont++;
        }
    }
    return cont
}

console.log(contaVogais('analise'))

// contar os n e m de uma string

console.log(contaCaracteres('desenvolvimento', '?!')) // 5