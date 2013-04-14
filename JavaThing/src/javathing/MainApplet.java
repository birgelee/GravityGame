/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing;

import javathing.settings.Settings;
import javathing.render.Paintable;
import javathing.level.LevelManager;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JApplet;

/**
 *
 * @author lausd_user
 */
public class MainApplet extends JApplet {
    
    //private LevelManager levelManager = MainClass.getLevelManager();
    
    @Override
    public void paint(Graphics g) {
        MainClass.getContainer().update();
        MainClass.getContainer().paint(g);
        
        
    }
}
