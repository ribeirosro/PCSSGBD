package sgbd;

import java.sql.*;

/**
 * 
 * @author Rodrigo
 *
 */
public class SessaoDBA {

	private Connection conexao = Conexao.getConexao();

    /**
     * 
     * @param usuario
     * @return
     */
	public int sessaoId(String usuario) {
		
		int id = 0;
		
		String sql = "SELECT * FROM usuario WHERE usuario = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, usuario);
			
			ResultSet resultado = preparador.executeQuery();
			if(resultado.next()) {
				id = resultado.getInt("id_usuario");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return id;
	}
	
}
