package controle;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.Extrato;
import sgbd.MonetarioDBA;
import visual.ExtratoVisual;
import visual.MonetarioVisual;

/**
 * 
 * @author Rodrigo
 *
 */
public class MonetarioControle {
    MonetarioDBA sgbdmonetario = new MonetarioDBA();
    
    private double saldo;
    private final ArrayList<String> historico = new ArrayList<>();
    
    private final ExtratoControle controleExtrato = new ExtratoControle();
    private final ArrayList<Extrato> listaExtrato = new ArrayList<>();
    
    /**
     * 
     */
    public MonetarioControle(){
        saldo = getValor();
    }
    
    /**
     * 
     * @return
     */
    private double getValor(){
        return sgbdmonetario.visualizarSaldo();
    }
    
    /**
     * 
     * @param form
     */
    public void salvar(MonetarioVisual form){
        
    	if (sgbdmonetario.jahExiste()) sgbdmonetario.alterarSaldo(saldo);
    	else sgbdmonetario.cadastrarSaldo(saldo);
        
        addExtrato();
        
        JOptionPane.showMessageDialog(form,"Saldo salvo com sucesso.");
    }
    
    /**
     * 
     * @param form
     */
    public void zerar(MonetarioVisual form){
        int opcao = JOptionPane.showConfirmDialog(form,"Voce deseja zerar o seu saldo?", "Monetario / Zerar",JOptionPane.YES_NO_OPTION);
        
        if (opcao == JOptionPane.OK_OPTION){
            this.saldo = 0.0;

            if (sgbdmonetario.jahExiste()) sgbdmonetario.excluirSaldo();
            
            historico.add("Saldo zerado");
        }
    }
    
    /**
     * 
     * @param valor
     * @param descricao
     * @param monetario
     */
    public void adicionar(String valor, String descricao, MonetarioVisual monetario){
        if (!emBranco(valor) && !temLetra(valor)) 
        {
            this.saldo += Double.parseDouble(valor);
            historico.add("Deposito: " + valor);
            listaExtrato.add(new Extrato(descricao, "Deposito", Double.parseDouble(valor)));
        } else JOptionPane.showMessageDialog(monetario,"Por favor, apenas some numeros");         
    }
    
    /**
     * 
     * @param valor
     * @param descricao
     * @param monetario
     */
    public void subtrair(String valor, String descricao, MonetarioVisual monetario){
        if (!emBranco(valor) && !temLetra(valor)) {
            this.saldo -= Double.parseDouble(valor);
            historico.add("Despesa: " + valor);
            listaExtrato.add(new Extrato(descricao, "Despesa", Double.parseDouble(valor)));
        } else JOptionPane.showMessageDialog(monetario,"Por favor, apenas subtraia numeros");                
    }
    
    /**
     * 
     * @param lista
     */
    public void visualizar(JList<String> lista){
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        
        for (String string : historico)
            dlm.addElement(string);
        
        lista.setModel(dlm);
    }
    
    /**
     * 
     * @param campoValor
     * @param campoDescricao
     */
    public void limpar(JTextField campoValor, JTextField campoDescricao){
        campoValor.setText("");
        campoDescricao.setText("");
    }
    
    /**
     * 
     * @param saldo
     */
    public void mostrarSaldo(JLabel saldo){
        saldo.setText("Seu saldo é de R$ " + this.saldo);
    }
    
    /**
     * 
     * @param monetarioVisual
     */
    public void Voltar(MonetarioVisual monetarioVisual){
        monetarioVisual.dispose();
    }
    
    /**
     * 
     */
    public void abrirExtrato(){
        ExtratoVisual form = new ExtratoVisual();
        form.setVisible(true);
    }
    
    /**
     * 
     */
    private void addExtrato(){
        controleExtrato.salvarExtrato(listaExtrato);
        
    }
    
    /* ---CRITICA DE DADOS---*/
    
    /**
     * 
     * @param valor
     * @return
     */
    private boolean emBranco(String valor){
        return valor.equals("");
    }
    
    /**
     * 
     * @param valor
     * @return
     */
    private boolean temLetra(String valor){        
        char[] aux = valor.toCharArray();
        boolean letra = false;
        
        for (int i = 0; i < aux.length; i++){
            letra = letra || Character.isLetter(aux[i]);
        }
        
        return letra;
    }
}
