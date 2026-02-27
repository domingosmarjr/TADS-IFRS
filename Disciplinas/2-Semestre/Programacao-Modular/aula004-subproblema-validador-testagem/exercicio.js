// Resolver o seguinte problema, com funções em arquivos modulares. O enunciado é de um caso de uso concreto. Mas tente abstrair (generalizar).

// Considere um texto Markdown como 
// "#Título 1 \n
// Parágrafo com \n 
// texto em _itálico_ ou **negrito**"
// 
// Remover a marcação de itálico (ex.: _) de modo que o texto devolvido seja 
// "#Título 1 \n
// Parágrafo com \n
// texto em itálico ou **negrito**".


// Exceto o comprimento (.length), as demais funções já prontas de JavaScript não pode ser usadas.
// Considere se reunir com um (ou mais) colegas para discutir as possibilidades.

// percorrer a string e repetir os caracteres que devem ficar
// se encontrar o caracter específico, passar para o próximo

var texto = "#Título _de_ **algo**"
var texto2 = "#Título 1 \n Parágrafo com \n texto em _itálico_ ou **negrito**"

function removeChar(string, char) {
    var newString = "";
    for (let i = 0; i < string.length; i++) if(string[i] != char) newString += string[i]
    return newString
}

export function removeTitle(string) {
    return removeChar(string, '#')
}

export function removeBold(string) {
    return removeChar(string, '*')
}

export function removeItalic(string) {
    return removeChar(string, '_')
}

export function removeNewLine(string) {
    return removeChar(string,'\n')
}

console.log(removeChar(texto,'*') == '#Título _de_ algo')
console.log(removeChar(texto,'_') == '#Título de **algo**')
console.log(removeChar(texto,'#') == 'Título _de_ **algo**')
console.log(removeTitle("#TADS") == 'TADS')
console.log(removeBold('**CUIDADO**') == 'CUIDADO')
console.log(removeItalic('_Era_ _um_ _texto_') == 'Era um texto')
console.log(removeNewLine('Era\n uma') == 'Era uma')
console.log((removeTitle(removeBold(removeItalic(removeNewLine(texto2))))) == 'Título 1  Parágrafo com  texto em itálico ou negrito')