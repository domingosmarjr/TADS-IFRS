// Function Declaration
function declarationMedia(n1, n2, n3) {
    let media = (n1 + n2 + n3) / 3;
    return media;
 }

// Function expression (anônima)
let expressionMedia = function (n1, n2, n3) {
    let media = (n1 + n2 + n3) / 3;
    return media;
    };

// Arrow Function
let arrowMedia = (n1, n2, n3) => { 
    let media = (n1 + n2 + n3) / 3
    return media
    };

//Chamando as funções
document.querySelector("div").innerHTML=
    `${declarationMedia(3, 6, 12)} <br>
    ${expressionMedia(3, 6, 9)}  <br>
    ${arrowMedia(7, 3, 2)}`;
