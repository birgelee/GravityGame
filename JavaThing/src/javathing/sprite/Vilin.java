/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.sprite;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import javathing.load.PopulationLoader;
import javathing.load.TokenResolver;

/**
 *
 * @author Henry
 */
public class Vilin extends Sprite {

    LinkedList<Point2D.Double> path;
    ArrayList<Point2D.Double> loop;
    private double speed;
    private int loopPoint;

    public Vilin(double x, double y, double speed, LinkedList<Point2D.Double> path, ArrayList<Point2D.Double> loop) {

        super(x, y, 30, 30);
        this.spriteColor = Color.white;
        this.speed = speed;
        this.path = path;
        this.loop = loop;
    }
    
    public static Vilin getFromArgs(String args, TokenResolver tokenResolver) throws Exception {
        Object[] objArgs = PopulationLoader.getObjectsFromParams(args, tokenResolver);
        double x = ((Double[]) objArgs[0])[0], y = ((Double[]) objArgs[0])[1];
        double speed = PopulationLoader.extrapolateToDouble(objArgs[2]);
        LinkedList<Point2D.Double> path = new LinkedList<Point2D.Double>(PopulationLoader.doubleArrayToPointList(objArgs[3]));
        ArrayList<Point2D.Double> loop = new ArrayList<Point2D.Double>(PopulationLoader.doubleArrayToPointList(objArgs[4]));
        return new Vilin(x,y,speed, path, loop);
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
        if (!path.isEmpty()) {
            if (this.getPosition().distance(path.getFirst()) < 2) {
                this.x = path.getFirst().x;
                this.y = path.getFirst().y;
                path.pop();
            } else {
                moveTo(path.getFirst());

            }
        } else if (!loop.isEmpty()) {
            if (this.getPosition().distance(loop.get(loopPoint)) < 2) {
                this.x = loop.get(loopPoint).x;
                this.y = loop.get(loopPoint).y;
                if (loopPoint + 1 < loop.size()) {
                    loopPoint++;
                } else {
                    loopPoint = 0;
                }
            } else {
                moveTo(loop.get(loopPoint));
            }
        }

    }

    private void moveTo(Point2D.Double location) {
        Point2D.Double motionVector = (Point2D.Double) location.clone();
        motionVector.x -= this.getX();
        motionVector.y -= this.getY();
        double scalingFactor = speed / (new Point2D.Double()).distance(motionVector);
        double deltax = motionVector.x * scalingFactor;
        double deltay = motionVector.y * scalingFactor;
        this.x += deltax;
        this.y += deltay;
    }
}
