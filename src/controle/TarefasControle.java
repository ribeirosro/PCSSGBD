package controle;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.Tarefas;
import sgbd.TarefasDBA;
import visual.TarefasVisual;

/**
 * 
 * @author Rodrigo
 *
 */
public class TarefasControle {
    TarefasDBA sgbdtarefas = new TarefasDBA();
    
    /**
     * 
     * @param lista
     */
    public void Visualizar(JList<String> lista){
        
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        
        ArrayList<Tarefas> arquivoTarefas = sgbdtarefas.visualizarTarefas();   
        
        for (Tarefas aux : arquivoTarefas) dlm.addElement(aux.getPrioridade() + " - " + aux.getAnotacao());
        
        lista.setModel(dlm);
    }
    
    /**
     * 
     * @param anotacao
     * @param propriedade
     * @param form
     */
    public void Inserir(String anotacao, int propriedade, TarefasVisual form){
        if (!emBranco(anotacao)){
            Tarefas tarefa = new Tarefas(anotacao, propriedade);
            
            sgbdtarefas.cadastrarTarefa(tarefa);
            
            JOptionPane.showMessageDialog(form,"Tarefa salva com sucesso.");
        } else JOptionPane.showMessageDialog(form,"Campo(s) em branco.");
        
    }
    
    /**
     * 
     * @param anotacao
     * @param anotacaoAlterada
     * @param prioridadeAlterada
     * @param form
     */
    public void Alterar(String anotacao, String anotacaoAlterada, int prioridadeAlterada, TarefasVisual form){
        if (!emBranco(anotacao)){
            int opcao = JOptionPane.showConfirmDialog(form,"Voce deseja alterar esta tarefa?", "Tarefas / Alterar",JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.OK_OPTION) sgbdtarefas.alterarTarefa(anotacaoAlterada, anotacao.substring(4), prioridadeAlterada, Integer.parseInt(anotacao.substring(0,1)));
            
        } else JOptionPane.showMessageDialog(form,"Por favor, escolha a tarefa que deseja alterar.");
    }
    
    /**
     * 
     * @param anotacao
     * @param form
     */
    public void Excluir(String anotacao, TarefasVisual form){
        if (!emBranco(anotacao)){
            int opcao = JOptionPane.showConfirmDialog(form,"Voce deseja excluir esta tarefa?", "Tarefas / Excluir",JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.OK_OPTION) sgbdtarefas.excluirTarefa(anotacao.substring(4), Integer.parseInt(anotacao.substring(0,1)));
            
        } else JOptionPane.showMessageDialog(form,"Por favor, escolha a tarefa que deseja excluir.");
    }

    /**
     * 
     * @param lista
     * @param texto
     * @param prioridade
     */
    public void EscolherTexto(JList<String> lista, JTextField texto, JComboBox<String> prioridade){
        String aux = lista.getSelectedValue().toString();
        
        String aux1 = aux.substring(0, 1); //Pegar a prioridade
        String aux2 = aux.substring(4); //Pegar a anotacao
        
        texto.setText(aux2);
        prioridade.setSelectedIndex(Integer.parseInt(aux1)-1);
        
    }

    /**
     * 
     * @param tarefasVisual
     */
    public void Voltar(TarefasVisual tarefasVisual){
        tarefasVisual.dispose();
    }

    /**
     * 
     * @param texto
     * @param prioridade
     */
    public void Zerar(JTextField texto, JComboBox<String> prioridade){
        texto.setText(null);
        
        prioridade.setSelectedIndex(0);
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
