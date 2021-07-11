
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Contato;

public class ContatoPersistence {
    
    private final static String dbUrl = "jdbc:sqlite:banco.db";
    
    public ContatoPersistence() {};
    
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl);
    }
    
    public ArrayList<Contato> getListaContatos() throws SQLException  {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM contato");
        ArrayList<Contato> listaContatos = new ArrayList<>();
        while (rs.next()) {
            listaContatos.add(new Contato(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone")
            ));
        }
        
        conn.close();
        return listaContatos;
    }
    
    public void salvarContato(Contato contato) throws SQLException  {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO contato(nome, telefone) VALUES(?, ?)");
        
        ps.setString(1, contato.getNome());
        ps.setString(2, contato.getTelefone());
        ps.executeUpdate();
        
        conn.close();
    }
    
    public void atualizarContato(Contato contato) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE contato SET nome = ?, telefone = ? WHERE id = ?");
        
        ps.setString(1, contato.getNome());
        ps.setString(2, contato.getTelefone());
        ps.setInt(3, contato.getId());
        ps.executeUpdate();
        
        conn.close();
    }

    public void excluirContato(Contato contato) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM contato WHERE id = ?");
        
        ps.setInt(1, contato.getId());
        ps.executeUpdate();
        
        conn.close();
    }
    
}
