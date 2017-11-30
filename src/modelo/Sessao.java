package modelo;

import sgbd.SessaoDBA;

/**
 * 
 * @author Rodrigo
 *
 */
public class Sessao {
    private static String usuario;
    private static int idUsuario;

    /**
     * 
     * @param usuario
     */
    public Sessao(String usuario){
        Sessao.usuario = usuario;
        
        idUsuario = recuperarId();
    }

    /**
     * 
     * @return
     */
    public static String getUsuario(){
        return usuario;
    }

    /**
     * 
     * @return
     */
    public static int getIdUsuario() {
    	return idUsuario;
    }

    /**
     * 
     * @return
     */
    private int recuperarId() {
    	SessaoDBA sgbdSessao =  new SessaoDBA();
    	
    	return sgbdSessao.sessaoId(usuario);
    }
    
}
