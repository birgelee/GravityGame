/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.level.gravity;

import java.util.List;

/**
 *
 * @author Henry
 */
public class GravitationalFeild {

    private List<GravitySource> gravitySources;
    private double[] constatnGravity;

    public GravitationalFeild(List<GravitySource> gravitySources, double[] constantGravity) {
	this.gravitySources = gravitySources;
	this.constatnGravity = constantGravity;
    }
    
    public List<GravitySource> getGravitySources() {
    return gravitySources;
    }

    public double[] getGravity(double x, double y) {
	double[] gravity = new double[2];
	gravity[0] += constatnGravity[0];
	gravity[1] += constatnGravity[1];
	for (GravitySource gravitySource : gravitySources) {
	    double[] addedGravity = gravitySource.getGravity(x, y);
	    gravity[0] += addedGravity[0];
	    gravity[1] += addedGravity[1];
	}
	return gravity;
    }
}
