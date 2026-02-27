package persistencia;

// RESPONSABILIDADE DELA = CONECTAR NO BANCO

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {
    // String host = "localhost";
    // String dbname = "sistema_eventos";
    // String port = "7070";
    // String username = "postgres";
    // String passwrod = "postgres";
    // String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbname;

    private String host;
    private String dbname;
    private String port;
    private String username;
    private String password;
    private String url;


    public ConexaoPostgreSQL() {
        host = "localhost";
        dbname = "sistema_eventos";
        port = "5432";
        username = "postgres";
        password = "postgres";
        url = "jdbc:postgresql://" + host + ":" + port + "/" + dbname + "?sslmode=disable";

    }

    // getConexo() -> ABRE UMA CONEXÃO PARA MIM
    public Connection getConexao() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}