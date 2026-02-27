package sistema_eventos.persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PGobject;

import sistema_eventos.negocio.EventoPrivado;

public class DAOEventoPrivado {

    public int inserir(EventoPrivado evento) throws SQLException {
        int eventoId = -1;

        // Criar EventoPrivado = Criar EventoPrivado + Evento
        String sql = """
                INSERT INTO evento_privado (nome, local, data_inicio, hora_inicio, data_fim, hora_fim, detalhes, imagem_banner, organizador_id, senha_acesso, lista_convidados)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                RETURNING id
                """;
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection()) {
            conexao.setAutoCommit(false);
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, evento.getNome());
                ps.setString(2, evento.getLocal());
                ps.setDate(3, evento.getData_inicio());
                ps.setTime(4, evento.getHora_inicio());
                ps.setDate(5, evento.getData_fim());
                ps.setTime(6, evento.getHora_fim());

                PGobject detalhes = new PGobject();
                detalhes.setType("jsonb");
                detalhes.setValue(evento.getDetalhes() != null ? evento.getDetalhes() : "{}");
                ps.setObject(7, detalhes);

                ps.setBytes(8, evento.getImagemBanner());
                ps.setInt(9, evento.getOrganizadorId());
                ps.setString(10, evento.getSenhaAcesso());

                PGobject convidados = new PGobject();
                convidados.setType("jsonb");
                convidados.setValue(evento.getListaConvidados());
                ps.setObject(11, convidados);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    eventoId = rs.getInt("id");
                } else {
                    throw new SQLException("Erro ao inserir evento privado: ID não retornado");
                }
            }
            throw new SQLException("Erro ao inserir evento privado");
        }
    }

    // BUSCAR POR ID
    public EventoPrivado buscarPorId(int id) throws SQLException {
        String sql = """
            SELECT 
                e.id,
                e.nome,
                e.local,
                e.data_inicio,
                e.hora_inicio,
                e.data_fim,
                e.hora_fim,
                e.detalhes,
                e.imagem_banner,
                e.organizador_id,
                p.senha_acesso,
                p.lista_convidados
            FROM evento e
            JOIN evento_privado p ON e.id = p.id
            WHERE e.id = ?
            """;

        try (Connection con = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return null;

            EventoPrivado ep = new EventoPrivado();

            ep.setId(rs.getInt("id"));
            ep.setNome(rs.getString("nome"));
            ep.setLocal(rs.getString("local"));
            ep.setData_inicio(rs.getDate("data_inicio"));
            ep.setHora_inicio(rs.getTime("hora_inicio"));
            ep.setData_fim(rs.getDate("data_fim"));
            ep.setHora_fim(rs.getTime("hora_fim"));
            ep.setDetalhes(rs.getString("detalhes"));
            ep.setImagemBanner(rs.getBytes("imagem_banner"));
            ep.setOrganizadorId(rs.getInt("organizador_id"));

            ep.setSenhaAcesso(rs.getString("senha_acesso"));
            ep.setListaConvidados(rs.getString("lista_convidados"));

            return ep;
        }
    }


    // LISTAR TODOS
    public List<EventoPrivado> listarTodos() throws SQLException {

        List<EventoPrivado> lista = new ArrayList<>();

        String sql = """
            SELECT id, nome, data_inicio, hora_inicio, data_fim, hora_fim,
                   local, detalhes, imagem_banner, organizador_id,
                   senha_acesso, lista_convidados
            FROM evento_privado
            ORDER BY id
        """;

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EventoPrivado evento = new EventoPrivado();

                evento.setId(rs.getInt("id"));
                evento.setNome(rs.getString("nome"));
                evento.setData_inicio(rs.getDate("data_inicio"));
                evento.setHora_inicio(rs.getTime("hora_inicio"));
                evento.setData_fim(rs.getDate("data_fim"));
                evento.setHora_fim(rs.getTime("hora_fim"));
                evento.setLocal(rs.getString("local"));
                evento.setDetalhes(rs.getString("detalhes"));
                evento.setImagemBanner(rs.getBytes("imagem_banner"));
                evento.setOrganizadorId(rs.getInt("organizador_id"));
                evento.setSenhaAcesso(rs.getString("senha_acesso"));
                evento.setListaConvidados(rs.getString("lista_convidados"));

                lista.add(evento);
            }
        }

        return lista;
    }

    // ATUALIZAR EVENTO PRIVADO
    public void atualizar(EventoPrivado evento) throws SQLException {

        String sql = """
            UPDATE evento_privado
            SET nome = ?, local = ?, data_inicio = ?, hora_inicio = ?, data_fim = ?, hora_fim = ?,
                detalhes = ?, imagem_banner = ?, organizador_id = ?, senha_acesso = ?, lista_convidados = ?
            WHERE id = ?
        """;

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, evento.getNome());
            ps.setString(2, evento.getLocal());
            ps.setDate(3, evento.getData_inicio());
            ps.setTime(4, evento.getHora_inicio());
            ps.setDate(5, evento.getData_fim());
            ps.setTime(6, evento.getHora_fim());

            PGobject detalhes = new PGobject();
            detalhes.setType("jsonb");
            detalhes.setValue(evento.getDetalhes());
            ps.setObject(7, detalhes);

            ps.setBytes(8, evento.getImagemBanner());
            ps.setInt(9, evento.getOrganizadorId());
            ps.setString(10, evento.getSenhaAcesso());

            PGobject lista = new PGobject();
            lista.setType("jsonb");
            lista.setValue(evento.getListaConvidados());
            ps.setObject(11, lista);

            ps.setInt(12, evento.getId());

            ps.executeUpdate();
        }
    }

    // DELETAR
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM evento_privado WHERE id = ?";

        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }


    // ====================== NOVO CÓDIGO
    public int inserirEventoPrivado(EventoPrivado ep) throws SQLException {
        int eventoId;
        String sqlEvento = """
                INSERT INTO evento
                    (nome, local, data_inicio, hora_inicio, data_fim, hora_fim, detalhes, imagem_banner, organizador_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                RETURNING id;
                """;
        try (Connection conexao = new ConexaoPostgres("postgres", "postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sqlEvento)) {
            ps.setString(1, ep.getNome());
            ps.setString(2, ep.getLocal());
            ps.setDate(3, ep.getData_inicio());
            ps.setTime(4, ep.getHora_inicio());
            ps.setDate(5, ep.getData_fim());
            ps.setTime(6, ep.getHora_fim());
            
            PGobject detalhesJSON = new PGobject();
            detalhesJSON.setType("json");
            detalhesJSON.setValue(ep.getDetalhes());
            ps.setObject(7, detalhesJSON);

            ps.setBytes(8, ep.getImagemBanner());
            ps.setInt(9, ep.getOrganizadorId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                eventoId = rs.getInt("id");
            } else {
                throw new SQLException("Erro ao inserir evento privado: ID não retornado");
            }

           
        }

        String sqlPrivado = """
            INSERT INTO evento_privado (id, senha_acesso, lista_convidados)
            VALUES (?, ?, ?)
            """;
        
        try (Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
            PreparedStatement ps = conexao.prepareStatement(sqlPrivado)) {
            
            ps.setInt(1, eventoId);
            ps.setString(2, ep.getSenhaAcesso());
            PGobject convidadosJSON = new PGobject();
            convidadosJSON.setType("json");
            convidadosJSON.setValue(ep.getListaConvidados());
            ps.setObject(3, convidadosJSON);
        
            ps.executeUpdate();
        }
        return eventoId;
    }

    // Verifica se o ID aparece na tabela evento_privado
    public boolean existeEventoPrivado(int eventoId) throws SQLException {
    String sql = "SELECT 1 FROM evento_privado WHERE id = ?";
    try (Connection conexao = new ConexaoPostgres("postgres","postgres").getConnection();
         PreparedStatement ps = conexao.prepareStatement(sql)) {

        ps.setInt(1, eventoId);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); // true = evento privado
        }
    }
}
}
