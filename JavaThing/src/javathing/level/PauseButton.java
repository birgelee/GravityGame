/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javathing.input.ButtonMouseListener;
import javathing.menu.ButtonEvent;
import javathing.render.Paintable;
import javathing.settings.Settings;
import javathing.ui.Component;
import javathing.utils.Convenience;

/**
 *
 * @author Henry
 */
public class PauseButton extends Component {

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(Settings.PAUSE_BUTTON_LOCATION[0], Settings.PAUSE_BUTTON_LOCATION[1], 20, 20);
    }
    
    public void addToLevel(LevelManager levelmanager) {
        levelmanager.addPaintable((Paintable)this, 4);
        levelmanager.addMouseListener(new ButtonMouseListener(Settings.PAUSE_BUTTON_LOCATION[0], Settings.PAUSE_BUTTON_LOCATION[1], 20, 20, new ButtonEvent() {

            @Override
            public void pressed() {
                Convenience.initMainMenu();
            }
            
        }));
	
    }
    
    
    @Override
    public MouseListener getMouseListener() {
	return new ButtonMouseListener(Settings.PAUSE_BUTTON_LOCATION[0], Settings.PAUSE_BUTTON_LOCATION[1], 20, 20, new ButtonEvent() {

            @Override
            public void pressed() {
                Convenience.initMainMenu();
            }
            
        });
    }

    @Override
    public KeyListener getKeyListener() {
	return null;
    }
    
}
