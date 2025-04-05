// Toda definição/declaração tem um ESCOPO

// export -> tornar função visível fora do próprio arquivo
// Se a função exporta, o outro lado, que vai usar ela, importa!
// Não se exporta tudo! Tudo que é exportado se torna público (info sensíveis)
// Por padrão se exporta NADA! -> "O que eu preciso exportar?"

import { min } from './min.js'
import { max } from './max.js'

export function mmc(n1, n2) {
    var menor = min(n1,n2)
    var maior = max(n1,n2)
    for (var i = maior ; ; i += maior) {
        if(i%menor == 0) return i;
    }
}
