package sgbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Extrato;
import modelo.Sessao;

/**
 * 
 * @author Rodrigo
 *
 */
public class ExtratoDBA {
	private Connection conexao = Conexao.getConexao();

    /**
     * 
     * @return
     */
	public ArrayList<Extrato> visualizarTarefas() {
		
		String sql = "SELECT * FROM extrato WHERE id_usuario = ?";
		ArrayList<Extrato> lista = new ArrayList<>();
		
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, Sessao.getIdUsuario());
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()) {
				Extrato extrato = new Extrato();
				
				extrato.setDescricao(resultado.getString("texto"));
				extrato.setOperacao(resultado.getString("operacao"));
				extrato.setValor(resultado.getDouble("valor"));
								
				lista.add(extrato);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return lista;
	}

    /**
     * 
     * @param lista
     */
	public void cadastrarExtrato(ArrayList<Extrato> lista) {
		
		String sql = "INSERT INTO extrato (texto, operacao, valor, id_usuario, id_monetario) VALUES (?,?,?,?,?)";
		MonetarioDBA sgbdmonetario = new MonetarioDBA();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			for (Extrato extrato : lista) {
				preparador.setString(1, extrato.getDescricao());
				preparador.setString(2,  extrato.getOperacao());
				preparador.setDouble(3, extrato.getValor());
				preparador.setInt(4, Sessao.getIdUsuario());
				preparador.setInt(5, sgbdmonetario.getId());
				
				preparador.execute();
			}
			
			preparador.close();
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}


}
