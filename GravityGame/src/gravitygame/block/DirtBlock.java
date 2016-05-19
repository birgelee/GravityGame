/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.block;

import java.awt.Color;
import java.awt.Graphics;
import gravitygame.render.PlatformerGraphicsUtil;
import gravitygame.settings.Settings;
import gravitygame.sprite.Player;
import gravitygame.sprite.Sprite;

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
