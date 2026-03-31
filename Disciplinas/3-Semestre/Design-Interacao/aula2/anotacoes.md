DOM - DOCUMENT OBJECT MODEL
>API de acesso e manipulação de documentos HTML
>Transforma todo documentl HTML em objetos com propriedades e me´todos
>Para DOM, tudo em um documento HTML é um nodo (node)
>Todo node é um objeto com propriedades e métodos
    - Documento inteiro é um nodo
    - Todos elementos são um nodos
    - Todos textos nos elementos são nodos
    - Todos atributos são nodos

    Exemplo:
        <html>
            <head>
                <title>DOM TUTORIAL</title>
            </head>
            <body>
                <h1>DOM</h1>
                <p>Texto</p>
            </body>
        </html>
    
    - <html> é o nodo rais (root)
    - todas tags são nodos

Árvore de Nodos:
Document
    <html> [root]
        <head>
            <title> text:"titulo"
        <body>
            <a> atribute: href text:"Link"
            <h1> text: "titulo"

    Element  -> html, head, title, body
    Atribute -> href
    Text     -> titulo, link, texto

----------------------------------------------
ACESSANDO NODOS - querySelector()
- método querySelector() retorna o primeiro elemento de um seletor CSS
- document.querySelector("#menu"); 
- retorna o elemento com id ="menu"

ACESSANDO TODOS NODOS - querySelectorAll()
- retorna todos os elementos de um seletor CSS
- node.document.querySelectorAll("p");
- documento.querySelector('#main').querySelectorAll("p");
    - retorna uma lista de nodos com <p> que estão dentro de id = main
    - retorna um array de nodes

PEGANDO ELEMENTO POR ID - getElementById()
- retorna o elemento com a ID especificada
- node.getElementById("id);
- similiar ao querySelector mas limitado a ids

PEGANDO ELEMENTO POR TAGNAME - getElementByTagName()
- retorna todos os elementos com o nome de tag especificado
- node.getElementsByTagName("tagname");
- document.getElementsByTagName("p");
- retorna lista dos elementos <p> vetor

ACESSANDO NODOS POR ATALHOS
- document.documentElement -> retorna <html>
- document.body -> retorna body

NODOS POSSUEM RELACIONAMENTOS (CHILD, PARENT, SIBLINGS)
- filhos (child)
- pai (parent)
- irmãos (siblings)

ACESSANDO PROPRIEDADES DE NODOS
- nodeName
    > especifica o nome dos nodos
    > para um elemento node é o nome da tag
    > para texto é sempre #text
- nodeValue
    > especifica o valor de um nodo
    > indefinido para elementos

- nodeType
    TIPO DE NODOS       NodeType
    Element             1
    Attribute           2
    Text                3
    Comment             8
    Document            9

EVENTOS E OUVINTES DE EVENTOS
- Eventos
    > fatos que ocorrem durante a interação do usuário com a página (clicar botões, selecionar caixas de texto, carregar páginas e etc.)
    > essencial para o JS executar códigos
    > JS reage a isso

- Event Listener
    > alarmes que detectam quando determinados eventos ocorrem e podem disparar ações quando estes eventos ocorrem.

    > Lista de Eventos
    - load -> detecta carga do documento (acessa a página)
    - unload -> detecta quando usuário sai da página
    - change -> quando o objetvo perde o foco e houve mudança de conteúdo (selecionar um item ou escrever um novo texto na caixa de texto)
    - blur -> quando o objeto perde o foco
    - focus -> quando o objetvo recebe o foo
    - click -> detecta quando o objeto recebe um clique
    - mouseover -> detecta quando o ponteiro do mouse passa por esse objeto
    - select -> detecta quando o objeto é selecionado
    - submit -> detecta quando um botão tipo Submit recebe um click (válido para form)
    