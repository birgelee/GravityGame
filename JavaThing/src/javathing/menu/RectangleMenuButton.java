/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;

/**
 *
 * @author Henry
 */
public class RectangleMenuButton extends MenuButton {
    private Dimension size;
    private Point position;
    Color backgroundColor;
    BufferedImage backgroundImage;
    String text;
    
    ButtonEvent buttonEvent;
    public RectangleMenuButton(int x, int y, int width, int height, Color color, String text, ButtonEvent buttonEvent) {
        size = new Dimension(width, height);
        position = new Point(x, y);
        this.backgroundColor = color;
        this.buttonEvent = buttonEvent;
    }
    public RectangleMenuButton(int x, int y, int width, int height, BufferedImage backgroundImage, String text, ButtonEvent buttonEvent) {
        size = new Dimension(width, height);
        position = new Point(x, y);
        this.backgroundImage = backgroundImage;
        this.buttonEvent = buttonEvent;
    }

    @Override
    public void paint(Graphics g) {
        if (backgroundColor != null) {
        g.setColor(backgroundColor);
        g.fillRect(position.x, position.y, size.width, size.height);
        }
        else {
            g.drawImage(backgroundImage, position.x, position.y, null);
        }
        
        FontMetrics fm = g.getFontMetrics();
        g.drawString(text, position.x + (size.width - fm.stringWidth(text)) / 2, position.y + 5);
            
        
    }

    @Override
    public void onClick() {
        buttonEvent.pressed();
        }

    @Override
    public boolean isClicked(MouseEvent me) {
        if (me.getX() > position.x && me.getX() < position.x + size.width) {
            if (me.getY() > position.y && me.getY() < position.y + size.height) {
                return true;
            }
        }
        return false;
    }
}
