/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javathing.settings.Settings;

/**
 *
 * @author Henry
 */
public class MenuManager {
    List<MenuButton> buttons;
    public MenuManager(List<MenuButton> buttons, Color bgColor) {
        this.buttons = buttons;
    }
    
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT);
        for (MenuButton b : buttons) {
            b.paint(g);
        }
    }
}
