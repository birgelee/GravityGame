/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing;

import java.awt.Graphics;
import javax.swing.JApplet;

/**
 *
 * @author lausd_user
 */
public class MainApplet extends JApplet {
    
    //private LevelManager levelManager = MainClass.getLevelManager();
    
    @Override
    public void paint(Graphics g) {
        MainClass.getContainer().paint(g);
        
        /*this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent ke) {
                System.out.println("key pressed");
            }
            
        });*/
        
    }
}
