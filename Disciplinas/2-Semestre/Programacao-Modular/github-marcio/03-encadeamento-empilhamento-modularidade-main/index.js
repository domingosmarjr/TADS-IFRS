import { mmc } from './mmc.js'
import { max } from './max.js'

//(function qualquer() {
//    qualquer();
//})();

// funções (ou métodos)
// podem ser encadeadas ou empilhadas
// ENCADEAR: a saída de uma função serve de entrada
// para outra função.
// console.log(max(34, 89))

console.log(mmc(13, 39))

// acoplamento de posição
console.log(max(mmc(4, 5), mmc(6, 8)))

// encadeamento: a saída de um é entrada de outra
console.log(max(65, max(23, 90)))
// encadeamento
console.log(mmc(4, mmc(5, 6)))

