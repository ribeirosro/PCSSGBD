package sgbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Sessao;
import modelo.Tarefas;

/**
 * 
 * @author Rodrigo
 *
 */
public class TarefasDBA {
	private Connection conexao = Conexao.getConexao();

    /**
     * 
     * @return
     */
	public ArrayList<Tarefas> visualizarTarefas() {
		
		String sql = "SELECT * FROM tarefas WHERE id_usuario = ? ORDER BY prioridade, anotacao";
		ArrayList<Tarefas> lista = new ArrayList<>();
		
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, Sessao.getIdUsuario());
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()) {
				Tarefas tarefas = new Tarefas();
				
				tarefas.setAnotacao(resultado.getString("anotacao"));
				tarefas.setPrioridade(resultado.getInt("prioridade"));
								
				lista.add(tarefas);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return lista;
	}

    /**
     * 
     * @param tarefas
     */
	public void cadastrarTarefa(Tarefas tarefas) {
		
		String sql = "INSERT INTO tarefas (anotacao, prioridade, id_usuario) VALUES (?,?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, tarefas.getAnotacao());
			preparador.setInt(2, tarefas.getPrioridade());
			preparador.setInt(3, Sessao.getIdUsuario());
			
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
     * @param prioridadeAlterada
     * @param prioridade
     */
	public void alterarTarefa(String notaAlterada, String nota, int prioridadeAlterada, int prioridade) {
		
		String sql = "UPDATE tarefas SET anotacao = ?, prioridade = ? WHERE anotacao = ? AND prioridade = ? AND id_usuario = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, notaAlterada);
			preparador.setInt(2, prioridadeAlterada);
			preparador.setString(3, nota);
			preparador.setInt(4, prioridade);
			preparador.setInt(5, Sessao.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

    /**
     * 	
     * @param nota
     * @param prioridade
     */
	public void excluirTarefa(String nota, int prioridade) {
		
		String sql = "DELETE FROM tarefas WHERE anotacao = ? AND prioridade = ? AND id_usuario = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, nota);
			preparador.setInt(2, prioridade);
			preparador.setInt(3, Sessao.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
