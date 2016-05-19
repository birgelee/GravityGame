/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gravitygame.block;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Henry
 */
public class PointMassBlock extends Block {
    
    public PointMassBlock(int x, int y) {
	super(x, y);
    }
    @Override
    public boolean getPassable() {
	return false;
    }

    @Override
    public void paint(Graphics g) {
	fillBlock(Color.red, g);
    }

    @Override
    public void update() {
	
    }

}
