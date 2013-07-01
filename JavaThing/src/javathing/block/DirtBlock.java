/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.block;

import java.awt.Color;
import java.awt.Graphics;
import javathing.render.PlatformerGraphicsUtil;
import javathing.settings.Settings;
import javathing.sprite.Player;
import javathing.sprite.Sprite;

/**
 *
 * @author lausd_user
 */
public class DirtBlock extends Block {

    public DirtBlock(int x, int y) {
        super(x, y);
    }
    
    @Override
    public boolean getPassable() {
        return false;
    }

    @Override
    public void paint(Graphics g) {
       PlatformerGraphicsUtil.translateGraphics(g);
       g.setColor(Color.blue);
       g.fillRect(getX(), getY(), Settings.TILE_SIZE, Settings.TILE_SIZE);
       PlatformerGraphicsUtil.unTranslateGraphics(g);
       
    }

    @Override
    public void update() {
    }
    
}
