package sistema_eventos.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PGobject;

import sistema_eventos.negocio.Evento;
import sistema_eventos.negocio.EventoPrivado;
import sistema_eventos.persistencia.ConexaoPostgres;

public class DAOEvento {

    // LISTA EVENTOS GERAIS
    public List<Evento> listarEventos() throws SQLException{
        List<Evento> vetEvento = new ArrayList<>();
        // String query = "SELECT * FROM evento ORDER BY id";
        String query = """
            SELECT 
                id,
                nome,
                data_inicio,
                hora_inicio,
                data_fim,
                hora_fim,
                local,
                CASE
                    WHEN data_fim < CURRENT_DATE THEN 'Encerrado'
                    WHEN data_inicio > CURRENT_DATE THEN 'Futuro'
                    ELSE 'Andamento'
                END AS status
            FROM evento
            ORDER BY id;
        """;
        // String query = "SELECT evento.id, evento.nome, evento.data_inicio, evento.hora_inicio, evento.data_fim, evento.hora_fim, evento.local CASE WHEN evento.data_fim < current_date THEN 'Encerrado' WHEN evento.data_inicio > current_date THEN 'Futuro' ELSE 'Andamento' END as status FROM evento ORDER BY evento.id";
        
        // CONECTA NO BANCO
        Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
        // OBJETO QUE EXECUTA COISAS -> PreparedStatement
        PreparedStatement instrucaoSQL = conexao.prepareStatement(query);
        // ResultSet -> recebe resultados de buscas
        // preparedstatement.executeQuery() -> executa a busca
        ResultSet resultado = instrucaoSQL.executeQuery();

        while (resultado.next()){
            Evento evento = new Evento();
            evento.setId(resultado.getInt("id"));
            evento.setNome(resultado.getString("nome"));
            evento.setData_inicio(resultado.getDate("data_inicio"));
            evento.setHora_inicio(resultado.getTime("hora_inicio"));
            evento.setData_fim(resultado.getDate("data_fim"));
            evento.setHora_fim(resultado.getTime("hora_fim"));
            evento.setLocal(resultado.getString("local"));
            evento.setStatus(resultado.getString("status"));
            vetEvento.add(evento);
        }
        conexao.close();
        return vetEvento;
    }


    // LISTAR EVENTOS INSCRITOS
    public List<Evento> listarEventosInscritos(String cpf) throws SQLException{
        Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
        String query = """
                SELECT evento.id, evento.nome, evento.local, evento.data_inicio, evento.data_fim 
                FROM evento INNER JOIN inscricao ON evento.id = inscricao.evento_id
                INNER JOIN participante ON inscricao.participante_id = participante.id
                WHERE participante.cpf = ?
                ORDER BY evento.data_inicio;
                """;
        PreparedStatement instrucaoSQL = conexao.prepareStatement(query);
        instrucaoSQL.setString(1, cpf);
        ResultSet resultado = instrucaoSQL.executeQuery();

        List<Evento> eventosInscritos = new ArrayList();

        while(resultado.next()){
            Evento novoEvento = new Evento();
            novoEvento.setId(resultado.getInt("id"));
            novoEvento.setNome(resultado.getString("nome"));
            novoEvento.setLocal(resultado.getString("local"));
            novoEvento.setData_inicio(resultado.getDate("data_inicio"));
            novoEvento.setData_fim(resultado.getDate("data_fim"));
            eventosInscritos.add(novoEvento);
        }
        conexao.close();
        return eventosInscritos;
    }

    // LISTAR EVENTOS DISPONÍVEIS
    public List<Evento> listarEventosDisponiveis(String cpf) throws SQLException {
        List<Evento> eventos = new ArrayList<>();

        String query = """
                SELECT evento.id, evento.nome, evento.local, evento.data_inicio, evento.data_fim
                FROM evento WHERE evento.data_fim >= CURRENT_DATE AND evento.id NOT IN (
                    SELECT inscricao.evento_id FROM inscricao 
                    INNER JOIN participante ON inscricao.participante_id = participante.id
                    WHERE participante.cpf = ? 
                ) ORDER BY evento.data_inicio
                """;
        
        Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(query);
        instrucaoSQL.setString(1, cpf);
        ResultSet resultado = instrucaoSQL.executeQuery();

        while (resultado.next()){
            Evento evento = new Evento();
            evento.setId(resultado.getInt("id"));
            evento.setNome(resultado.getString("nome"));
            evento.setLocal(resultado.getString("local"));
            evento.setData_inicio(resultado.getDate("data_inicio"));
            evento.setData_fim(resultado.getDate("data_fim"));
            eventos.add(evento);
        }
        return eventos;
    }

    // VERIFICAR INSCRIÇÃO EM EVENTO
    public boolean verificarInscricao(String cpf, int eventoId) throws SQLException {
        String query = """
                SELECT 1 FROM inscricao
                INNER JOIN participante ON participante.id = inscricao.participante_id
                WHERE participante.cpf = ? AND inscricao.evento_id = ?
                """;
        try (
            Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement instrucaoSQL = conexao.prepareStatement(query)) {
                instrucaoSQL.setString(1, cpf);
                instrucaoSQL.setInt(2, eventoId);
                ResultSet resultado = instrucaoSQL.executeQuery();
                return resultado.next();
            }
    }

    // CADASTRAR INSCRIÇÃO EM EVENTO
    public void cadastrarInscricao(String cpf, int eventoId) throws SQLException {
        String query = """
                INSERT INTO inscricao(participante_id, evento_id)
                SELECT id, ? FROM participante WHERE cpf = ?
                """;
        try ( 
            Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement instrucaoSQL = conexao.prepareStatement(query)) {
                instrucaoSQL.setInt(1, eventoId);
                instrucaoSQL.setString(2, cpf);
                instrucaoSQL.executeUpdate();
            }
    }

    // REMOVER INSCRIÇÃO DE PARTICIPANTE
    public void desinscreverParticipante(int eventoId, int participanteId) throws SQLException {
        String sql = "DELETE FROM inscricao WHERE evento_id = ? AND participante_id = ?";
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, eventoId);
            pst.setInt(2, participanteId);
            pst.executeUpdate();
        }
    }

    // BUSCAR EVENTO POR ID
    public Evento buscarPorId(int id) throws SQLException {
        String sql = """
            SELECT id, nome, data_inicio, hora_inicio, data_fim, hora_fim, local, detalhes, imagem_banner
            FROM evento
            WHERE id = ?
        """;
        Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Evento evento = null;
        if (rs.next()) {
            evento = new Evento();
            evento.setId(rs.getInt("id"));
            evento.setNome(rs.getString("nome"));
            evento.setData_inicio(rs.getDate("data_inicio"));
            evento.setHora_inicio(rs.getTime("hora_inicio"));
            evento.setData_fim(rs.getDate("data_fim"));
            evento.setHora_fim(rs.getTime("hora_fim"));
            evento.setLocal(rs.getString("local"));
            evento.setDetalhes(rs.getString("detalhes"));
            evento.setImagemBanner(rs.getBytes("imagem_banner"));
        }
        rs.close();
        ps.close();
        conexao.close();
        return evento;
    }

    public List<Evento> listarInscritos(int participanteId) throws SQLException {

        String query = """
            SELECT e.id, e.nome, e.local, e.data_inicio, e.data_fim, e.detalhes
            FROM evento e
            INNER JOIN inscricao i ON e.id = i.evento_id
            WHERE i.participante_id = ?
            ORDER BY e.data_inicio;
        """;

        Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
        PreparedStatement ps = conexao.prepareStatement(query);
        ps.setInt(1, participanteId);
        ResultSet rs = ps.executeQuery();

        List<Evento> lista = new ArrayList<>();

        while (rs.next()) {
            Evento e = new Evento();
            e.setId(rs.getInt("id"));
            e.setNome(rs.getString("nome"));
            e.setLocal(rs.getString("local"));
            e.setData_inicio(rs.getDate("data_inicio"));
            e.setData_fim(rs.getDate("data_fim"));
            e.setDetalhes(rs.getString("detalhes"));

            String detalhes = rs.getString("detalhes");
            if (detalhes != null && detalhes.trim().startsWith("{")) {
                int idx = detalhes.indexOf("\"descricao\"");
                if (idx != -1) {
                    int start = detalhes.indexOf(":", idx) + 1;
                    int end = detalhes.indexOf("\"", start + 1);
                    if (start != -1 && end != -1) {
                        e.setDetalhes(detalhes.substring(start + 1, end));
                    } else {
                        e.setDetalhes(detalhes);
                    }
                } else {
                    e.setDetalhes(detalhes);
                }
            } else {
                e.setDetalhes(detalhes);
            }

            lista.add(e);
        }

        conexao.close();
        return lista;
    }

    // LISTAR EVENTOS DISPONÍVEIS (NÃO INSCRITOS)
    public List<Evento> listarNaoInscritos(int participanteId) throws SQLException {
        List<Evento> lista = new ArrayList<>();

        String query = 
            "SELECT e.id, e.nome, e.local, e.data_inicio, e.hora_inicio, " +
            "e.data_fim, e.hora_fim, e.detalhes, e.imagem_banner " +
            "FROM evento e " +
            "WHERE NOT EXISTS ( " +
            "      SELECT 1 FROM inscricao i " +
            "      WHERE i.evento_id = e.id " +
            "        AND i.participante_id = ? " +
            ") " +
            "ORDER BY e.id;";

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(query)) {

            ps.setInt(1, participanteId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Evento e = new Evento();
                    e.setId(rs.getInt("id"));
                    e.setNome(rs.getString("nome"));
                    e.setLocal(rs.getString("local"));
                    e.setData_inicio(rs.getDate("data_inicio"));
                    e.setHora_inicio(rs.getTime("hora_inicio"));
                    e.setData_fim(rs.getDate("data_fim"));
                    e.setHora_fim(rs.getTime("hora_fim"));
                    e.setImagemBanner(rs.getBytes("imagem_banner"));
                    e.setDetalhes(rs.getString("detalhes"));

                    String detalhes = rs.getString("detalhes");
                    if (detalhes != null && detalhes.trim().startsWith("{")) {
                        int idx = detalhes.indexOf("\"descricao\"");
                        if (idx != -1) {
                            int start = detalhes.indexOf(":", idx) + 1;
                            int end = detalhes.indexOf("\"", start + 1);
                            if (start != -1 && end != -1) {
                                e.setDetalhes(detalhes.substring(start + 1, end));
                            } else {
                                e.setDetalhes(detalhes);
                            }
                        } else {
                            e.setDetalhes(detalhes);
                        }
                    } else {
                        e.setDetalhes(detalhes);
                    }

                    lista.add(e);
                }
            }
        }

        return lista;
    }

    public void inscreverParticipante(int eventoId, int participanteId) throws SQLException {
        String sql = "INSERT INTO inscricao (evento_id, participante_id) VALUES (?, ?)";

        try (Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, eventoId);
            ps.setInt(2, participanteId);
            ps.executeUpdate();
        }
    }

    // VERIFICA SE PARTICIPANTE JÁ ESTÁ INSCRITO
    public boolean verificarInscricao(int eventoId, int participanteId) throws SQLException {
        String sql = "SELECT 1 FROM inscricao WHERE evento_id = ? AND participante_id = ?";
        try (Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, eventoId);
            ps.setInt(2, participanteId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // LISTAR EVENTOS POR ORGANIZADOR
    public List<Evento> listarPorOrganizador(int organizadorId) throws SQLException {
        List<Evento> eventos = new ArrayList<>();

        String query = """
            SELECT id, nome, local, data_inicio, hora_inicio, data_fim, hora_fim, detalhes, imagem_banner
            FROM evento
            WHERE organizador_id = ?
            ORDER BY data_inicio;
        """;

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(query)) {

            ps.setInt(1, organizadorId);

            ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Evento evento = new Evento();
                    evento.setId(rs.getInt("id"));
                    evento.setNome(rs.getString("nome"));
                    evento.setLocal(rs.getString("local"));
                    evento.setData_inicio(rs.getDate("data_inicio"));
                    evento.setHora_inicio(rs.getTime("hora_inicio"));
                    evento.setData_fim(rs.getDate("data_fim"));
                    evento.setHora_fim(rs.getTime("hora_fim"));
                    evento.setDetalhes(rs.getString("detalhes"));
                    evento.setImagemBanner(rs.getBytes("imagem_banner"));
                    eventos.add(evento);
                }
            }

        return eventos;
    }

    // ATUALIZAR EVENTO
    public void atualizar(Evento evento) throws SQLException {
        String sql = """
            UPDATE evento
            SET nome = ?,
                local = ?,
                data_inicio = ?,
                hora_inicio = ?,
                data_fim = ?,
                hora_fim = ?,
                detalhes = ?,
                imagem_banner = ?
            WHERE id = ?
        """;

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, evento.getNome());
            ps.setString(2, evento.getLocal());
            ps.setDate(3, evento.getData_inicio() != null ? new java.sql.Date(evento.getData_inicio().getTime()) : null);
            ps.setTime(4, evento.getHora_inicio());
            ps.setDate(5, evento.getData_fim() != null ? new java.sql.Date(evento.getData_fim().getTime()) : null);
            ps.setTime(6, evento.getHora_fim());

            // CORREÇÃO: detalhes como JSONB
            PGobject jsonObject = new PGobject();
            jsonObject.setType("jsonb");
            jsonObject.setValue(evento.getDetalhes() != null ? evento.getDetalhes() : "{}");
            ps.setObject(7, jsonObject);

            ps.setBytes(8, evento.getImagemBanner());
            ps.setInt(9, evento.getId());

            ps.executeUpdate();
        }
    }


    // DELETAR EVENTO
    public void deletar(int eventoId) throws SQLException {
        String sql = "DELETE FROM evento WHERE id = ?";

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, eventoId);
            ps.executeUpdate();
        }
    }


    public void inserir(Evento evento) throws SQLException {
        String sql = """
            INSERT INTO evento
            (nome, local, data_inicio, hora_inicio, data_fim, hora_fim, detalhes, imagem_banner, organizador_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, evento.getNome());
            ps.setString(2, evento.getLocal());
            ps.setDate(3, evento.getData_inicio());
            ps.setTime(4, evento.getHora_inicio());
            ps.setDate(5, evento.getData_fim());
            ps.setTime(6, evento.getHora_fim());

            PGobject json = new PGobject();
            json.setType("jsonb");
            json.setValue(evento.getDetalhes() != null ? evento.getDetalhes() : "{}");
            ps.setObject(7, json);

            ps.setBytes(8, evento.getImagemBanner());

            ps.setInt(9, evento.getOrganizadorId());

            ps.executeUpdate();
        }
    }

    // VERIFICA SE É EVENTO PRIVADO
    public boolean isEventoPrivado(int eventoId) throws SQLException {
        String sql = "SELECT 1 FROM evento_privado WHERE id = ?";
        try (Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, eventoId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // VERIFICA SENHA DE EVENTO PRIVADO
    public boolean verificarSenhaEventoPrivado(int eventoId, String senhaInformada) throws SQLException {
        String sql = "SELECT senha_acesso FROM evento_privado WHERE id = ?";
        try (Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, eventoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return senhaInformada.equals(rs.getString("senha_acesso"));
                }
            }
        }
        return false;
    }
    
    // BUSCAR EVENTO PAI (evento ou evento_privado)
    public int buscarEventoPaiId(int eventoId) throws SQLException {
        String sql = "SELECT id FROM evento WHERE id = ?";
        try (Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, eventoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }

        sql = "SELECT id FROM evento_privado WHERE id = ?";
        try (Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, eventoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return -1;
    }

    // INSERIR EVENTO PRIVADO
    public int inserirEventoPrivado(EventoPrivado ep) throws SQLException {
        String sqlEvento = "INSERT INTO evento (nome, local, data_inicio, hora_inicio, data_fim, hora_fim, detalhes, imagem_banner, organizador_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        
        int eventoId;
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sqlEvento)) {
            ps.setString(1, ep.getNome());
            ps.setString(2, ep.getLocal());
            ps.setDate(3, ep.getData_inicio());
            ps.setTime(4, ep.getHora_inicio());
            ps.setDate(5, ep.getData_fim());
            ps.setTime(6, ep.getHora_fim());
            ps.setObject(7, ep.getDetalhes(), java.sql.Types.OTHER);
            ps.setBytes(8, ep.getImagemBanner());
            ps.setInt(9, ep.getOrganizadorId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                eventoId = rs.getInt("id");
            } else {
                throw new SQLException("Erro ao inserir evento privado: ID não retornado");
            }
        }

        String sqlPrivado = "INSERT INTO evento_privado (id, senha_acesso, lista_convidados) VALUES (?, ?, ?)";
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sqlPrivado)) {
            ps.setInt(1, eventoId);
            ps.setString(2, ep.getSenhaAcesso());
            ps.setString(3, ep.getListaConvidados());
            ps.executeUpdate();
        }

        return eventoId;
    }
}