package sistema_eventos.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgres {

    private String host;
    private String port;
    private String dbname;
    private String username;
    private String password;

    public ConexaoPostgres(String username, String password) {
        this.host = "localhost";
        this.port = "5432";
        this.dbname = "sistema_eventos";
        this.username = username;
        this.password = password;
    }

    public ConexaoPostgres() {
        
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://"+this.host+":"+this.port+"/"+this.dbname;
        try {
            return DriverManager.getConnection(url, username, password);        
        } catch (Exception e) {
            System.out.println("Erro.");
            return null;
        }
    }

}
