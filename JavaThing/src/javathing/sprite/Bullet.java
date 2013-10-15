/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.sprite;

import java.awt.Color;
import javathing.MainClass;
import javathing.utils.MathUtils;

/**
 *
 * @author Henry
 */
public class Bullet extends Sprite {
    private double angle;
    public Bullet(double x, double y, double angle, double speed, double size, Color color) {
        super(x, y, size, size);
        this.xVolocity = MathUtils.getCordinates(angle)[0] * speed;
        this.yVolocity = MathUtils.getCordinates(angle)[1] * speed;
    }
    
    public Bullet(double x, double y, double angle, double speed, Color color) {
        this(x, y, angle, speed, 10, color);
    }

    @Override
    public void update() {
        this.setX(this.getX() + xVolocity);
        this.setY(this.getY() + yVolocity);
        if ((!MainClass.getLevelManager().getTileMap().isInTileGrid(getX(), getY()))|| (!MainClass.getLevelManager().getTileMap().isInTileGrid(getX() + getWidth(), getY() + getHeight()))) {
            MainClass.getLevelManager().removeSprite(this);
            return;
        }
        
        super.update();
    }

    @Override
    public void onContact(Sprite interactor) {
        super.onContact(interactor);
        if (interactor instanceof Shootable) {
            ((Shootable) interactor).shot(this);
        }
    }
    
    public void distroy() {
        MainClass.getLevelManager().removeSprite(this);
    }
    
    
}
