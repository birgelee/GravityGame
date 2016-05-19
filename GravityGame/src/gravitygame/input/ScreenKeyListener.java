/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Henry
 */
public class ScreenKeyListener extends KeyAdapter {

    private boolean r;

    public boolean getR() {
	return r;
    }
    private boolean e;

    public boolean getE() {
	return e;
    }

    @Override
    public void keyPressed(KeyEvent ke) {
	if (ke.getKeyCode() == KeyEvent.VK_E) {
	    e = true;
	}
	if (ke.getKeyCode() == KeyEvent.VK_R) {
	    r = true;
	}
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
	if (ke.getKeyCode() == KeyEvent.VK_E) {
	    e = false;
	}
	if (ke.getKeyCode() == KeyEvent.VK_R) {
	    r = false;
	}
    }
}
