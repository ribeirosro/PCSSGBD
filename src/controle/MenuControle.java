package controle;

import javax.swing.JOptionPane;

import visual.CalendarioVisual;
import visual.Login;
import visual.Menu;
import visual.MonetarioVisual;
import visual.NotasVisual;
import visual.TarefasVisual;

/**
 * 
 * @author Rodrigo
 *
 */
public class MenuControle {
	
	/**
	 * 
	 */
    public void MenuNotas(){
        NotasVisual form = new NotasVisual();
        form.setVisible(true);
    }
    
    /**
     * 
     */
    public void MenuTarefas(){
        TarefasVisual form = new TarefasVisual();
        form.setVisible(true);
    }
    
    /**
     * 
     */
    public void MenuCalendario(){
        CalendarioVisual form = new CalendarioVisual();
        form.setVisible(true);
    }
    
    /**
     * 
     */
    public void MenuMonetario(){
        MonetarioVisual form = new MonetarioVisual();
        form.setVisible(true);
    }
    
    /**
     * 
     * @param menu
     */
    public void LogOff(Menu menu){
        int opcao = JOptionPane.showConfirmDialog(menu,"Voce deseja fazer logoff?", "LOGOFF",JOptionPane.YES_NO_OPTION);
        
        if (opcao == JOptionPane.OK_OPTION){
            Login login = new Login();
            
            login.setVisible(true);
            
            menu.dispose();
        }
    }
}
