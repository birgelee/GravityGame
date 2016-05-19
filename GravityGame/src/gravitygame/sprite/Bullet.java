/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.sprite;

import java.awt.Color;
import gravitygame.MainClass;
import gravitygame.level.OutOfLevelException;
import gravitygame.utils.MathUtils;

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
        try {
        super.update();
        } catch (OutOfLevelException ex) {
            MainClass.getLevelManager().removeSprite(this);
        }
    }

    @Override
    public void onContact(Sprite interactor) {
        super.onContact(interactor);
        if (interactor instanceof Shootable) {
            ((Shootable) interactor).shot(this);
            this.distroy();
        }
    }
    
    public void distroy() {
        MainClass.getLevelManager().removeSprite(this);
    }
    
    
}
