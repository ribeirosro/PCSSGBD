package sgbd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Calendario;
import modelo.Sessao;

/**
 * 
 * @author Rodrigo
 *
 */
public class CalendarioDBA {
	
	private Connection conexao = Conexao.getConexao();

    /**
     * 
     * @return
     */
	public ArrayList<Calendario> visualizarCalendario() {
		
		String sql = "SELECT * FROM calendario WHERE id_usuario = ? ORDER BY data, anotacao";
		ArrayList<Calendario> lista = new ArrayList<>();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, Sessao.getIdUsuario());
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()) {
				Calendario data = new Calendario();
								
				data.setAnotacao(resultado.getString("anotacao"));
				data.setData(resultado.getDate("data"));
				
				
				lista.add(data);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return lista;
	}

    /**
     * 
     * @param calendario
     */
	public void cadastrarCalendario(Calendario calendario) {
		
		String sql = "INSERT INTO calendario (anotacao, data, id_usuario) VALUES (?,?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, calendario.getAnotacao());
			preparador.setDate(2, calendario.getData());
			preparador.setInt(3, Sessao.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

    /**
     * 
     * @param anotacaoAlterada
     * @param anotacao
     * @param dataAlterada
     * @param data
     */
	public void alterarCalendario(String anotacaoAlterada, String anotacao, Date dataAlterada, Date data) {
		
		String sql = "UPDATE calendario SET anotacao = ?, data = ? WHERE anotacao = ? AND data = ? AND id_usuario = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, anotacaoAlterada);
			preparador.setDate(2, dataAlterada);
			preparador.setString(3, anotacao);
			preparador.setDate(4, data);
			preparador.setInt(5, Sessao.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

    /**
     * 
     * @param anotacao
     * @param data
     */
	public void excluirCalendario(String anotacao, Date data) {
		
		String sql = "DELETE FROM calendario WHERE anotacao = ? AND data = ? AND id_usuario = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, anotacao);
			preparador.setDate(2, data);
			preparador.setInt(3, Sessao.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
}
