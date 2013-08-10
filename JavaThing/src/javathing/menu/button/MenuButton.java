/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.menu.button;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author Henry
 */
public abstract class MenuButton {
    public abstract void paint(Graphics g);
    public abstract void update();
    public abstract void onClick();
    public abstract boolean isClicked(MouseEvent me);
}
