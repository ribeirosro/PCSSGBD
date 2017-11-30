package controle;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import modelo.Calendario;
import sgbd.CalendarioDBA;
import visual.CalendarioVisual;

/**
 * 
 * @author Rodrigo
 *
 */
public class CalendarioControle {
    CalendarioDBA sgbdcalendario = new CalendarioDBA();
    
    /**
     * 
     * @param lista
     */
    public void Visualizar(JList<String> lista){
                
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        
        ArrayList<Calendario> arquivoCalendario = sgbdcalendario.visualizarCalendario(); 
        
        for (Calendario aux : arquivoCalendario) {
        	String[] day = aux.getData().toString().split("-");
        	dlm.addElement(day[2]+"/"+day[1]+"/"+day[0] + " - " + aux.getAnotacao());
        }
        
        lista.setModel(dlm);
    }
    
    /**
     * 
     * @param anotacao
     * @param data
     * @param form
     * @throws ParseException
     */
    public void Inserir(String anotacao, Date data, CalendarioVisual form) throws ParseException{
        if (!emBranco(anotacao)){
            Calendario calendario = new Calendario(anotacao, data);
            
            sgbdcalendario.cadastrarCalendario(calendario);
            
            JOptionPane.showMessageDialog(form,"Evento salvo com sucesso.");
        } else JOptionPane.showMessageDialog(form,"Campo(s) em branco.");
        
    }
    
    /**
     * 
     * @param anotacao
     * @param anotacaoAlterada
     * @param dataAlterada
     * @param form
     */
    public void Alterar(String anotacao, String anotacaoAlterada, Date dataAlterada, CalendarioVisual form){
        if (!emBranco(anotacao)){
            int opcao = JOptionPane.showConfirmDialog(form,"Voce deseja alterar este evento?", "Calendario / Alterar",JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.OK_OPTION) {
            	try {
            		String[] aux = anotacao.substring(0, 10).split("/");
            		String data = aux[2]+"-"+aux[1]+"-"+aux[0];
            		
            		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            		
            		java.sql.Date d = new java.sql.Date(formato.parse(data).getTime());
            		
            		sgbdcalendario.alterarCalendario(anotacaoAlterada, anotacao.substring(12).trim(), dataAlterada, d);
            	} catch (Exception e) {
            		System.out.println("Erro " + e.getMessage());
            	}
            }
            	
        } else JOptionPane.showMessageDialog(form,"Por favor, escolha o evento que deseja alterar.");
    }
    
    /**
     * 
     * @param anotacao
     * @param form
     */
    public void Excluir(String anotacao, CalendarioVisual form){
        if (!emBranco(anotacao)){
            int opcao = JOptionPane.showConfirmDialog(form,"Voce deseja excluir este evento?", "Calendario / Excluir",JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.OK_OPTION) {
            	try {
            		String[] aux = anotacao.substring(0, 10).split("/");
            		String data = aux[2]+"-"+aux[1]+"-"+aux[0];
            		
            		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            	
            		java.sql.Date d = new java.sql.Date(formato.parse(data).getTime());
        		
                
            		sgbdcalendario.excluirCalendario(anotacao.substring(12).trim(), d);
            	} catch (Exception e) {
            		System.out.println("Erro" + e.getMessage());
            		
            	}
            }
     
        } else JOptionPane.showMessageDialog(form,"Por favor, escolha o evento que deseja excluir.");
    }
    
    /**
     * 
     * @param lista
     * @param texto
     * @param data
     * @throws ParseException
     */
    public void EscolherTexto(JList<String> lista, JTextField texto, JDateChooser data) throws ParseException{
        String aux = (String)lista.getSelectedValue();
        
        String aux1 = aux.substring(0, 10);
        String aux2 = aux.substring(12);
        
        texto.setText(aux2.trim());
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        data.setDate(formato.parse(aux1));
        
    }
    
    /**
     * 
     * @param calendarioVisual
     */
    public void Voltar(CalendarioVisual calendarioVisual){
        calendarioVisual.dispose();
    }
    
    /**
     * 
     * @param texto
     * @param data
     */
    public void Zerar(JTextField texto, JDateChooser data){
        texto.setText(null);
        
        data.setDate(null);
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
