/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javathing.MainClass;

/**
 *
 * @author lausd_user
 */
public class PlayerKeyListener extends KeyAdapter {

    private boolean[] arrowKeys = new boolean[4];
    private boolean space = false;
    private boolean[] dandf = new boolean[2];

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
	    
	    case KeyEvent.VK_F:
		dandf[1] = true;
		break;
	    case KeyEvent.VK_D:
		MainClass.getPlayer().decreaseMass();
		break;
            case KeyEvent.VK_LEFT:
                arrowKeys[0] = true;
                //MainClass.getPlayer().arrowKeyReleased(0);
                break;
            case KeyEvent.VK_RIGHT:
                arrowKeys[1] = true;
                //MainClass.getPlayer().arrowKeyReleased(1);
                break;
            case KeyEvent.VK_UP:
                arrowKeys[2] = true;
                //MainClass.getPlayer().arrowKeyReleased(2);
                break;

            case KeyEvent.VK_DOWN:
                arrowKeys[3] = true;
                //MainClass.getPlayer().arrowKeyReleased(3);
                break;
            case KeyEvent.VK_SPACE:
                space = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

        switch (ke.getKeyCode()) {
	    case KeyEvent.VK_F:
		MainClass.getPlayer().undoMassIncrease();
		break;
	    case KeyEvent.VK_D:
		MainClass.getPlayer().undoMassDecrease();
		break;
            case KeyEvent.VK_LEFT:
                arrowKeys[0] = false;
                //MainClass.getPlayer().arrowKeyReleased(0);
                break;
            case KeyEvent.VK_RIGHT:
                arrowKeys[1] = false;
                //MainClass.getPlayer().arrowKeyReleased(1);
                break;
            case KeyEvent.VK_UP:
                arrowKeys[2] = false;
                //MainClass.getPlayer().arrowKeyReleased(2);
                break;

            case KeyEvent.VK_DOWN:
                arrowKeys[3] = false;
                //MainClass.getPlayer().arrowKeyReleased(3);
                break;
            case KeyEvent.VK_SPACE:
                space = false;
                break;

        }
    }

    /**
     * @return the arrowKeys
     */
    public boolean[] getArrowKeys() {
        return arrowKeys;
    }

    /**
     * @return the space
     */
    public boolean isSpace() {
        return space;
    }
}