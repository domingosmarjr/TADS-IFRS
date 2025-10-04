// index.js

//           0123
var curso = 'tads'
console.log(curso[0]) // t
console.log(curso[4]) // undefined

// alterar uma string? NÃO -- strings são IMUTÁVEIS
// curso[0] = 'T'

var aula = ['a', 'u', 'l', 'a']
console.log(aula)
aula[0] = 'A'
console.log(aula)

var curso2 = curso + '!' + '!' + '!' // uma nova string
console.log(curso2)
console.log(curso)

var curso3 = curso[0] + curso[1]
console.log(curso3)