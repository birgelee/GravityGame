/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import javathing.GameObject;
import javathing.MainClass;
import javathing.level.TileMap;
import javathing.render.PlatformerGraphicsUtil;

/**
 *
 * @author lausd_user
 */
public abstract class Sprite extends GameObject {

    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected Color spriteColor;
    protected Image spriteImage;

    public Sprite(double x, double y, double width, double height, boolean autoAddToLevel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if (autoAddToLevel) {
            MainClass.getLevelManager().addSprite(this);
        }
    }

    public Sprite(double x, double y, double width, double height) {
        this(x, y, width, height, true);
    }

    @Override
    public void paint(Graphics g) {
        PlatformerGraphicsUtil.translateGraphics(g);
        if (spriteImage != null) {
            g.drawImage(spriteImage, (int) x, (int) y, null);
        } else {
            if (spriteColor != null) {
                g.setColor(spriteColor);
            } else {
                g.setColor(Color.red);
            }
            g.fillRect((int) x, (int) y, (int) width, (int) height);
        }
        PlatformerGraphicsUtil.unTranslateGraphics(g);

    }

    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    @Override
    public void update() {
        int tileX = TileMap.getTileLocation(x);
        int tileY = TileMap.getTileLocation(y);
        MainClass.getLevelManager().getTileMap().getBlock(tileX, tileY).whenInside(this);

        if (!(tileX == TileMap.getTileLocation(x + width) && tileY == TileMap.getTileLocation(y + height))) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(x + width), TileMap.getTileLocation(y + height)).whenInside(this);
        }

        if (!(tileX == TileMap.getTileLocation(x) && tileY == TileMap.getTileLocation(y + height))) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(x), TileMap.getTileLocation(y + height)).whenInside(this);
        }

        if (!(tileX == TileMap.getTileLocation(x + width) && tileY == TileMap.getTileLocation(y))) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(x + width), TileMap.getTileLocation(y)).whenInside(this);
        }

        if (TileMap.isOnTile(x + width)) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(x + width + 1), TileMap.getTileLocation(y)).onContact(this);
            if (TileMap.getTileLocation(y) != TileMap.getTileLocation(y + height)) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(x + width + 1), TileMap.getTileLocation(y + height)).onContact(this);
            }
        }

        if (TileMap.isOnTile(y)) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(x), TileMap.getTileLocation(y - 1)).onContact(this);
            if (TileMap.getTileLocation(x) != TileMap.getTileLocation(x + width)) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(x + width), TileMap.getTileLocation(y - 1)).onContact(this);
            }
        }

        if (TileMap.isOnTile(x)) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(x - 1), TileMap.getTileLocation(y)).onContact(this);
            if (TileMap.getTileLocation(y) != TileMap.getTileLocation(y + height)) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(x - 1), TileMap.getTileLocation(y + height)).onContact(this);
            }
        }

        if (TileMap.isOnTile(y + height)) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(x), TileMap.getTileLocation(y + height + 1)).onContact(this);
            if (TileMap.getTileLocation(x) != TileMap.getTileLocation(x + width)) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(x + width), TileMap.getTileLocation(y + height + 1)).onContact(this);
            }
        }



    }
}
