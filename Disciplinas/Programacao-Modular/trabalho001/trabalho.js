//SUBSTRING
export function substring (string, inicio, tamanho = string.length) {
    
    if(typeof(string) != 'string') throw 'String inserida inválida.'
    if(typeof(inicio) != 'number') throw 'Inicio inserido inválido.'
    if(typeof(tamanho) != 'number') throw 'Tamanho inserido inválido.'
    if(inicio < 0) throw 'Início inserido não pode ser negativo ou valor inválido.'
    if(inicio > string.length) throw 'Início inserido não pode ser maior que a String.'
    if(tamanho > string.length) throw 'Tamanho inserido é maior que o disponível.'
    if(tamanho < 0) throw 'Tamanho inserido não pode ser negativo.'
    
    var nova = ''
    for (var i = inicio; i < tamanho; i++) nova += string[i]
    return nova
}

// LEFT TRIM
export function leftTrim(string) {

    if(typeof(string) != 'string') throw 'String inserida inválida.'
    
    var nova = ''
    for(var i = 0; i < string.length; i++) {
        if (string[i] != ' ') {
            nova = substring(string, i)
            break
        }
    }
    return nova
}

// RIGHT TRIM
export function rightTrim(string) {

    if(typeof(string) != 'string') throw 'String inserida inválida.'

    var nova = ''
    for(var i = string.length - 1; i >= 0; i--) {
        if(string[i] != ' '){
            nova = substring(string, 0, i+1)
            console.log(nova)
            break
        }
    }
    return nova

}

// TRIM
export function trim(string) {
    return (rightTrim(leftTrim(string)))
}

//HALF
export function half(string = '', metade = 0) {
    let nova = ''
    
    if (metade == 0) return string

    if (string.length % 2 == 0) { //STRING PAR
        if (metade == 1) nova = substring(string, 0, string.length/2)  
        if (metade == 2) nova = substring(string, string.length/2, string.length)
    } else if (string.length % 2 != 0) { //STRING IMPAR (precisa encontrar o char MEDIANA
        let mediana = medianaString(string)
        if (metade == 1) nova = substring(string, 0, string.length/2 - 1)
        if (metade == 2) nova = substring(string, mediana)
    }
    return nova
}

export function group() {

}

// CALCULAR A MEDIANA DE UMA STRING
function medianaString(string) {
    var mediana = -1
    for (let x = 0, y = string.length-1; x < string.length; x++, y--) {
        if (x == y) {
            mediana = x
            break
        }
    }
    return mediana
}