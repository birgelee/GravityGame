/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javathing.block;

import java.awt.Color;
import java.awt.Graphics;
import javathing.settings.GameplaySettings;
import javathing.sprite.BlockSide;
import javathing.sprite.Player;
import javathing.sprite.Sprite;

/**
 *
 * @author Henry
 */
public class Accelerator extends Block {
    double strength;
    
    
    public Accelerator(int x, int y, double strength) {
	super(x, y);
	this.strength = strength;
    }
    public Accelerator(int x, int y) {
	this(x, y, GameplaySettings.ACCELERATOR_STRENGTH);
    }
    @Override
    public boolean getPassable() {
	return false;
    }

    @Override
    public void paint(Graphics g) {
	fillBlock(Color.yellow, g);
    }

    @Override
    public void update() {
	
    }

    @Override
    public void onContact(Sprite interactor, BlockSide blockSide) {
	switch (blockSide) {
	    case Top:
		interactor.addSingleFrameAcceleration(strength, 0);
		break;
	    case Right:
		interactor.addSingleFrameAcceleration(0, -strength);
	    case Bottom:
		interactor.addSingleFrameAcceleration(-strength, 0);
	    case Left:
		interactor.addSingleFrameAcceleration(0, strength);
	}
	
	    
    }
    

}
