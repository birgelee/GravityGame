/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javathing.GameObject;
import javathing.MainClass;
import javathing.level.OutOfLevelException;
import javathing.level.TileMap;
import javathing.render.PlatformerGraphicsUtil;
import javathing.settings.Settings;
import javax.imageio.ImageIO;

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
    private BlockSide BlockSide;

    public Sprite(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(Graphics g) {
        PlatformerGraphicsUtil.translateGraphics(g);
        if (getSpriteImage() != null) {
            g.drawImage(getSpriteImage(), (int) getX(), (int) getY(), null);
        } else {
            if (spriteColor != null) {
                g.setColor(spriteColor);
            } else {
                g.setColor(Color.red);
            }
            g.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
        }
        PlatformerGraphicsUtil.unTranslateGraphics(g);

    }

    public Rectangle getRectangle() {
        return new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }

    public void onContact(Sprite interactor) {
    }

    @Override
    public void update() {
        xVolocity += singleFrameXAcceleration * Settings.SLEEPTIME;
	yVolocity +=  singleFrameYAcceleration * Settings.SLEEPTIME;
        singleFrameXAcceleration = 0;
	singleFrameYAcceleration = 0;
        int tileX = TileMap.getTileLocation(getX());
        int tileY = TileMap.getTileLocation(getY());
        if (!checkInBound(tileX, MainClass.getLevelManager().getTileMap().xDimention) || !checkInBound(TileMap.getTileLocation(getX() + width), MainClass.getLevelManager().getTileMap().xDimention) || !checkInBound(tileY, MainClass.getLevelManager().getTileMap().yDimention) || !checkInBound(TileMap.getTileLocation(getY() + height), MainClass.getLevelManager().getTileMap().yDimention)) {
            throw new OutOfLevelException();
        }
        try {
            MainClass.getLevelManager().getTileMap().getBlock(tileX, tileY).whenInside(this);

            if (!(tileX == TileMap.getTileLocation(x + width) && tileY == TileMap.getTileLocation(y + height))) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() + getWidth()), TileMap.getTileLocation(getY() + getHeight())).whenInside(this);
            }

            if (!(tileX == TileMap.getTileLocation(x) && tileY == TileMap.getTileLocation(y + height))) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX()), TileMap.getTileLocation(getY() + getHeight())).whenInside(this);
            }

            if (!(tileX == TileMap.getTileLocation(x + width) && tileY == TileMap.getTileLocation(y))) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() + getWidth()), TileMap.getTileLocation(getY())).whenInside(this);
            }

            if (TileMap.isOnTile(getX() + width)) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() + width + 1), TileMap.getTileLocation(getY())).onContact(this, BlockSide.Left);
                if (TileMap.getTileLocation(getY()) != TileMap.getTileLocation(getY() + height)) {
                    MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() + width + 1), TileMap.getTileLocation(getY() + height)).onContact(this, BlockSide.Left);
                }
            }

            if (TileMap.isOnTile(getY())) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX()), TileMap.getTileLocation(getY() - 1)).onContact(this, BlockSide.Bottom);
                if (TileMap.getTileLocation(getX()) != TileMap.getTileLocation(getX() + width)) {
                    MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() + width), TileMap.getTileLocation(getY() - 1)).onContact(this, BlockSide.Bottom);
                }
            }

            if (TileMap.isOnTile(getX())) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() - 1), TileMap.getTileLocation(getY())).onContact(this, BlockSide.Right);
                if (TileMap.getTileLocation(getY()) != TileMap.getTileLocation(getY() + height)) {
                    MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() - 1), TileMap.getTileLocation(getY() + height)).onContact(this, BlockSide.Right);
                }
            }
            
            if (TileMap.isOnTile(getY() + height)) {
                MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX()), TileMap.getTileLocation(getY() + height + 1)).onContact(this, BlockSide.Top);
                if (TileMap.getTileLocation(getX()) != TileMap.getTileLocation(getX() + width)) {
                    MainClass.getLevelManager().getTileMap().getBlock(TileMap.getTileLocation(getX() + width), TileMap.getTileLocation(getY() + height + 1)).onContact(this, BlockSide.Top);
                }
            }

        } catch (NullPointerException ex) {
        }
    }

    private static boolean checkInBound(int tile, int max) {
        if (tile >= 0 && tile < max) {
            return true;
        }
        return false;
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

    public Point2D.Double getPosition() {
        return new Point2D.Double(x, y);
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the spriteImage
     */
    public Image getSpriteImage() {
        return spriteImage;
    }

    /**
     * @param spriteImage the spriteImage to set
     */
    public void setSpriteImage(Image spriteImage) {
        this.spriteImage = spriteImage;
    }
    
    public void setSpriteImageFromResourceLocation(String resourceLocation) {
        try {
            setSpriteImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(resourceLocation)));
        } catch (IOException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
