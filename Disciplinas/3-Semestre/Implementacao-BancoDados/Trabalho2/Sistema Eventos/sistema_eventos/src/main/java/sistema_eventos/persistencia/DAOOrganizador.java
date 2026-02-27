package sistema_eventos.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema_eventos.negocio.Evento;
import sistema_eventos.negocio.Organizador;
import sistema_eventos.negocio.Participante;

public class DAOOrganizador {

    // INSERIR ORGANIZADOR
    public void inserir(Organizador novoOrganizador) throws SQLException{
        String sql = "INSERT INTO organizador (nome, email, cpf, usuario_id) VALUES (?, ?, ?, ?)";
        Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
        PreparedStatement pst = conexao.prepareStatement(sql);
        pst.setString(1, novoOrganizador.getNome());
        pst.setString(2, novoOrganizador.getEmail());
        pst.setString(3, novoOrganizador.getCpf());
        pst.setInt(4, novoOrganizador.getUsuarioId());
        pst.executeUpdate();
        pst.close();
    }

    public Organizador buscarUsuarioId(int usuarioId) throws SQLException {
        String sql = "SELECT * FROM organizador WHERE usuario_id = ?";
        try (
            Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, usuarioId);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                Organizador org = new Organizador();
                org.setId(rs.getInt("id"));
                org.setNome(rs.getString("nome"));
                org.setCpf(rs.getString("cpf"));
                org.setEmail(rs.getString("email"));
                org.setUsuarioId(usuarioId);
                return org;
            }
            return null;
        }
    }

    public List<Organizador> listarTodos() throws SQLException {
        String sql = "SELECT id, nome FROM organizador ORDER BY nome";
        List<Organizador> lista = new ArrayList<>();

        try (
            Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()
        ) {
            while (rs.next()) {
                Organizador org = new Organizador();
                org.setId(rs.getInt("id"));
                org.setNome(rs.getString("nome"));
                lista.add(org);
            }
        }

        return lista;
    }

    public Organizador buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM organizador WHERE id = ?";
        try (
            Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql)
        ) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Organizador org = new Organizador();
                org.setId(rs.getInt("id"));
                org.setNome(rs.getString("nome"));
                org.setCpf(rs.getString("cpf"));
                org.setEmail(rs.getString("email"));
                org.setUsuarioId(rs.getInt("usuario_id"));
                return org;
            }
            return null;
        }
    }

    // DELETAR ORGANIZADOR
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM organizador WHERE id = ?";

        try (
            Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql)
        ) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }

    public List<Organizador> listarTodosComDetalhes() throws SQLException {
        String sql = "SELECT id, nome, cpf, email, usuario_id FROM organizador ORDER BY nome";
        List<Organizador> lista = new ArrayList<>();

        try (
            Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()
        ) {
            while (rs.next()) {
                Organizador org = new Organizador();
                org.setId(rs.getInt("id"));
                org.setNome(rs.getString("nome"));
                org.setCpf(rs.getString("cpf"));
                org.setEmail(rs.getString("email"));
                org.setUsuarioId(rs.getInt("usuario_id"));
                lista.add(org);
            }
        }

        return lista;
    }

    public Organizador buscarPorIdComEventos(int id) throws SQLException {
            Organizador org = null;

            // 1️⃣ Buscar dados do organizador
            String sqlOrg = "SELECT * FROM organizador WHERE id = ?";
            try (
                Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
                PreparedStatement psOrg = conexao.prepareStatement(sqlOrg)
            ) {
                psOrg.setInt(1, id);
                ResultSet rsOrg = psOrg.executeQuery();

                if (rsOrg.next()) {
                    org = new Organizador();
                    org.setId(rsOrg.getInt("id"));
                    org.setNome(rsOrg.getString("nome"));
                    org.setCpf(rsOrg.getString("cpf"));
                    org.setEmail(rsOrg.getString("email"));
                    org.setNascimento(rsOrg.getDate("data_nascimento"));
                    org.setUsuarioId(rsOrg.getInt("usuario_id"));
                } else {
                    return null;
                }
            }
            String sqlEventos = "SELECT * FROM evento WHERE organizador_id = ? ORDER BY data_inicio";
            try (
                Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
                PreparedStatement psEventos = conexao.prepareStatement(sqlEventos)
            ) {
                psEventos.setInt(1, id);
                ResultSet rsEventos = psEventos.executeQuery();

                List<Evento> eventos = new ArrayList<>();
                while (rsEventos.next()) {
                    Evento e = new Evento();
                    e.setId(rsEventos.getInt("id"));
                    e.setNome(rsEventos.getString("nome"));
                    e.setData_inicio(rsEventos.getDate("data_inicio"));
                    e.setHora_inicio(rsEventos.getTime("hora_inicio"));
                    e.setData_fim(rsEventos.getDate("data_fim"));
                    e.setHora_fim(rsEventos.getTime("hora_fim"));
                    e.setLocal(rsEventos.getString("local"));
                    e.setDetalhes(rsEventos.getString("detalhes"));
                    e.setImagemBanner(rsEventos.getBytes("imagem_banner"));
                    eventos.add(e);
                }

                org.setEventos(eventos);
            }

            return org;
        }

    public void atualizar(Organizador org) throws SQLException {
        String sql = "UPDATE organizador SET nome = ?, cpf = ?, email = ?, data_nascimento = ? WHERE id = ?";
        
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            
            ps.setString(1, org.getNome());
            ps.setString(2, org.getCpf());
            ps.setString(3, org.getEmail());
            ps.setDate(4, org.getNascimento());
            ps.setInt(5, org.getId());

            ps.executeUpdate();
        }
    }

}
