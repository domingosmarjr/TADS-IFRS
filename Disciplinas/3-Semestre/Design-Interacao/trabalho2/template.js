class LayoutPagina extends HTMLElement {
    constructor() {
        super();

        const template = document.getElementById("layout-template");

        const shadow = this.attachShadow({ mode: "open"});

        shadow.appendChild(template.content.cloneNode(true));
    }
}
customElements.define("layout-pagina", LayoutPagina);