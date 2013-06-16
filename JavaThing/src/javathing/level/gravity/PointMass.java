package javathing.level.gravity;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Henry
 */
public class PointMass extends GravitySource {
    private double x, y, strength;
    public PointMass(double x, double y, double strength) {
	this.x = x;
	this.y = y;
	this.strength = strength;
    }
    
    @Override
    public double[] getGravity(double x, double y) {
	double distance = Math.sqrt(Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2));
	double magnitude = strength / (distance * distance);
	double direction = Math.atan((this.y - y) / (x - this.x)) + Math.PI;
	double xReturn = x - this.x > 0 ? -Math.abs(Math.cos(direction) * magnitude) : Math.abs(Math.cos(direction) * magnitude);
	double yReturn = this.y - y > 0 ? -Math.abs(-Math.sin(direction) * magnitude) : Math.abs(-Math.sin(direction) * magnitude);
	return new double[] {xReturn, yReturn};
    }
    
    public void setX(double x) {
	this.x = x;
    }
    public void setY(double y) {
	this.y = y;
    }
    public void setStrength(double strength) {
	this.strength = strength;
    }
}
