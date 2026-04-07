let dados = [];
dados[0] = "João";
dados[1] = 123;
dados[2] = 32;

let dados1 = new Array("João", 123, 12);
let dados2 = ["João", 123, 12];

console.log(dados.length);

let pessoa = ["José", "Pedro", "Arthur"];
console.log(pessoa[1]);

// =====================
// MANIPULAÇÃO DE ARRAYS
let vetor = ["a", "b", "c"];

// Append
vetor.push("d","e");
console.log(vetor.length);

// Shift (retira o primeiro do vetor)
vetor.shift();
console.log(vetor.length);
console.log(vetor[0]);

// Unshift (adiciona no primeiro do vetor)
vetor.unshift("z");
console.log(vetor.length);
console.log(vetor[0]);

// Retira n elementos após a posição x
vetor.splice(1,2);
console.log(vetor.length);
console.log(vetor[1]);

// Adiciona n elementos após a posição x
vetor.splice(3, 0, "r", "s");
console.log(vetor.length);
console.log(vetor[1]);

// Retira último elemento do vetor
vetor.pop();
console.log(vetor.length);

// ===========================
// ARRAY DE OBJETO
let pessoas = [
    {firstName: "Fulano", lastName: "Silva", age: 35},
    {firstName: "Fulana", lastName: "Torres", age: 29}
];
console.log(pessoas[1].firstName);


//============
let myObject = {
    comandos: [
        {protocolo: "web", metodo: "post"},
        {protocolo: "web", metodo: "get"},
        {protocolo: "email", metodo: "post"}
    ]
}

console.log(myObject.comandos[0].metodo);
myObject.comandos[0].metodo = "new post"
myObject.comandos[3] = {"protocolo":"web", "metodo":"get"};