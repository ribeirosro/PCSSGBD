package modelo;

/**
 * 
 * @author Rodrigo
 *
 */
public class Monetario {
    private double saldo;

    /**
     * 
     */
    public Monetario(){
        saldo = 0;
    }

    /**
     * 
     * @param saldo
     */
    public Monetario(double saldo){
        this.saldo = saldo;
    }

    /**
     * 
     * @return
     */
    public double getValor(){
        return this.saldo;
    }

    /**
     * 
     * @param valor
     */
    public void setValor(double valor){
        this.saldo = valor;
    }
    
}
