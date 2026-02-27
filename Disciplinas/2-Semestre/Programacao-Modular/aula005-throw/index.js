

var curso = 'tads'
//           0123

console.log(curso[0]) //t
console.log(curso[4]) //undefined

// Alterar uma String? NÃO!
// Strings são imutáveis!
// String é um array e é um OBJETO que vive em outro lugar da memória
// String é um objeto REFERENCIADO!
// São imutáveis porque são recicladas pela memória do JS
var texto = 'abc'
var texto = 'abc' + 'd' + 'e' + 'f'
// abc    | string 1
// abcd   | string 2
// abcde  | string 3
// abcdef | string 4


// curso[0] = 'T'

// Isso não é String, mas um Array
var aula = ['a', 'u', 'l', 'a']
console.log(aula)
aula[0] = 'A'
console.log(aula)

// Cada vez que contatena algo = gera uma String nova!
var curso2 = curso + '!' //Cria uma nova String!
console.log(curso)
console.log(curso2)

var curso3 = curso[0] + curso[1]
console.log(curso3)



