// Janaiton
// Domingos

function max (a, b) {
    return (a > b) ? a : b;
}

function mmc (a, b) {
    for (i = 1; ; i++) {
        if ((i%a) === 0 && (i%b) === 0) return i
    }

}

function mdc (a, b) {
    var x = max(a,b)
    
    for (i = x; ; i--) {
        if ((a%i) === 0 && (b%i) === 0) {
            return i
        }
    }
}

var resp1 = mmc(3, 5)
var resp2 = mdc(15, 6)

console.log(resp1 === 15)
console.log(resp2 === 3)


