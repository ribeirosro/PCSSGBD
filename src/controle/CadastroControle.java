package controle;

import javax.swing.JOptionPane;

import modelo.Usuario;
import sgbd.UsuarioDBA;
import visual.Cadastro;
import visual.Login;

/**
 * 
 * @author Rodrigo
 *
 */
public class CadastroControle {
	Login login = new Login();
	
    UsuarioDBA sgbdCliente = new UsuarioDBA();
    
    /**
     * 
     * @param usuario
     * @param senha
     * @param cadastro
     */
    public void Cadastrar(String usuario, String senha, Cadastro cadastro){
        if (!emBranco(usuario, senha)){
            if (!sgbdCliente.usuarioExistente(usuario)){
            	Usuario cliente = new Usuario(usuario, senha);
                
            	sgbdCliente.cadastrarCliente(cliente);

                JOptionPane.showMessageDialog(cadastro,"Cliente cadastrado com sucesso.");

                cadastro.dispose();

                login.setVisible(true);
            } else JOptionPane.showMessageDialog(cadastro,"Usuario ja existe.");
        } else JOptionPane.showMessageDialog(cadastro,"Usuario e/ou senha em branco.");
    }
    
    /**
     * 
     * @param cadastro
     */
    public void Cancelar (Cadastro cadastro){
        login.setVisible(true);
        
        cadastro.dispose();
    }
    
    /* ---CRITICA DE DADOS--- */
    
    /**
     * 
     * @param usuario
     * @param senha
     * @return
     */
    private boolean emBranco(String usuario, String senha){
        return usuario.equals("") || senha.equals("");
    }
}
