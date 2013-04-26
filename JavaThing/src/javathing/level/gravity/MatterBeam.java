/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javathing.level.gravity;

/**
 *
 * @author Henry
 */
public class MatterBeam extends GravitySource{
    private double y;
    private double strength;
    public MatterBeam(double y, double strength) {
	this.y = y;
	this.strength = strength;
    }

    @Override
    public double[] getGravity(double xPos, double yPos) {
	double force = strength / (yPos - y);
	double[] retunValue = {0, force};
	return retunValue;
    }
    
}
