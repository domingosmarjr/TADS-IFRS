package sistema_eventos.persistencia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistema_eventos.negocio.Palestra;
import sistema_eventos.negocio.Palestrante;
import sistema_eventos.persistencia.ConexaoPostgres;

public class DAOPalestra {

    public void cadastrarPalestra(String titulo, int duracao, Timestamp data, int evento_id, String[] palestrantes) throws SQLException {
        Connection conexao = new ConexaoPostgres("psotgres","postgres").getConnection();
        
        try {
            conexao.setAutoCommit(false);
            String query = "INSERT INTO palestra (titulo, duracao, data_hora_inicio, evento_id) VALUES (?, ?, ?, ?) RETURNING id";
            PreparedStatement instrucaoSQL = conexao.prepareStatement(query);
            // instrucaoSQL.setInt(1, id);
            instrucaoSQL.setString(1, titulo);
            instrucaoSQL.setInt(2, duracao);
            instrucaoSQL.setTimestamp(3, data);
            instrucaoSQL.setInt(4, evento_id);

            ResultSet resultado = instrucaoSQL.executeQuery();
            resultado.next();
            int palestraId = resultado.getInt("id");

            String queryPP = "INSERT INTO palestra_palestrante (palestra_id, palestrante_id) VALUES (?, ?)";
            PreparedStatement instrucaoSQLPP = conexao.prepareStatement(queryPP);

            for (String idStr : palestrantes) {
                int palestranteId = Integer.parseInt(idStr);
                instrucaoSQLPP.setInt(1, palestraId);
                instrucaoSQLPP.setInt(2, palestranteId);
                instrucaoSQLPP.executeUpdate();
            }
            conexao.commit();
        } catch (SQLException e) {
            conexao.rollback();
            throw e;
        } finally {
            conexao.setAutoCommit(true);
            conexao.close();
        }
    }

    // LISTAR POR EVENTO
    public List<Palestra> listarPorEvento(int eventoId) throws SQLException {
        String sql = "SELECT id, titulo, duracao, data_hora_inicio, evento_id FROM palestra WHERE evento_id = ? ORDER BY data_hora_inicio ASC";
        List<Palestra> palestras = new ArrayList<>();

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
             PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, eventoId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Palestra p = new Palestra();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDuracao(rs.getInt("duracao"));
                p.setData(rs.getTimestamp("data_hora_inicio"));
                p.setEventoId(rs.getInt("evento_id"));
                palestras.add(p);
            }
        }

        return palestras;
    }

    // BUSCAR PALESTRA POR ID
    public Palestra buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, titulo, duracao, data_hora_inicio, evento_id FROM palestra WHERE id = ?";
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
             PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Palestra p = new Palestra();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDuracao(rs.getInt("duracao"));
                p.setData(rs.getTimestamp("data_hora_inicio"));
                p.setEventoId(rs.getInt("evento_id"));
                return p;
            } else {
                return null;
            }
        }
    }


    public List<Palestrante> listarPalestrantesPorPalestra(int palestraId) {
        List<Palestrante> palestrantes = new ArrayList<>();
        String sql = """
            SELECT p.id AS palestrante_id, p.nome, p.email, p.cpf, p.biografia
            FROM palestrante p
            JOIN palestra_palestrante pp ON p.id = pp.palestrante_id
            WHERE pp.palestra_id = ?
        """;

        try (Connection con = new ConexaoPostgres("postgres","postgres").getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, palestraId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Palestrante pal = new Palestrante();
                pal.setId(rs.getInt("palestrante_id"));
                pal.setNome(rs.getString("nome"));
                pal.setEmail(rs.getString("email"));
                pal.setCpf(rs.getString("cpf"));
                pal.setBiografia(rs.getString("biografia"));
                palestrantes.add(pal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return palestrantes;
    }




    public void inserir(int eventoId, String titulo, int duracao) throws SQLException {
        String sql = "INSERT INTO palestra (titulo, duracao, evento_id) VALUES (?, ?, ?)";

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, titulo);
            ps.setInt(2, duracao);
            ps.setInt(3, eventoId);

            ps.executeUpdate();
        }
    }

    public void deletar(int palestraId) throws SQLException {
        String sql = "DELETE FROM palestra WHERE id = ?";
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
             PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, palestraId);
            pst.executeUpdate();
        }
    }

    public void adicionarPalestrante(int palestraId, int palestranteId) throws SQLException {
        String sql = "INSERT INTO palestra_palestrante (palestra_id, palestrante_id) VALUES (?, ?)";
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
             PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, palestraId);
            pst.setInt(2, palestranteId);
            pst.executeUpdate();
        }
    }
    
    public void removerPalestrante(int palestraId, int palestranteId) throws SQLException {
        String sql = "DELETE FROM palestra_palestrante WHERE palestra_id = ? AND palestrante_id = ?";
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
             PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, palestraId);
            pst.setInt(2, palestranteId);
            pst.executeUpdate();
        }
    }

    public int inserirRetornandoId(Palestrante novoPalestrante) throws SQLException {
        String sql = "INSERT INTO palestrante (nome, email, cpf, usuario) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setString(1, novoPalestrante.getNome());
            pst.setString(2, novoPalestrante.getEmail());
            pst.setString(3, novoPalestrante.getCpf());
            pst.setString(4, novoPalestrante.getUsuario());

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Falha ao inserir palestrante, nenhum ID retornado.");
            }
        }
    }

    public int inserirRetornandoId(int eventoId, String titulo, int duracao, Timestamp dataHora) throws SQLException {
            String sql = "INSERT INTO palestra (titulo, duracao, evento_id, data_hora_inicio) VALUES (?, ?, ?, ?) RETURNING id";
            try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
                PreparedStatement pst = conexao.prepareStatement(sql)) {

                pst.setString(1, titulo);
                pst.setInt(2, duracao);
                pst.setInt(3, eventoId);
                pst.setTimestamp(4, dataHora);

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new SQLException("Erro ao inserir palestra: ID não retornado.");
                }
            }
        }

    public void atualizar(Palestra p) throws SQLException {
        String sql = "UPDATE palestra SET titulo = ?, duracao = ?, data_hora_inicio = ?, evento_id = ? WHERE id = ?";
        try (Connection conn = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getTitulo());
            ps.setInt(2, p.getDuracao());
            ps.setTimestamp(3, p.getData());
            ps.setInt(4, p.getEventoId());
            ps.setInt(5, p.getId());

            ps.executeUpdate();
        }
    }

    public void inserir(Palestra p) throws SQLException {
        String sql = "INSERT INTO palestra (titulo, duracao, data_hora_inicio, evento_id) VALUES (?, ?, ?, ?)";

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, p.getTitulo());
            ps.setInt(2, p.getDuracao());
            ps.setTimestamp(3, p.getData());
            ps.setInt(4, p.getEventoId());

            ps.executeUpdate();
        }
    }
    public List<Palestrante> listarTodosPalestrantes() throws SQLException {
        List<Palestrante> lista = new ArrayList<>();

        String sql = "SELECT id, nome, email, cpf, biografia FROM palestrante ORDER BY nome";

        try (Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Palestrante p = new Palestrante();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                p.setCpf(rs.getString("cpf"));
                p.setBiografia(rs.getString("biografia"));
                lista.add(p);
            }
        }

        return lista;
    }

    
    public Palestra buscarPorIdComDetalhes(int id) {
        String sql = """
            SELECT id, titulo, duracao, data_hora_inicio, evento_id
            FROM palestra
            WHERE id = ?
        """;

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Palestra p = new Palestra();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDuracao(rs.getInt("duracao"));
                p.setData(rs.getTimestamp("data_hora_inicio"));
                p.setEventoId(rs.getInt("evento_id"));

                // 🔥 Buscar palestrantes dessa palestra
                List<Palestrante> palestrantes = listarPalestrantesPorPalestra(id);
                p.setPalestrantes(palestrantes);

                return p;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Palestra> listarTodasComDetalhes() throws SQLException {
        List<Palestra> lista = new ArrayList<>();

        String sql = """
            SELECT 
                p.id AS palestra_id,
                p.titulo,
                p.duracao,
                p.data_hora_inicio,
                e.id AS evento_id,
                e.nome AS evento_nome
            FROM palestra p
            JOIN evento e ON p.evento_id = e.id
            ORDER BY p.data_hora_inicio;
        """;

        try (Connection conn = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Palestra palestra = new Palestra();
                palestra.setId(rs.getInt("palestra_id"));
                palestra.setTitulo(rs.getString("titulo"));
                palestra.setDuracao(rs.getInt("duracao"));

                Timestamp ts = rs.getTimestamp("data_hora_inicio");
                palestra.setData(ts);
                if (ts != null) {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
                    palestra.setDataHora(sdf.format(ts));
                }

                palestra.setEventoId(rs.getInt("evento_id"));
                palestra.setEventoNome(rs.getString("evento_nome"));

                lista.add(palestra);
            }
        }

        return lista;
    }

    public void removerTodosPalestrantes(int palestraId) throws SQLException {
        String sql = "DELETE FROM palestra_palestrante WHERE palestra_id = ?";
        try (Connection conn = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, palestraId);
            ps.executeUpdate();
        }
    }

    
    public List<Palestra> listarTodasPorPalestrante(int palestranteId) throws SQLException {
        List<Palestra> lista = new ArrayList<>();
        String sql = """
            SELECT p.id, p.titulo, p.duracao, p.data_hora_inicio, p.evento_id
            FROM palestra p
            JOIN palestra_palestrante pp ON p.id = pp.palestra_id
            WHERE pp.palestrante_id = ?
            ORDER BY p.data_hora_inicio
        """;

        try (Connection conn = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, palestranteId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Palestra p = new Palestra();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDuracao(rs.getInt("duracao"));
                p.setData(rs.getTimestamp("data_hora_inicio"));
                p.setEventoId(rs.getInt("evento_id"));
                lista.add(p);
            }
        }
        return lista;
    }

}
