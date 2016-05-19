/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import gravitygame.input.ButtonMouseListener;
import gravitygame.menu.button.ButtonEvent;
import gravitygame.render.Paintable;
import gravitygame.settings.Settings;
import gravitygame.ui.Component;
import gravitygame.utils.Convenience;

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
    
    
    
    @Override
    public MouseListener getMouseListener() {
	return new ButtonMouseListener(Settings.PAUSE_BUTTON_LOCATION[0], Settings.PAUSE_BUTTON_LOCATION[1], 20, 20, new ButtonEvent() {

            @Override
            public void pressed() {
                Convenience.initPauseMenu();
            }
            
        });
    }

    @Override
    public KeyListener getKeyListener() {
	return null;
    }
    
}
