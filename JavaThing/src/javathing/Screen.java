/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing;

import java.awt.Point;

/**
 *
 * @author lausd_user
 */
public class Screen {

    private Point screenPosition;

    public Screen(int x, int y) {
        screenPosition = new Point(x, y);
    }

    public void scroll(int x, int y) {
        screenPosition.x += x;
        screenPosition.y += y;
    }

    public Point getScreenPosition() {
        return screenPosition;
    }
    
    public void setScreenPosition(int x, int y) {
        setScreenPosition(new Point(x, y));
        
    }
    
    public void setScreenPosition(Point p) {
        screenPosition = p;
    }
}
