/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javathing.GameObject;
import javathing.MainClass;
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
            if (spriteColor != null)
                g.setColor(spriteColor);
            else
                g.setColor(Color.red);
            g.fillRect((int) x, (int) y, (int) width, (int) height);
        }
        PlatformerGraphicsUtil.unTranslateGraphics(g);
        
    }
    
    public Rectangle getRectangle() {
        return new Rectangle((int) x,(int) y, (int) width, (int) height);
    }
    
    
}
