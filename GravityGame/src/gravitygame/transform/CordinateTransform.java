/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.transform;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import gravitygame.utils.MathUtils;

public class CordinateTransform {

    double x, y, angleChange;
    AffineTransform transform = new AffineTransform();

    public CordinateTransform(double x, double y) {
	this.x = x;
	this.y = y;
	angleChange = -(MathUtils.getAngle(x, y) + Math.PI / 2);
	transform.rotate(angleChange);
    }
    
    public double[] getTransformedCordinates(double x, double y) {
	Point2D temp = new Point2D.Double();
	transform.transform(new Point2D.Double(x, y), temp);
	return new double[] {temp.getX(), temp.getY()};
    }
    
    public double[] getOriginalCordinates(double x, double y) {
	Point2D temp = new Point2D.Double();
	try {
	    transform.inverseTransform(new Point2D.Double(x, y), temp);
	} catch (NoninvertibleTransformException ex) {
	    ex.printStackTrace();
	}
	return new double[] {temp.getX(), temp.getY()};
    }
}
