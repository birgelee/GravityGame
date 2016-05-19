/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gravitygame.block;

import gravitygame.sprite.BlockSide;
import gravitygame.sprite.Player;
import gravitygame.sprite.Sprite;

/**
 *
 * @author Henry
 */
public class Spike extends StandardBlock {
    public Spike (int x, int y) {
	super(x, y);
	this.resourceLocation = "javathing/resources/graphics/block/spike.png";
	
    }

    @Override
    public boolean getPassable() {
        return true;
    }
    
    @Override
    public void onContact(Sprite interactor, BlockSide side) {
	if (interactor instanceof Player) {
	    ((Player)interactor).kill();
	}
    }

    @Override
    public void whenInside(Sprite interactor) {
        if (interactor instanceof Player) {
	    ((Player)interactor).kill();
	}
    }
}
