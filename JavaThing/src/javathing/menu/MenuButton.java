/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Henry
 */
public abstract class MenuButton {
    public abstract void paint(Graphics g);
    public abstract void onClick();
    public abstract boolean isClicked(MouseEvent me);
}
