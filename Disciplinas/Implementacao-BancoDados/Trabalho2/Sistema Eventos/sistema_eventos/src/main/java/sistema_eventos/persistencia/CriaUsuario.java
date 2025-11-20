package sistema_eventos.persistencia;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class CriaUsuario {
    
    private String usuario;
    private String senha;
    private int tipo;

    public CriaUsuario(String usuario, String senha, int tipo) throws SQLException {
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
        Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
        try {
            String sqlUsuario = "CREATE USER " + usuario + " WITH PASSWORD '" + senha + "';";
            String sqlRole = "GRANT " + this.getTipoUsuario(tipo) + " TO " + this.getUsuario() + ";"; 

            Statement contexto = (Statement) conexao.createStatement();            
            contexto.execute(sqlUsuario);
            contexto.execute(sqlRole);

            System.out.println("Usuário criado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar usário.");
        } finally {
            conexao.close();
        }
    }

    public String getUsuario() { return usuario; }
    public String getSenha() { return senha; }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    private String getTipoUsuario(int tipo) {
        switch (tipo) {
            case 1 : return "admin";
            case 2 : return "organizador";
            case 3 : return "participante";
            case 4 : return "palestrante";
            default:
                throw new IllegalArgumentException("Dígito inválido.");
        }
    }

}
