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
    protected double xVolocity;
    protected double yVolocity;
    protected double singleFrameXVolocity;
    protected double singleFrameYVolocity;
    protected double singleFrameXAcceleration;
    protected double singleFrameYAcceleration;
    
    public Sprite(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(Graphics g) {
        PlatformerGraphicsUtil.translateGraphics(g);
        if (spriteImage != null) {
            g.drawImage(spriteImage, (int) getX(), (int) getY(), null);
        } else {
            if (spriteColor != null) {
                g.setColor(spriteColor);
            } else {
                g.setColor(Color.red);
            }
            g.fillRect((int) getX(), (int) getY(), (int) width, (int) height);
        }
        PlatformerGraphicsUtil.unTranslateGraphics(g);

    }

    public Rectangle getRectangle() {
        return new Rectangle((int) getX(), (int) getY(), (int) width, (int) height);
    }

    @Override
    public void update() {
        int tileX = TileMap.getTileLocation(getX());
        int tileY = TileMap.getTileLocation(getY());
        MainClass.getLevelManager().getTileMap().getBlock(tileX, tileY).whenInside(this);

        if (!(tileX == TileMap.getTileLocation(x + width) && tileY == TileMap.getTileLocation(y + height))) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() + width), TileMap.getTileLocation(getY() + height)).whenInside(this);
        }

        if (!(tileX == TileMap.getTileLocation(x) && tileY == TileMap.getTileLocation(y + height))) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX()), TileMap.getTileLocation(getY() + height)).whenInside(this);
        }

        if (!(tileX == TileMap.getTileLocation(x + width) && tileY == TileMap.getTileLocation(y))) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() + width), TileMap.getTileLocation(getY())).whenInside(this);
        }

        if (TileMap.isOnTile(getX() + width)) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() + width + 1), TileMap.getTileLocation(getY())).onContact(this);
            if (TileMap.getTileLocation(getY()) != TileMap.getTileLocation(getY() + height)) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() + width + 1), TileMap.getTileLocation(getY() + height)).onContact(this);
            }
        }

        if (TileMap.isOnTile(getY())) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX()), TileMap.getTileLocation(getY() - 1)).onContact(this);
            if (TileMap.getTileLocation(getX()) != TileMap.getTileLocation(getX() + width)) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() + width), TileMap.getTileLocation(getY() - 1)).onContact(this);
            }
        }

        if (TileMap.isOnTile(getX())) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() - 1), TileMap.getTileLocation(getY())).onContact(this);
            if (TileMap.getTileLocation(getY()) != TileMap.getTileLocation(getY() + height)) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() - 1), TileMap.getTileLocation(getY() + height)).onContact(this);
            }
        }

        if (TileMap.isOnTile(getY() + height)) {
            MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX()), TileMap.getTileLocation(getY() + height + 1)).onContact(this);
            if (TileMap.getTileLocation(getX()) != TileMap.getTileLocation(getX() + width)) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() + width), TileMap.getTileLocation(getY() + height + 1)).onContact(this);
            }
        }



    }
    
    public void addSingleFrameAcceleration(double xAccelerationToAdd, double yAccelerationToAdd) {
	singleFrameXAcceleration += xAccelerationToAdd;
	singleFrameYAcceleration += yAccelerationToAdd;
    }
    
    public void addSingleFrameVolocity(double xVolocityToAdd, double yVolocityToAdd) {
	singleFrameXVolocity += xVolocityToAdd;
	singleFrameYVolocity += yVolocityToAdd;
    }
    
    /**
     * @return the x
     */
    public double getX() {
	return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
	this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
	return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
	this.y = y;
    }
}
