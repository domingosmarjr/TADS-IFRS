// SUBPROBLEMA - VALIDADOR - TESTAGEM

// npm init -y

import { contaVogais, contaCaracteres } from "./strings.js";
import { teste } from './teste.js'

console.log('Oi')
teste()

// Encadeamento e Empilhamento de Funções
// Subproblemas

// String -> cadeia de caracteres
// ela é um tipo especializado de array (vector)
// 'tads' = ['t','a','d','s']

var curso = 'tads'
console.log(curso) //curso
console.log(curso.length) //4
console.log(curso[0])
console.log(curso.length - 1) //último índice
console.log(curso.charCodeAt(0)) //116

// Problema:
// Dada uma string sem acentos, saber quantidade de vogais dessa string

console.log(contaVogais('analise') == 4)

console.log(contaCaracteres('desenvolvimento','ev') == 5)
