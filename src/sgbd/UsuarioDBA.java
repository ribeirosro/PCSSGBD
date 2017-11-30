package sgbd;

import java.sql.*;

import modelo.Usuario;

/**
 * 
 * @author Rodrigo
 *
 */
public class UsuarioDBA {
	
	private Connection conexao = Conexao.getConexao();

    /**
     * 
     * @param cliente
     */
	public void cadastrarCliente(Usuario cliente) {
		
		String sql = "INSERT INTO usuario (usuario, senha) VALUES (?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, cliente.getUsuario());
			preparador.setString(2, cliente.getSenha());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

    /**
     * 
     * @param usuario
     * @return
     */
	public String recuperarSenha(String usuario) {
		
		String password = null;
		
		String sql = "SELECT * FROM usuario WHERE usuario = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, usuario);
			
			ResultSet resultado = preparador.executeQuery();
			if(resultado.next()) {
				password = resultado.getString("senha");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return password;
	}

    /**
     * 
     * @param usuario
     * @return
     */
	public boolean usuarioExistente(String usuario) {
		
		boolean existe = false;
		
		String sql = "SELECT * FROM usuario WHERE usuario = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, usuario);
			
			ResultSet resultado = preparador.executeQuery();
			if(resultado.next()) existe = true;
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return existe;
	}

    /**
     * 
     * @param usuario
     * @param senha
     * @return
     */
	public boolean validarLogin(String usuario, String senha) {
		boolean existeUsuario = false;
		
		String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, usuario);
			preparador.setString(2, senha);
			
			ResultSet resultado = preparador.executeQuery();
			if(resultado.next()) existeUsuario = true;
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return existeUsuario;
	}

}
