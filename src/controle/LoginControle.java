package controle;

import javax.swing.JOptionPane;

import modelo.Sessao;
import sgbd.UsuarioDBA;
import visual.Cadastro;
import visual.Login;
import visual.Menu;
import visual.Senha;

/**
 * 
 * @author Rodrigo
 *
 */
public class LoginControle {
    UsuarioDBA sgbdclientes = new UsuarioDBA();
    
    /**
     * 
     * @param usuario
     * @param senha
     * @param login
     */
    public void Login(String usuario, String senha, Login login){
        if (!emBranco(usuario, senha) && sgbdclientes.validarLogin(usuario, senha)){
            
        	new Sessao (usuario);
            
        	Menu menu = new Menu();
            menu.setVisible(true);

            login.dispose();
            
        } else JOptionPane.showMessageDialog(login,"Usuario e/ou senha incorrento(s).");
    }
    
    /**
     * 
     * @param login
     */
    public void Cadastro (Login login){
        Cadastro cadastro = new Cadastro();
        cadastro.setVisible(true);
        
        login.dispose();
    }
    
    /**
     * 
     */
    public void Senha(){
        Senha senha = new Senha();
        senha.setVisible(true);
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
