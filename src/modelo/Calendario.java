package modelo;

import java.sql.Date;

/**
 * 
 * @author Rodrigo
 *
 */
public class Calendario {
    private String anotacao;
    private Date data;

    /**
     * 
     */
    public Calendario(){
        anotacao = "";
    }

    /**
     * 
     * @param anotacao
     * @param data
     */
    public Calendario(String anotacao, Date data){
        this.anotacao = anotacao;
        this.data = data;
    }

    /**
     * 
     * @return
     */
    public String getAnotacao(){
        return this.anotacao;
    }

    /**
     * 
     * @param anotacao
     */
    public void setAnotacao(String anotacao){
        this.anotacao = anotacao;
    }

    /**
     * 
     * @return
     */
    public Date getData(){
        return this.data;
    }

    /**
     * 
     * @param data
     */
    public void setData(Date data){
        this.data = data;
    }
}
