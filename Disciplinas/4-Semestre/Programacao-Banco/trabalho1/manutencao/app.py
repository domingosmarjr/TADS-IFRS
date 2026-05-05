from flask import Flask, render_template
from banco import conectar

app = Flask(__name__)

@app.route("/")
def index():
    return render_template("index.html")

@app.route("/usuarios")
def usuarios():
    conexao = conectar()
    cur = conexao.cursor()

    cur.execute("SELECT * FROM list_usuarios();")
    dados = cur.fetchall()

    cur.close()
    conexao.close()

    return render_template("usuarios.html", usuarios=dados)

if __name__ == "__main__":
    app.run(debug=True)