function max (a, b) {
    return (a > b) ? a : b;
}

function min (a, b) {
    return (a < b) ? a : b;
}


console.log(max(3,2) === 3)
console.log(max(2,4) === 4)
console.log(max(2,2) === 2) 

console.log(min(3,2) === 2)
console.log(min(2,4) === 2)
console.log(min(2,2) === 2)

console.log(max("tads",10))
console.log(max('tads','instituto'))
console.log(min('tads','ifrs'))

// typeof -> mostra qual o tipo do dado
console.log(typeof('tads')) // string
console.log(typeof(3)) //number

var texto = 'tads'
var n = texto.length

console.log(n)

function maxx (a, b) {
    if (typeof(a) === 'number' && typeof(b) === 'number') return (a > b) ? a : b
    if (typeof(a) === 'string' && typeof(b) === 'string') return (a.length > b.length) ? a : b;
}

console.log(maxx('tads','ifrss') === 'ifrss')
console.log(maxx('ifrss','tads') === 'ifrss')
console.log(maxx(1,2) === 2)
console.log(maxx(2,1) === 2)
console.log(maxx(5,5) === 5)
console.log(maxx('tads','modular') === 'modular')
console.log(maxx(2.0009, 2) === 2.0009)
