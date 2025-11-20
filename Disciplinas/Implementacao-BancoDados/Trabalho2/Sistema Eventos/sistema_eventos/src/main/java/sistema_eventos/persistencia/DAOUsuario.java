package sistema_eventos.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistema_eventos.negocio.Usuario;

public class DAOUsuario {
    private Connection conexao;

    public DAOUsuario() throws SQLException {
        conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
    }

    public int inserir(String login, String senha, int tipo) {
        String sql = "INSERT INTO usuario (login, senha, tipo) VALUES (?, ?, ?) RETURNING id";
        int idGerado = -1;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            stmt.setInt(3, tipo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idGerado = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGerado;
    }

    public Usuario autenticar(String login, String senha) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getInt("tipo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean loginExiste(String login) {
        String sql = "SELECT 1 FROM usuario WHERE login = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
    
    public void atualizarUsuario(int usuarioId, String login, String senha) throws SQLException {
        String sql = "UPDATE usuario SET login = ?, senha = ? WHERE id = ?";

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, login);
            ps.setString(2, senha);
            ps.setInt(3, usuarioId);

            ps.executeUpdate();
        }
    }
    
    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setTipo(rs.getInt("tipo"));
                return u;
            }
            return null;
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection con = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }


}
