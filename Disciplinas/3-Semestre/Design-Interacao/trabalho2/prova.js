// PROVA
class Prova extends HTMLElement {
    constructor() {  
        super();
        // Adiciona isolando do global
        this.attachShadow({ mode: "open"});
        
        // Bloco de perguntas (poderia puxar de um banco)
        this.perguntas = [
            { questao: "JS é acrônimo de?", respostas: ["Python", "Java", "JavaScript"], certa: 2 },
            { questao: "Qual dessas linguagens é mais atual?", respostas: ["Assembly", "Go", "Ruby"], certa: 1},
            { questao: "Quem é o criador do Linux?", respostas: ["Bill", "Steve", "Linus"], certa: 2}
            // Questão de teste para adição:
            // ,{ questao: "Quais digitos representam binários?", respostas: ["6 e 7", "8 e 9", "1 e 0"], certa: 2}
        ];

        this.render();
    }
    
    render () {
        this.shadowRoot.innerHTML = `
            <style>
                * {
                    font-family: Arial, sans-serif;
                    box-sizing: border-box;
                }

                .container {
                    max-width: 600px;
                    margin: 20px auto;
                    border: 1px solid #ccc;
                    padding: 20px;
                    background: #fff;
                }

                h2 {
                    text-align: center;
                    margin-bottom: 20px;
                }

                .questao {
                    border: 1px solid #ddd;
                    padding: 10px;
                    margin-bottom: 15px;
                }

                .questao p {
                    margin-bottom: 8px;
                    font-weight: bold;
                }

                label {
                    display: block;
                    margin-bottom: 5px;
                }

                button {
                    margin-top: 10px;
                    padding: 8px 12px;
                    border: 1px solid #333;
                    background: #eee;
                    cursor: pointer;
                }

                button:hover {
                    background: #ddd;
                }

                #resultado {
                    margin-top: 15px;
                    padding: 10px;
                    border: 1px solid #ccc;
                }

                .correta {
                    color: green;
                }

                .errada {
                    color: red;
                }
            </style>            

            <div class="container">
                <h2>Prova de Info</h2>
                <form id="formulario"></form>
                <button type="button" id="corrigir">Corrigir</button>
                <div id="resultado"></div>
            </div>
        `;

        const formulario = this.shadowRoot.getElementById("formulario");
        this.perguntas.forEach((questao, i) => {
            const div = document.createElement("div");
            let html = `<p>${questao.questao}</p>`;
            questao.respostas.forEach((resposta, n) => {
                html += ` <label><input type="radio" name="questao${i}" value="${n}">${resposta}</label><br>`;
            });
            div.innerHTML = html;
            formulario.appendChild(div);
        });

        this.shadowRoot.getElementById("corrigir").addEventListener("click", () => this.corrigir());

    }

    corrigir() {
        let nota = 0;
        let resultado = "";

        this.perguntas.forEach((questao, i) => {
            const resposta = this.shadowRoot.querySelector(`input[name="questao${i}"]:checked`);
            if (!resposta) {
                resultado += `<p>Questão não respondida</p>`;
                return;
            }

            const valorResposta = parseInt(resposta.value);
            if (valorResposta === questao.certa) {
                nota++;
                resultado += `<p>Questão correta - R: ${questao.respostas[valorResposta]}</p>`;
            } else {
                resultado += `<p>Questão incorreta - Alternativa: ${questao.respostas[valorResposta]} - Resposta Certa: ${questao.respostas[questao.certa]}</p>`;
            }
        });

        resultado += `<h3>Nota: ${nota}/${this.perguntas.length}</h3>`;
        resultado += `<button id="refazer">Refazer</button>`;

        this.shadowRoot.getElementById("resultado").innerHTML = resultado;
        this.shadowRoot.getElementById("refazer").addEventListener("click", () => this.render());
    };

}
customElements.define("prova-1", Prova);