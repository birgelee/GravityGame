/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javathing.block;

import javathing.sprite.BlockSide;
import javathing.sprite.Player;
import javathing.sprite.Sprite;

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
    public void onContact(Sprite interactor, BlockSide side) {
	if (interactor instanceof Player) {
	    ((Player)interactor).kill();
	}
    }

}
