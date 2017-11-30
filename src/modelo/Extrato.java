package modelo;

/**
 * 
 * @author Rodrigo
 *
 */
public class Extrato {
    private String descricao;
    private String operacao;
    private double valor;

    /**
     * 
     */
    public Extrato() {
    }

    /**
     * 
     * @param descricao
     * @param operacao
     * @param valor
     */
    public Extrato(String descricao, String operacao, double valor) {
        this.descricao = descricao;
        this.operacao = operacao;
        this.valor = valor;
    }

    /**
     * 
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * 
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * 
     * @return
     */
    public String getOperacao() {
        return operacao;
    }

    /**
     * 
     * @param operacao
     */
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    /**
     * 
     * @return
     */
    public double getValor() {
        return valor;
    }

    /**
     * 
     * @param valor
     */
    public void setValor(double valor) {
        this.valor = valor;
    }    
    
}
