package sgbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Sessao;

/**
 * 
 * @author Rodrigo
 *
 */
public class MonetarioDBA {
	private Connection conexao = Conexao.getConexao();

    /**
     * 
     * @return
     */
	public double visualizarSaldo() {
		
		String sql = "SELECT * FROM monetario WHERE id_usuario = ?";
		double saldo = 0.0;
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, Sessao.getIdUsuario());
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) saldo = resultado.getDouble("saldo");
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return saldo;
	}
	

    /**
     * 
     * @return
     */
	public boolean jahExiste() {
		
		String sql = "SELECT * FROM monetario WHERE id_usuario = ?";
		boolean existe = false;
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, Sessao.getIdUsuario());
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) existe = true;
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return existe;
	}

    /**
     * 
     * @param saldo
     */
	public void cadastrarSaldo(double saldo) {
		
		String sql = "INSERT INTO monetario (saldo, id_usuario) VALUES (?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setDouble(1, saldo);
			preparador.setInt(2, Sessao.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

    /**
     * 
     * @param novoSaldo
     */
	public void alterarSaldo(double novoSaldo) {
		
		String sql = "UPDATE monetario SET saldo = ? WHERE id_usuario = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setDouble(1, novoSaldo);
			preparador.setInt(2, Sessao.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

    /**
     * 
     */
	public void excluirSaldo() {
		
		String sql = "DELETE FROM monetario WHERE id_usuario = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, Sessao.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

    /**
     * 
     * @return
     */
	public int getId() {
		
		String sql = "SELECT id_monetario FROM monetario WHERE id_usuario = ?";
		int id = 0;
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, Sessao.getIdUsuario());
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) id = resultado.getInt("id_monetario");
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return id;
	}
	
}
