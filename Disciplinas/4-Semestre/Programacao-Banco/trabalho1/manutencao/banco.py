import psycopg2

def conectar():
    return psycopg2.connect(
        host="localhost",
        database="manutencao",
        user="postgres",
        password="postgres"
    )