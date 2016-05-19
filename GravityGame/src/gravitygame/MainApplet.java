/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame;

import java.awt.Color;
import java.awt.Graphics;
import gravitygame.settings.Settings;
import javax.swing.JApplet;

/**
 *
 * @author lausd_user
 */
public class MainApplet extends JApplet {
    
    //private LevelManager levelManager = MainClass.getLevelManager();
    boolean fristTime = true;
    @Override
    public void paint(Graphics g) {
        if (fristTime) {
            fristTime = false;
            g.setColor(Color.black);
            g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT);
        }
        MainClass.getContainer().paint(g);
        
    }
}
