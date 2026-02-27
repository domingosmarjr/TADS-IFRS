package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.Evento;

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
        Connection conexao = new ConexaoPostgreSQL().getConexao();
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
        Connection conexao = new ConexaoPostgreSQL().getConexao();
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
        
        Connection conexao = new ConexaoPostgreSQL().getConexao();
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
            Connection conexao = new ConexaoPostgreSQL().getConexao();
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
            Connection conexao = new ConexaoPostgreSQL().getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement(query)) {
                instrucaoSQL.setInt(1, eventoId);
                instrucaoSQL.setString(2, cpf);
                instrucaoSQL.executeUpdate();
            }
    }

}