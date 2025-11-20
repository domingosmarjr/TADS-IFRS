package sistema_eventos.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema_eventos.negocio.Palestra;
import sistema_eventos.negocio.Palestrante;

public class DAOPalestrante {

    // LISTAR PALESTRANTES SIMPLES
    public List<Palestrante> listarPalestrantes() throws SQLException {
        List<Palestrante> vetPalestrantes = new ArrayList<>();
        
        String query = "SELECT id, nome, biografia, cpf FROM palestrante;";

        try (Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
             PreparedStatement instrucaoSQL = conexao.prepareStatement(query);
             ResultSet resultado = instrucaoSQL.executeQuery()) {

            while (resultado.next()) { 
                Palestrante palestrante = new Palestrante();
                palestrante.setId(resultado.getInt("id"));
                palestrante.setNome(resultado.getString("nome"));
                palestrante.setBiografia(resultado.getString("biografia"));
                palestrante.setCpf(resultado.getString("cpf"));
                vetPalestrantes.add(palestrante);
            }
        }

        return vetPalestrantes;
    }

    // QUANTIDADE DE PALESTRAS POR PALESTRANTE
    public List<Palestrante> quantidadePalestras() throws SQLException {
        List<Palestrante> vetQntPalestrante = new ArrayList<>();

        String query = """
            SELECT p.nome, COUNT(palestra_palestrante.palestra_id) AS total_palestras
            FROM palestrante p
            LEFT JOIN palestra_palestrante palestra_palestrante ON p.id = palestra_palestrante.palestrante_id
            GROUP BY p.nome
            ORDER BY total_palestras DESC;
        """;

        try (Connection conexao = new ConexaoPostgres().getConnection();
             PreparedStatement instrucaoSQL = conexao.prepareStatement(query);
             ResultSet resultado = instrucaoSQL.executeQuery()) {

            while (resultado.next()) {
                Palestrante palestrante = new Palestrante();
                palestrante.setNome(resultado.getString("nome"));
                palestrante.setTotalPalestras(resultado.getInt("total_palestras"));
                vetQntPalestrante.add(palestrante);
            }
        }

        return vetQntPalestrante;
    }

    // INSERIR
    public void inserir(Palestrante novoPalestrante) throws SQLException {
        String sql = "INSERT INTO palestrante (nome, email, cpf, usuario_id, usuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
             PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setString(1, novoPalestrante.getNome());
            pst.setString(2, novoPalestrante.getEmail());
            pst.setString(3, novoPalestrante.getCpf());
            pst.setInt(4, novoPalestrante.getUsuarioId());
            pst.setString(5, novoPalestrante.getUsuario());
            pst.executeUpdate();
        }
    }

    // LISTAR PALESTRAS POR EVENTO
    public List<Palestra> listarPorEvento(int eventoId) throws SQLException {
        List<Palestra> palestras = new ArrayList<>();

        String sql = """
            SELECT id, titulo, duracao, data_hora_inicio, evento_id
            FROM palestra
            WHERE evento_id = ?
            ORDER BY data_hora_inicio;
        """;

        try (Connection conexao = new ConexaoPostgres().getConnection();
             PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, eventoId);
            ResultSet resultado = pst.executeQuery();

            while (resultado.next()) {
                Palestra palestra = new Palestra();
                palestra.setId(resultado.getInt("id"));
                palestra.setTitulo(resultado.getString("titulo"));
                palestra.setDuracao(resultado.getInt("duracao"));
                palestra.setData(resultado.getTimestamp("data_hora_inicio"));
                palestra.setEventoId(resultado.getInt("evento_id"));

                palestras.add(palestra);
            }
        }

        return palestras;
    }

    // INSERIR RETORNANDO ID
    public int inserirRetornandoId(Palestrante novoPalestrante) throws SQLException {
        String sql = "INSERT INTO palestrante (nome, email, cpf, usuario_id) VALUES (?, ?, ?, ?) RETURNING id";

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
             PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setString(1, novoPalestrante.getNome());
            pst.setString(2, novoPalestrante.getEmail());
            pst.setString(3, novoPalestrante.getCpf());
            pst.setInt(4, novoPalestrante.getUsuarioId());

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Falha ao inserir palestrante: nenhum ID retornado.");
            }
        }
    }

    // LISTAR TODOS
    public List<Palestrante> listarTodos() throws SQLException {
        List<Palestrante> lista = new ArrayList<>();

        String sql = "SELECT id, nome, usuario, email, biografia, cpf, usuario_id FROM palestrante ORDER BY nome";

        try (Connection conn = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Palestrante p = new Palestrante();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));

                // AQUI ESTAVA O ERRO — usuario é STRING no banco!
                p.setUsuario(rs.getString("usuario"));

                p.setEmail(rs.getString("email"));
                p.setBiografia(rs.getString("biografia"));
                p.setCpf(rs.getString("cpf"));
                p.setUsuarioId(rs.getInt("usuario_id"));

                lista.add(p);
            }
        }

        return lista;
    }

    public List<Palestrante> listarPorPalestraId(int palestraId) throws SQLException {
        List<Palestrante> lista = new ArrayList<>();

        String sql = "SELECT pa.id, pa.nome, pa.email " +
                    "FROM palestrante pa " +
                    "JOIN palestra_palestrante pp ON pa.id = pp.palestrante_id " +
                    "WHERE pp.palestra_id = ?";

        try (Connection conn = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, palestraId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Palestrante p = new Palestrante();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setEmail(rs.getString("email"));
                    lista.add(p);
                }
            }
        }

        return lista;
    }

    public List<Palestrante> listarTodosComDetalhes() throws SQLException {
        List<Palestrante> lista = new ArrayList<>();

        String sql = "SELECT id, nome, usuario, email, biografia, cpf, usuario_id FROM palestrante ORDER BY nome";

        try (Connection conn = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Palestrante p = new Palestrante();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setUsuario(rs.getString("usuario"));
                p.setEmail(rs.getString("email"));
                p.setBiografia(rs.getString("biografia"));
                p.setCpf(rs.getString("cpf"));
                p.setUsuarioId(rs.getInt("usuario_id"));
                lista.add(p);
            }
        }

        return lista;
    }

    // LISTAR PALESTRAS POR PALESTRANTE
    public List<Palestra> listarPalestrasPorPalestrante(int palestranteId) throws SQLException {
        List<Palestra> palestras = new ArrayList<>();

        String sql = """
            SELECT p.id, p.titulo, p.duracao, p.data_hora_inicio, p.evento_id
            FROM palestra p
            JOIN palestra_palestrante pp ON p.id = pp.palestra_id
            WHERE pp.palestrante_id = ?
            ORDER BY p.data_hora_inicio;
        """;

        try (Connection conn = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, palestranteId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Palestra palestra = new Palestra();
                    palestra.setId(rs.getInt("id"));
                    palestra.setTitulo(rs.getString("titulo"));
                    palestra.setDuracao(rs.getInt("duracao"));
                    palestra.setData(rs.getTimestamp("data_hora_inicio"));
                    palestra.setEventoId(rs.getInt("evento_id"));
                    palestras.add(palestra);
                }
            }
        }

        return palestras;
    }

    // EXCLUIR PALESTRANTE
    public void excluir(int palestranteId) throws SQLException {
        String sql = "DELETE FROM palestrante WHERE id = ?";

        try (Connection conn = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, palestranteId);
            ps.executeUpdate();
        }
    }


    public Palestrante buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nome, usuario, email, biografia, cpf, usuario_id FROM palestrante WHERE id = ?";

        try (Connection conn = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Palestrante p = new Palestrante();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setUsuario(rs.getString("usuario"));
                    p.setEmail(rs.getString("email"));
                    p.setBiografia(rs.getString("biografia"));
                    p.setCpf(rs.getString("cpf"));
                    p.setUsuarioId(rs.getInt("usuario_id"));
                    return p;
                }
            }
        }
        return null;
    }
    
    public void atualizarPalestranteComUsuario(Palestrante palestrante, String senha, DAOUsuario daoUsuario) throws SQLException {
        String sql = """
            UPDATE palestrante 
            SET nome = ?, usuario = ?, email = ?, cpf = ?, biografia = ?
            WHERE id = ?
        """;

        try (Connection conn = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, palestrante.getNome());
            ps.setString(2, palestrante.getUsuario());
            ps.setString(3, palestrante.getEmail());
            ps.setString(4, palestrante.getCpf());
            ps.setString(5, palestrante.getBiografia());
            ps.setInt(6, palestrante.getId());

            ps.executeUpdate();
        }
        daoUsuario.atualizarUsuario(palestrante.getUsuarioId(), palestrante.getUsuario(), senha);
    }
}
