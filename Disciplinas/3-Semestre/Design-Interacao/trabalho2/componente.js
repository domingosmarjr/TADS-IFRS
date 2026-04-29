// NOVO HEADER
class NovoHeader extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({ mode: "open"});
        this.shadowRoot.innerHTML = `
            <style>
                header {
                    background: linear-gradient(135deg, #4b6cb7, #182848);
                    color: white;
                    padding: 20px;
                    text-align: center;
                    font-family: Arial, sans-serif;
                    border-width: 1px 1px 0px 1px;
                    border-style: solid;
                    border-color: black;
                }

                h1 {
                    margin: 0;
                    font-size: 24px;
                }

                h2 {
                    margin: 5px 0;
                    font-size: 16px;
                    font-weight: normal;
                    opacity: 0.9;
                }

                h3 {
                    margin: 0;
                    font-size: 14px;
                    opacity: 0.7;
                }
                main {
                    margin: 20px 40px;
                }

                @media (max-width: 600px) {
                    main {
                        margin: 10px;
                    }
                }
            </style>
            <header>
                <h1>Design de Interação</h1>
                <h2>Domingos Martins Jr. - 2023017853</h2>
            </header>
        `;
    }
}
customElements.define("novo-header", NovoHeader);

// NOVO NAV
class NovoNav extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({ mode: "open"});
        this.shadowRoot.innerHTML = `
            <style>
                * {
                }

                nav {
                    background: #2f3640;
                    font-family: Arial, sans-serif;
                    border-width: 1px 1px 0px 1px;
                    border-style: solid;
                    border-color: black;

                }

                ul {
                    display: flex;
                    justify-content: center;
                    list-style: none;
                    margin: 0;
                    padding: 0;
                }

                li {
                    padding: 15px 25px;
                    color: white;
                    cursor: pointer;
                    transition: 0.3s;
                }

                li:hover {
                    background: #40739e;
                }
                a {
                    text-decoration: none;   /* remove sublinhado */
                    color: white;            /* mesma cor do li */
                    display: block;          /* ocupa toda área do li */
                    width: 100%;
                    height: 100%;
                }

                @media (max-width: 600px) {
                    ul {
                        flex-direction: column;
                        align-items: center;
                    }

                    li {
                        width: 100%;
                        text-align: center;
                        border-top: 1px solid #555;
                    }
                }
            </style>
            <nav>
                <ul>
                    <li><a href="./index.html">Trabalho I</a></li>
                    <li><a href="./trabalho2.html">Trabalho II</a></li>
                    <li><a href="./template.html">Trabalho II - Template</a></li>
                </ul>
            </nav>
        `;
    }
}
customElements.define("novo-nav", NovoNav);

// NOVO FOOTER
class NovoFooter extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({ mode: "open"});
        this.shadowRoot.innerHTML = `
            <style>
            
                footer {
                    background: #2f3640;
                    color: #ccc;
                    text-align: center;
                    padding: 15px;
                    font-family: Arial, sans-serif;
                    font-size: 14px;
                    margin-top: 0px;
                    border-width: 0px 1px 1px 1px;
                    border-style: solid;
                    border-color: black;

                }

                span {
                    color: white;
                    font-weight: bold;
                }
            </style>
            <footer>
                <span>Domingos</span>
            </footer>
        `;
    }
}
customElements.define("novo-footer", NovoFooter);

