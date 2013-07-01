/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javathing.GameObject;
import javathing.render.PlatformerGraphicsUtil;
import javathing.settings.Settings;
import javathing.sprite.Sprite;


public abstract class Block extends GameObject {
    protected int xTileLocation;
    protected int yTileLocation;
    public abstract boolean getPassable();
    
    public Block(int x, int y) {
        this.xTileLocation = x;
        this.yTileLocation = y;
    }
    
    public int getX() {
        return xTileLocation * Settings.TILE_SIZE;
    }
    
    public int getY() {
        return yTileLocation * Settings.TILE_SIZE;
    }
    
    public int getXTileLocation() {
        return xTileLocation;
    }
    
    public int getYTileLocation() {
        return yTileLocation;
    }
    
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), Settings.TILE_SIZE, Settings.TILE_SIZE);
    }
    protected void fillBlock(Color color, Graphics g) {
	PlatformerGraphicsUtil.translateGraphics(g);
	g.setColor(color);
	g.fillRect(xTileLocation * Settings.TILE_SIZE, yTileLocation * Settings.TILE_SIZE, Settings.TILE_SIZE, Settings.TILE_SIZE);
	PlatformerGraphicsUtil.unTranslateGraphics(g);
    }
    public void onContact(Sprite interactor) {}
    
    public void whenInside(Sprite interactor) {}
}
