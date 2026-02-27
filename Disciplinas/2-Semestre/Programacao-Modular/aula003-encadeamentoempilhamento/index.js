// Encadeamento, Empilhamento, Modularidade

// Todo código tem acomplamento

// let -> variável local minuciosa
// var -> variável global acessível
// --------------------------------

// IMPORT
// import { [o que importar] } from "caminhoDoArquivo"
// Criar arquivo JSON padrão de pacote
// terminal na pasta = npm init -y
import { mmc } from './mmc.js'
import { max } from './max.js'

console.log(mmc(4,5)) //20
console.log(max(34,89)) //89

// ACOPLAMENTO/ENCADEAR: a saída de uma função serve de entrada para outra função
// Acoplamento: quando o resultado de uma função é parâmetro já de outro função
// Encadeamento! Uma atrás da outra!
console.log(max(mmc(4,5),mmc(6,8)))
console.log(mmc(4, mmc(5, mmc(6, 7))))

// EMPILHAMENTO
// Empilhamento: quando uma função, dentro de si, chama outra função.
console.log(mmc(13, 39))
