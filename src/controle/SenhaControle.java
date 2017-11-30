package controle;

import javax.swing.JOptionPane;

import sgbd.UsuarioDBA;
import visual.Senha;

/**
 * 
 * @author Rodrigo
 *
 */
public class SenhaControle {
    UsuarioDBA sgbdCliente = new UsuarioDBA();
    
    /**
     * 
     * @param usuario
     * @param senha
     */
    public void buscarSenha(String usuario, Senha senha){
            if (sgbdCliente.usuarioExistente(usuario)) JOptionPane.showMessageDialog(senha,"Sua senha e " + sgbdCliente.recuperarSenha(usuario));
            else JOptionPane.showMessageDialog(senha,"O usuario informado nao existe");
    }
}
