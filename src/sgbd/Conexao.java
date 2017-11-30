package sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Rodrigo
 *
 */
public class Conexao {

    /**
     * 
     * @return
     */
	public static Connection getConexao() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/utility", "postgres", "root");

		} catch (SQLException e) {
			System.out.println("Erro - conexao: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Erro - driver: " + e.getMessage());
		}
		
		return con;
	}
	
}
