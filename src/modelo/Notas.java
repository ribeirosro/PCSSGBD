package modelo;

/**
 * 
 * @author Rodrigo
 *
 */
public class Notas {
    private String anotacao;

    /**
     * 
     */
    public Notas(){
        anotacao = "";
        
    }

    /**
     * 
     * @param nota
     */
    public Notas(String nota){
        this.anotacao = nota;
    }

    /**
     * 
     * @return
     */
    public String getAnotacao(){
        return anotacao;
    }

    /**
     * 
     * @param anotacao
     */
    public void setAnotacao(String anotacao){
        this.anotacao = anotacao;
    }
}
