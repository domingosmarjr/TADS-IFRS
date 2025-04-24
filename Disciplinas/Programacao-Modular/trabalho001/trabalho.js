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
export function half(string = '', metade = 1) {
    
    let nova = ''
        
    if (typeof(string) == 'string') {
        if (metade == 0) return string
        if (string.length % 2 == 0) { //STRING PAR
            if (metade == 1) nova = substring(string, 0, string.length/2)  
            if (metade == 2) nova = substring(string, string.length/2, string.length)
        } else if (string.length % 2 != 0) { //STRING IMPAR (precisa encontrar o char MEDIANA
            let mediana = medianaString(string)
            if (metade == 1) nova = substring(string, 0, string.length/2 - 1)
            if (metade == 2) nova = substring(string, mediana)
        }    
    } else if (typeof(string) == 'number') {
        let metadeInteiro = arrendonda(string/2)
        let decimal = string % 1
        let resultado = (metade == 1) ? metadeInteiro : 1 + metadeInteiro + decimal
        // Tive que pesquisar para corrigir ponto flutuante de half(25.9,2)
        // pois sempre estava resultando 13.8999 ao invés de 13.9
        const precision = 1e10
        return (parseInt(resultado * precision))/precision
    }
    return nova
}

export function group(texto, divisor, parte) {

    if (typeof(texto) != 'string') throw 'Texto inserido não é string.'
    if (divisor <= 0 || parte < 1 || parte > 3) return ''




    
    /*
    tecnologia, 3, 1

    tecnologia
    0123456789

    tec nol ogia 
    012 345 6789


    */

}







// --- FUNÇÕES AUXILIARES ---

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

// ARRENDONDAMENTO MANUAL
function arrendonda(num) {
    return num >= 0 ? num - (num % 1) : num - (num % 1) - 1
}