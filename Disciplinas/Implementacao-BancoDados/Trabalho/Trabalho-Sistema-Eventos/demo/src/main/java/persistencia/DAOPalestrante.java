package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.Palestrante;

public class DAOPalestrante {

    public List<Palestrante> listarPalestrantes() throws SQLException {
        List<Palestrante> vetPalestrantes = new ArrayList<>();
        
        String query= "SELECT id, nome, biografia, cpf FROM palestrante;";

        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(query);
        ResultSet resultado = instrucaoSQL.executeQuery();
        
        while (resultado.next()) { 
            Palestrante palestrante = new Palestrante();
            palestrante.setId(resultado.getInt("id"));
            palestrante.setNome(resultado.getString("nome"));
            palestrante.setBiografia(resultado.getString("biografia"));
            palestrante.setCpf(resultado.getString("cpf"));
            vetPalestrantes.add(palestrante);
        }
        return vetPalestrantes;
    }

    public List<Palestrante> quantidadePalestras() throws SQLException{
        List<Palestrante> vetQntPalestrante = new ArrayList<>();
        String query = """
            SELECT palestrante.nome, count(palestra_palestrante.palestra_id) AS total_palestras FROM palestrante
            LEFT JOIN palestra_palestrante ON palestrante.id = palestra_palestrante.palestrante_id
            GROUP BY palestrante.nome ORDER BY count(palestra_palestrante.palestra_id) DESC;
        """;

        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(query);
        ResultSet resultado = instrucaoSQL.executeQuery();

        while (resultado.next()) {
            Palestrante palestrante = new Palestrante();
            palestrante.setNome(resultado.getString("nome"));
            palestrante.setTotalPalestras(resultado.getInt("total_palestras"));
            vetQntPalestrante.add(palestrante);
        }

        conexao.close();
        return vetQntPalestrante;
    }

}
