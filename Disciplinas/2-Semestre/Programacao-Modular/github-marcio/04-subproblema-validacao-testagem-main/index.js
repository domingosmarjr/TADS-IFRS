import { teste } from './teste.js'
import { contaVogais } from './strings.js'

console.log(contaVogais('analise')) // 4
console.log(contaVogais('analise') == 4) // true

// vscode, atom, sublime text,
// CTRL+P = abrir um arquivo existente
// CTRL+SHIFT+P = executar um comando
console.log('sempre executar: npm init -y')
console.log('para criar um projeto')
teste()

// encadeamento e empilhamento de funções
// subproblemas (monobloco, monolito)

// string: cadeia de caracteres (corda de caracteres)
// uma string é um tipo especializado de array (vetor)
// 'tads' => ['t', 'a', 'd', 's']

          // 0123
var curso = 'tads'

for (var i = 0; i < curso.length; i++) {
    console.log(i, 'caractere:', curso[i])
    if (curso[i] == 'a') {
        console.log('Encontrei uma vogal!')
    }
}

console.log(curso) // tads
console.log(curso.length) // comprimento: 4
console.log(curso[0]) // t
console.log(curso[3]) // s
console.log(curso[curso.length - 1]) // último índice

console.log('🌦️')
console.log('🌦️'.length) // bytes
console.log('🌦️'[0])

// Problema: dada uma string (sem acentos) saber
// a quantidade de vogais desta.

