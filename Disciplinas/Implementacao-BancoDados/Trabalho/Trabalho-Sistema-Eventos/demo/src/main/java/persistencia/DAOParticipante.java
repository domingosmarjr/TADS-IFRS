package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.Evento;
import negocio.Participante;

public class DAOParticipante {


    // Buscar por CPF
    // Busca uma pessoa de determinado CPF no Banco.
    // Cria uma instância de pessoa para ser apresentada.
    public Participante buscarPorCPF(String cpf) throws SQLException {
        Connection conexao = new ConexaoPostgreSQL().getConexao();
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
}
