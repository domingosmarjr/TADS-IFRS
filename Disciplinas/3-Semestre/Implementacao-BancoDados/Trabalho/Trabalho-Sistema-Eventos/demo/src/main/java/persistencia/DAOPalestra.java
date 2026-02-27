package persistencia;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOPalestra {

    public void cadastrarPalestra(String titulo, int duracao, Timestamp data, int evento_id, String[] palestrantes) throws SQLException {
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        
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

            // Palestra-Palestrante
            String queryPP = "INSERT INTO palestra_palestrante (palestra_id, palestrante_id) VALUES (?, ?)";
            PreparedStatement instrucaoSQLPP = conexao.prepareStatement(queryPP);

            for (String idStr : palestrantes) {
                int palestranteId = Integer.parseInt(idStr);
                instrucaoSQLPP.setInt(1, palestraId);
                instrucaoSQLPP.setInt(2, palestranteId);
                // Função para atualizar valores -> sql
                instrucaoSQLPP.executeUpdate();
            }
            conexao.commit();
        } catch (SQLException e) {
            // impede conclusão de transação no sql
            conexao.rollback();
            throw e;
        } finally {
            // valida a comitação para true
            conexao.setAutoCommit(true);
            conexao.close();
        }
    }

}
