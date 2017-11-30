package controle;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import modelo.Extrato;
import sgbd.ExtratoDBA;

/**
 * 
 * @author Rodrigo
 *
 */
public class ExtratoControle {
    ExtratoDBA sgbdextrato = new ExtratoDBA();
    
    /**
     * 
     * @param lista
     */
    public void salvarExtrato(ArrayList<Extrato> lista){
        sgbdextrato.cadastrarExtrato(lista);
        
    }
    
    /**
     * 
     * @param lista
     */
    public void mostrarExtrato(JList<String> lista){
        
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        
        ArrayList<Extrato> arquivoExtrato = sgbdextrato.visualizarTarefas();
        
        for (Extrato extrato : arquivoExtrato) dlm.addElement(extrato.getOperacao() + ": " + extrato.getValor() + " - " + extrato.getDescricao());
        
        lista.setModel(dlm);
    }
}
