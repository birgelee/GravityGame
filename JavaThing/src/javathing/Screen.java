/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing;

import java.awt.Point;
import java.awt.event.KeyListener;
import javathing.input.ScreenKeyListener;

/**
 *
 * @author lausd_user
 */
public class Screen implements Updateable {

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
    @Override
    public void update() {
	if (skl.getR()) {
	    rotation += .01;
	}
	if (skl.getE()) {
	    rotation -= .01;
	}
    }
    double rotation; //in radions
    public double getTilt() {
	//return -MathUtils.getAngle(GameplaySettings.ACCELERATION_DUE_TO_GRAVITY[0], GameplaySettings.ACCELERATION_DUE_TO_GRAVITY[1]);
	return rotation;
    }
    ScreenKeyListener skl = new ScreenKeyListener();
    public KeyListener getKeyListener() {
	return skl;
    }
}
