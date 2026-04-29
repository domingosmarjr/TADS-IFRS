class EditorCartao extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({ mode: "open" });
    }

    connectedCallback() {
        this.render();
        this.addEventos();
    }

    render() {
        this.shadowRoot.innerHTML = `
            <style>
                * {
                    font-family: Arial;
                    box-sizing: border-box;
                }

                .container {
                    display: flex;
                    gap: 20px;
                    width: 100%;
                }

                #editor {
                    border: 1px solid #ccc;
                    border-radius: 6px;
                    background: #f9f9f9;
                    padding: 15px;
                    flex: 1;
                    min-width: 250px;
                    display: flex;
                    flex-direction: column;
                    gap: 15px;
                }

                .campo {
                    display: flex;
                    flex-direction: column;
                    gap: 5px;
                }

                #card {
                    border: 1px solid black;
                    padding: 15px;
                    min-height: 220px;
                    flex: 2;
                }

                button {
                    padding: 5px;
                    cursor: pointer;
                }

                img {
                    max-width: 100%;
                }
                main {
                    margin: 20px 40px;
                }

                @media (max-width: 600px) {
                    main {
                        margin: 10px;
                    }
                }

                @media (max-width: 600px) {
                    .container {
                        flex-direction: column;
                    }
                }
            </style>

            <div class="container">
                <div id="editor">
                    <h3>Personalize seu cartão!</h3>

                    <div class="campo">
                        <label for="titulo">Título:</label>
                        <input type="text" id="titulo">
                        <button type="button" id="btnTitulo">Adicionar</button>
                    </div>
                    
                    <div class="campo">
                        <label for="texto">Texto:</label>
                        <input type="text" id="texto">
                        <button type="button" id="btnTexto">Adicionar</button>
                    </div>

                    <div class="campo">
                        <label for="imagem">Imagem:</label>
                        <input type="file" id="imagem">
                        <button type="button" id="btnImagem">Adicionar</button>
                    </div>

                    <div class="campo">
                        <label for="cor">Cor:</label>
                        <input type="color" id="cor">
                    </div>
                </div>

                <div id="card"></div>
            </div>
        `;
    }

    addEventos() {
        const shadow = this.shadowRoot;

        const card = shadow.getElementById("card");

        const inputTitulo = shadow.getElementById("titulo");
        const inputTexto = shadow.getElementById("texto");
        const inputImagem = shadow.getElementById("imagem");
        const inputCor = shadow.getElementById("cor");

        shadow.getElementById("btnTitulo").onclick = () => {
            const h2 = document.createElement("h2");
            h2.textContent = inputTitulo.value || "Título";
            card.appendChild(h2);
        };

        shadow.getElementById("btnTexto").onclick = () => {
            const p = document.createElement("p");
            p.textContent = inputTexto.value || "Texto";
            card.appendChild(p);
        };

        shadow.getElementById("btnImagem").onclick = () => {
            const file = inputImagem.files[0];
            if (!file) return;

            const img = document.createElement("img");
            img.alt = "Imagem Cartão.";
            
            const reader = new FileReader();
            reader.onload = e => img.src = e.target.result;
            reader.readAsDataURL(file);

            card.appendChild(img);
        };

        inputCor.addEventListener("input", () => {
            card.style.backgroundColor = inputCor.value;
        });

        // remover elemento ao clicar
        card.addEventListener("click", (e) => {
            if (e.target !== card) {
                e.target.remove();
            }
        });
    }
}

customElements.define("editor-cartao", EditorCartao);