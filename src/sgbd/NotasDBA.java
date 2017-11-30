package sgbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Notas;
import modelo.Sessao;

/**
 * 
 * @author Rodrigo
 *
 */
public class NotasDBA {

	private Connection conexao = Conexao.getConexao();

    /**
     * 
     * @return
     */
	public ArrayList<Notas> visualizarNotas() {
		
		String sql = "SELECT * FROM notas WHERE id_usuario = ? ORDER BY anotacao";
		ArrayList<Notas> lista = new ArrayList<>();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, Sessao.getIdUsuario());
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()) {
				Notas notas = new Notas();
				
				notas.setAnotacao(resultado.getString("anotacao"));
				
				lista.add(notas);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return lista;
	}

    /**
     * 
     * @param notas
     */
	public void cadastrarNota(Notas notas) {
		
		String sql = "INSERT INTO notas (anotacao, id_usuario) VALUES (?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, notas.getAnotacao());
			preparador.setInt(2, Sessao.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

    /**
     * 
     * @param notaAlterada
     * @param nota
     */
	public void alterarNota(String notaAlterada, String nota) {
		
		String sql = "UPDATE notas SET anotacao = ? WHERE anotacao = ? AND id_usuario = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, notaAlterada);
			preparador.setString(2, nota);
			preparador.setInt(3, Sessao.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

    /**
     * 
     * @param nota
     */
	public void excluirNota(String nota) {
		
		String sql = "DELETE FROM notas WHERE anotacao = ? AND id_usuario = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, nota);
			preparador.setInt(2, Sessao.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
