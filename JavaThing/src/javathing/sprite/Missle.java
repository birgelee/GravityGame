/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.sprite;

import java.awt.Color;
import java.awt.geom.Point2D;
import javathing.MainClass;
import javathing.level.OutOfLevelException;
import javathing.load.PopulationLoader;
import javathing.load.TokenResolver;
import javathing.utils.MathUtils;

/**
 *
 * @author Henry
 */
public class Missle extends Sprite {

    private double speed;

    public Missle(double x, double y, double speed) {
        super(x, y, 20, 20);
        this.speed = speed;
        this.spriteColor = Color.red;
    }
    public Missle(double x, double y, double xVolocity, double yVolocity, double speed) {
        super(x, y, 20, 20);
        this.speed = speed;
        this.spriteColor = Color.red;
        this.xVolocity = xVolocity;
        this.yVolocity = yVolocity;
    }

    @Override
    public void update() {
        Point2D.Double playerPosition = MainClass.getLevelManager().getPlayer().getPosition();
        double accelStrength = speed / Math.pow((MathUtils.getDistance(playerPosition.x - x, playerPosition.y - y)), 2) + .01;
        double angle = MathUtils.getAngle(playerPosition.x - x, playerPosition.y - y);
        this.addSingleFrameAcceleration(Math.cos(angle) * accelStrength, Math.sin(angle) * accelStrength);
        this.x += xVolocity;
        this.y += yVolocity;
        try {
            super.update();
        } catch (OutOfLevelException ole) {
        }

    }

    @Override
    public void onContact(Sprite interactor) {
        if (interactor instanceof Shootable) {
            ((Shootable) interactor).shot(this);
        }
    }

    public static Missle getFromArgs(String args, TokenResolver tokenResolver) throws Exception {
        Object[] objArgs = PopulationLoader.getObjectsFromParams(args, tokenResolver);
        Double[] position = (Double[]) objArgs[0];
        Object[] volocities = (Object[]) objArgs[1];
        Double speed = PopulationLoader.extrapolateToDouble(objArgs[2]);
        return new Missle(position[0], position[1], PopulationLoader.extrapolateToDouble(volocities[0]), PopulationLoader.extrapolateToDouble(volocities[1]), speed);
    }
}
