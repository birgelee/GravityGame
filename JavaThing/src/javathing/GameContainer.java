/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing;

import java.awt.Graphics;

/**
 *
 * @author Henry
 */
public interface GameContainer {
    public void paint(Graphics g);
    public void update();
    public String getName();
}
