package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.Evento;

public class DAOEvento {

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
            evento.setStauts(resultado.getString("status"));
            vetEvento.add(evento);
        }

        conexao.close();
        return vetEvento;
    }

}
