package modelo;

/**
 * 
 * @author Rodrigo
 *
 */
public class Tarefas {
    private String anotacao;
    private int prioridade;

    /**
     * 
     */
    public Tarefas(){
        anotacao = "";
        prioridade = 0;
    }

    /**
     * 
     * @param anotacao
     * @param prioridade
     */
    public Tarefas(String anotacao, int prioridade){
        this.anotacao = anotacao;
        this.prioridade = prioridade;
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

    /**
     * 
     * @return
     */
    public int getPrioridade(){
        return prioridade;
    }

    /**
     * 
     * @param prioridade
     */
    public void setPrioridade(int prioridade){
        this.prioridade = prioridade;
    }
}
