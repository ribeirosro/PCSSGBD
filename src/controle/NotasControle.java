package controle;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.Notas;
import sgbd.NotasDBA;
import visual.NotasVisual;

/**
 * 
 * @author Rodrigo
 *
 */
public class NotasControle {
    NotasDBA sgbdnotas = new NotasDBA();
        
    /**
     * 
     * @param lista
     */
    public void Visualizar(JList<String> lista){
        
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        
        ArrayList<Notas> arquivoNotas = sgbdnotas.visualizarNotas();
        
        for (Notas notas : arquivoNotas) dlm.addElement(notas.getAnotacao());
         
        lista.setModel(dlm);
    }
    
    /**
     * 
     * @param anotacao
     * @param form
     */
    public void Inserir(String anotacao, NotasVisual form){
        if (!emBranco(anotacao)){
            Notas notas = new Notas(anotacao);
            
            sgbdnotas.cadastrarNota(notas);
            
            JOptionPane.showMessageDialog(form,"Nota salva com sucesso.");
        } else JOptionPane.showMessageDialog(form,"Campo em branco."); 
        
    }
    
    /**
     * 
     * @param anotacaoAlterada
     * @param anotacao
     * @param form
     */
    public void Alterar(String anotacaoAlterada, String anotacao, NotasVisual form){
        if (!emBranco(anotacao)){
            int opcao = JOptionPane.showConfirmDialog(form,"Voce deseja alterar esta nota?", "Notas / Alterar",JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.OK_OPTION) sgbdnotas.alterarNota(anotacaoAlterada, anotacao);
            
        } else JOptionPane.showMessageDialog(form,"Por favor, escolha a nota que deseja alterar.");
    }
    
    /**
     * 
     * @param anotacao
     * @param form
     */
    public void Excluir(String anotacao, NotasVisual form){
        if (!emBranco(anotacao)){
            int opcao = JOptionPane.showConfirmDialog(form,"Voce deseja excluir esta nota?", "Notas / Excluir",JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.OK_OPTION) sgbdnotas.excluirNota(anotacao);
            
        } else JOptionPane.showMessageDialog(form,"Por favor, escolha a nota que deseja excluir.");
    }
    
    /**
     * 
     * @param lista
     * @param texto
     */
    public void EscolherTexto(JList<String> lista, JTextField texto){
        texto.setText((String)lista.getSelectedValue());
    }
    
    /**
     * 
     * @param notasVisual
     */
    public void Voltar(NotasVisual notasVisual){
        notasVisual.dispose();
    }
    
    /**
     * 
     * @param texto
     */
    public void Zerar(JTextField texto){
        texto.setText(null);
    }
    
    /* ---CRITICA DE DADOS--- */
    
    /**
     * 
     * @param anotacao
     * @return
     */
    private boolean emBranco(String anotacao){
        return anotacao.equals("");
    }
}
