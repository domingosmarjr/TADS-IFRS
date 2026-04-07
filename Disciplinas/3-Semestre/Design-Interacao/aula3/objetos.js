let pessoa1 = {nome: "Fulano", telefone: "3333", idade:30 };
// alert(pessoa1.nome);
// alert(pessoa1["nome"]);
console.log(pessoa1.nome);
console.log(pessoa1["nome"]);

let func3 = {nome: "Fulaninho",
             endereco: {
                rua: "Av Brasil",
                cidade: "Rio Grande",
                estado: "RS"
             }
}
// alert(func3.endereco.cidade);
console.log(func3.endereco.cidade);

// ==================
// ATRIBUTOS
let lista = {nome: "Beltrano", produto: "Geladeira"};

lista.nome = "Fulano"; //altera o nome
lista["nome"] = "Fulano";
lista.nome = ""; //vazio

delete lista.nome; //deleta propriedade nome da lista

lista.preco = 12.00; //adiciona propriedade nova na lista
console.log(lista.preco);

// ==================
// MÉTODOS
let pera = {
    cor: "verde",
    forma: "redonda",
    gosto: 8,
    imprime: function () {
        console.log("Pêra");
        console.log(this.cor);
    }
}

pera.imprime();

// ==================
// THIS
var pessoa = {
    nome: "Fulano",
    sobrenome: "Silva",
    id: 556,

    nomeCompleto: function() {
        return this.nome + " " + this.sobrenome;
    }
}

console.log(pessoa.nomeCompleto());