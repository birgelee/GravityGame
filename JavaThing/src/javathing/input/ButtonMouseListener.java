/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javathing.menu.ButtonEvent;

/**
 *
 * @author Henry
 */
public class ButtonMouseListener extends MouseAdapter {
    int x, y, width, height;
    ButtonEvent buttenEvent;
    public ButtonMouseListener(int x, int y, int width, int height, ButtonEvent buttenEvent) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttenEvent = buttenEvent;
    }
    

    @Override
    public void mouseReleased(MouseEvent me) {
        if (me.getX() > x && me.getX() < x + width && me.getY() > y && me.getY() < y + height) {
             buttenEvent.pressed();
        }
    }
    
}
