# venv\Scripts\activate 

from flask import Flask, render_template, request, redirect
from banco import conectar

app = Flask(__name__)
app.config['JSON_AS_ASCII'] = False

# Página 1 - Dashboard Sistema [0,5]
@app.route("/")
def dashboard():
    conexao = conectar()
    cur = conexao.cursor()

    cur.execute("SELECT * FROM dashboard_sistema();")
    dados = cur.fetchone()

    cur.close()
    conexao.close()

    return render_template("dashboard.html", dashboard=dados)

@app.route("/usuarios")
def usuarios():
    conexao = conectar()
    cur = conexao.cursor()

    cur.execute("SELECT * FROM list_usuarios();")
    dados = cur.fetchall()

    cur.close()
    conexao.close()

    return render_template("usuarios.html", usuarios=dados)

@app.route("/equipamentos")
def equipamentos():
    conexao = conectar()
    cur = conexao.cursor()

    cur.execute("SELECT * FROM listar_equipamentos();")
    dados = cur.fetchall()

    cur.close()
    conexao.close()

    return render_template("equipamentos.html", equipamentos=dados)

@app.route("/servicos")
def pagina_servicos():
    conexao = conectar()
    cur = conexao.cursor()

    cur.execute("SELECT * FROM listar_servicos();")
    dados = cur.fetchall()

    cur.close()
    conexao.close()

    return render_template("servicos.html", servicos=dados)

@app.route("/status", methods=["GET", "POST"])
def status():
    dados = []

    conexao = conectar()
    cur = conexao.cursor()

    cur.execute("SELECT id, titulo FROM servico;")
    servicos = cur.fetchall()

    if request.method == "POST":
        servico_id = request.form["servico_id"]

        cur.execute("SELECT * FROM listar_status(%s);", (servico_id,))
        dados = cur.fetchall()

    cur.close()
    conexao.close()

    return render_template("status.html", status=dados, servicos=servicos)

@app.route("/ranking")
def ranking():
    conexao = conectar()
    cur = conexao.cursor()

    cur.execute("SELECT* FROM ranking_tecnicos();")
    dados = cur.fetchall()

    cur.close()
    conexao.close()

    return render_template("ranking.html", ranking=dados)

@app.route("/servico/<int:id>")
def detalhe_servico(id):
    conexao = conectar()
    cur = conexao.cursor()

    cur.execute("SELECT titulo, descricao FROM servico WHERE id = %s;", (id,))
    servico = cur.fetchone()  # ✅ importante ()

    cur.execute("SELECT prioridade_servico(%s);", (id,))
    prioridade = cur.fetchone()[0]

    cur.close()
    conexao.close()

    return render_template("detalhe_servico.html", servico=servico, prioridade=prioridade)

@app.route("/novo_status", methods=["GET", "POST"])
def novo_status():
    conexao = conectar()
    cur = conexao.cursor()

    cur.execute("SELECT id, titulo FROM servico;")
    servicos = cur.fetchall()

    mensagem = None

    if request.method == "POST":
        servico_id = request.form["servico_id"]
        texto = request.form["mensagem"]
        cur.execute("CALL registrar_status_inteli(%s, %s);", (servico_id, texto))
        conexao.commit()

        mensagem = "Status registrado"

    cur.close()
    conexao.close()

    return render_template("novo_status.html", servicos=servicos, mensagem=mensagem)

if __name__ == "__main__":
    app.run(debug=True)