/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.sprite;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.LinkedList;

/**
 *
 * @author Henry
 */
public class Vilin extends Sprite {

    LinkedList<Point2D.Double> path;
    private double speed;

    public Vilin(double x, double y, double speed, LinkedList<Point2D.Double> path) {

        super(x, y, 30, 30);
        this.spriteColor = Color.white;
        this.speed = speed;
        this.path = path;
    }

    @Override
    public void onContact(Sprite interactor) {
        if (interactor instanceof Player) {
            ((Player) interactor).kill();
        }
    }

    @Override
    public void update() {
        super.update();
        if (path.size() != 0) {
            if (this.getPosition().distance(path.getFirst()) < 2) {
                this.x = path.getFirst().x;
                this.y = path.getFirst().y;
                path.pop();
            } else {
                Point2D.Double motionVector = (Point2D.Double) path.getFirst().clone();
                motionVector.x -= this.getX();
                motionVector.y -= this.getY();
                double scalingFactor = speed / (new Point2D.Double()).distance(motionVector);
                double deltax = motionVector.x * scalingFactor;
                double deltay = motionVector.y * scalingFactor;
                this.x += deltax;
                this.y += deltay;

            }
        }

    }
}
