package modelo;

/**
 * 
 * @author Rodrigo
 *
 */
public class Usuario  {
    
    private String usuario, senha;

    /**
     * 
     */
    public Usuario(){
        this.usuario = this.senha = "";
    }

    /**
     * 
     * @param usuario
     * @param senha
     */
    public Usuario(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }

    /**
     * 
     * @return
     */
    public String getUsuario (){
        return usuario;
    }

    /**
     * 
     * @param usuario
     */
    public void setUsuario (String usuario){
        this.usuario = usuario;
    }

    /**
     * 
     * @return
     */
    public String getSenha (){
        return senha;
    }

    /**
     * 
     * @param senha
     */
    public void setSenha (String senha){
        this.senha = senha;
    }
    
}
