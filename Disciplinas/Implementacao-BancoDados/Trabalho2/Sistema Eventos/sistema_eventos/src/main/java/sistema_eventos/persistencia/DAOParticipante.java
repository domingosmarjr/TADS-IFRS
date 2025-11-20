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
import sistema_eventos.negocio.Usuario;

public class DAOParticipante {


    public Participante buscarPorCPF(String cpf) throws SQLException {
        Connection conexao = new ConexaoPostgres().getConnection();
        String query = "SELECT cpf, nome, email FROM participante WHERE cpf = ?";
        PreparedStatement instrucaoSQL = conexao.prepareStatement(query);
        instrucaoSQL.setString(1, cpf);
        ResultSet resultado = instrucaoSQL.executeQuery();
        
        Participante pessoa = new Participante();
        if(resultado.next()) {
            pessoa.setNome(resultado.getString("nome"));
            pessoa.setCpf(resultado.getString("cpf"));
            pessoa.setEmail(resultado.getString("email"));
        }
        conexao.close();
        return pessoa;
    }

    public Participante buscarUsuarioId(int usuario_id) throws SQLException {
        String sql = "SELECT * FROM participante WHERE usuario_id = ?";
        Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, usuario_id);
        ResultSet resultado = statement.executeQuery();

        if(resultado.next()) {
            Participante participante = new Participante();

            participante.setId(resultado.getInt("id"));
            participante.setNome(resultado.getString("nome"));
            participante.setCpf(resultado.getString("cpf"));
            participante.setEmail(resultado.getString("email"));
            participante.setNascimento(resultado.getDate("data_nascimento"));

            participante.setUsuarioId(resultado.getInt("usuario_id"));

            return participante;
        }
        return null;
    }


    // INSERIR PARTICIPANTE
    public void inserir(Participante novoParticipante) throws SQLException{
        String sql = "INSERT INTO participante (nome, email, cpf, data_nascimento, usuario_id) VALUES (?, ?, ?, ?, ?)";
        Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
        PreparedStatement pst = conexao.prepareStatement(sql);
        pst.setString(1, novoParticipante.getNome());
        pst.setString(2, novoParticipante.getEmail());
        pst.setString(3, novoParticipante.getCpf());
        pst.setDate(4, novoParticipante.getNascimento());
        pst.setInt(5, novoParticipante.getUsuarioId());
        pst.executeUpdate();
        pst.close();
    }

    // ATUALIZAR DADOS DE PARTICIPANTE
    public void atualizar(Participante p) throws SQLException {
        String sql = "UPDATE participante SET nome = ?, email = ?, cpf = ?, data_nascimento = ? WHERE usuario_id = ?";
        Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
        PreparedStatement ps = conexao.prepareStatement(sql);

        ps.setString(1, p.getNome());
        ps.setString(2, p.getEmail());
        ps.setString(3, p.getCpf());
        ps.setDate(4, p.getNascimento());
        ps.setInt(5, p.getUsuarioId());

        ps.executeUpdate();
        ps.close();
    }

    public List<Evento> listarEventosInscritos(int participanteId) throws SQLException {
        List<Evento> lista = new ArrayList<>();
        String sql = """
            SELECT e.id, e.nome, e.data_inicio, e.hora_inicio,
                e.data_fim, e.hora_fim, e.local, e.detalhes, e.imagem_banner
            FROM evento e
            INNER JOIN inscricao i ON e.id = i.evento_id
            WHERE i.participante_id = ?
            ORDER BY e.data_inicio
        """;
        Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, participanteId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Evento e = new Evento();
            e.setId(rs.getInt("id"));
            e.setNome(rs.getString("nome"));
            e.setData_inicio(rs.getDate("data_inicio"));
            e.setHora_inicio(rs.getTime("hora_inicio"));
            e.setData_fim(rs.getDate("data_fim"));
            e.setHora_fim(rs.getTime("hora_fim"));
            e.setLocal(rs.getString("local"));
            e.setDetalhes(rs.getString("detalhes"));
            e.setImagemBanner(rs.getBytes("imagem_banner"));
            lista.add(e);
        }
        conexao.close();
        return lista;
    }

    // LISTAR EVENTOS NÃO INSCRITOS
    public List<Evento> listarEventosNaoInscritos(int participanteId) throws SQLException{
        List<Evento> lista = new ArrayList<>();
        String sql = """
                SELECT evento.id, evento.nome, evento.data_inicio, evento.hora_inicio,
                evento.data_fim, evento.hora_fim, evento.local, evento.detalhes, evento.imagem_banner
                FROM evento
                WHERE evento.id NOT IN (
                    SELECT evento_id FROM inscricao WHERE participante_id = ?
                )
                ORDER BY evento.data_inicio
                """;
        Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, participanteId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Evento e = new Evento();
            e.setId(rs.getInt("id"));
            e.setNome(rs.getString("nome"));
            e.setData_inicio(rs.getDate("data_inicio"));
            e.setHora_inicio(rs.getTime("hora_inicio"));
            e.setData_fim(rs.getDate("data_fim"));
            e.setHora_fim(rs.getTime("hora_fim"));
            e.setLocal(rs.getString("local"));
            e.setDetalhes(rs.getString("detalhes"));
            e.setImagemBanner(rs.getBytes("imagem_banner"));
            lista.add(e);
        }
        conexao.close();
        return lista;
    }

    // VERIFICA SE PARTICIPANTE ESTÁ INSCRITO EM EVENTO
    public boolean estaInscrito(int participanteId, int eventoId) throws SQLException {
        String sql = """
            SELECT 1 FROM inscricao
            WHERE participante_id = ? AND evento_id = ?
        """;

        Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
        PreparedStatement ps = conexao.prepareStatement(sql);

        ps.setInt(1, participanteId);
        ps.setInt(2, eventoId);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public void inscrever(int participanteId, int eventoId) throws SQLException {
        String sql = """
            INSERT INTO inscricao (participante_id, evento_id, valor, pago)
            VALUES (?, ?, 0, false)
        """;

        Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, participanteId);
        ps.setInt(2, eventoId);
        ps.executeUpdate();
    }

    // LISTAR PARTICIPANTES DE UM EVENTO
    public List<Participante> listarPorEvento(int eventoId) throws SQLException {
        List<Participante> lista = new ArrayList<>();
        String sql = """
            SELECT p.id, p.nome, p.email, p.cpf, p.usuario_id
            FROM participante p
            INNER JOIN inscricao i ON p.id = i.participante_id
            WHERE i.evento_id = ?
            ORDER BY p.nome
        """;

        Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, eventoId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Participante p = new Participante();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setEmail(rs.getString("email"));
            p.setCpf(rs.getString("cpf"));
            p.setUsuarioId(rs.getInt("usuario_id"));
            lista.add(p);
        }

        ps.close();
        conexao.close();
        return lista;
    }

    public List<Participante> listarTodosComDetalhes() throws SQLException {
        List<Participante> lista = new ArrayList<>();
        String sql = "SELECT p.id, p.nome, p.data_nascimento, p.cpf, p.email, p.usuario_id FROM participante p ORDER BY p.nome ASC";
        Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
        PreparedStatement ps = conexao.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Participante p = new Participante();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setEmail(rs.getString("email"));
            p.setNascimento(rs.getDate("data_nascimento"));
            p.setCpf(rs.getString("cpf"));
            p.setUsuarioId(rs.getInt("usuario_id"));
            lista.add(p);
        }
        conexao.close();
        return lista;
    }

    public Participante buscarPorIdComEventos(int id) throws SQLException {
        Participante p = null;

        String sqlParticipante = "SELECT * FROM participante WHERE id = ?";

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement pst = conexao.prepareStatement(sqlParticipante)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                p = new Participante();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setEmail(rs.getString("email"));
                p.setNascimento(rs.getDate("data_nascimento"));
                p.setUsuarioId(rs.getInt("usuario_id"));
            } else {
                return null;
            }
        }

        String sqlUsuario = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conexaoUsuario = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement pstUsuario = conexaoUsuario.prepareStatement(sqlUsuario)) {

            pstUsuario.setInt(1, p.getUsuarioId());
            ResultSet rsU = pstUsuario.executeQuery();

            if (rsU.next()) {
                Usuario u = new Usuario();
                u.setId(rsU.getInt("id"));
                u.setLogin(rsU.getString("login"));
                u.setSenha(rsU.getString("senha"));
                u.setTipo(rsU.getInt("tipo"));
                p.setUsuario(u);
            }
        }
        List<Evento> eventos = listarEventosInscritos(id);
        p.setEventos(eventos);

        return p;
    }

    public void deletar(int id) throws SQLException {
         String sqlInscricao = "DELETE FROM inscricao WHERE participante_id = ?";
            Connection conexao1 = new ConexaoPostgres("postgres","postgres").getConnection();            
            PreparedStatement st1 = conexao1.prepareStatement(sqlInscricao);
            st1.setInt(1, id);
            st1.executeUpdate();
        
        String sql = "DELETE FROM participante WHERE id = ?";
        try (
            Connection conexao2 = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conexao2.prepareCall(sql);
            
         ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Participante buscarPorId(int id) throws SQLException {
        Participante p = null;
        String sql = "SELECT * FROM participante WHERE id = ?";
        
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                p = new Participante();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setEmail(rs.getString("email"));
                p.setNascimento(rs.getDate("data_nascimento"));
                p.setUsuarioId(rs.getInt("usuario_id"));
            }
        }  
        return p;
    }
}
