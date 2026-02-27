import { min } from './min.js'
import { max } from './max.js'
// toda definição/declaração tem um ESCOPO (por arquivo)
// se exporta tudo? não
// tudo que é exportado se torna público
// por padrão exporta nada

export function mmc(n1, n2) {
    var menor = min(n1, n2)
    var maior = max(n1, n2)
    for (var i = maior; ; i += maior) {
        if (i % menor == 0) return i
    }
}

export var x = 5
