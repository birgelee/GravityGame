/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javathing.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javathing.MainClass;

/**
 *
 * @author Henry
 */
public class MenuMouseListener extends MouseAdapter{
    
    
    
    @Override
    public void mouseReleased(MouseEvent e) {
	MainClass.getMenuManager().handleMouseRelease(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
	//super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
    }
    
}
