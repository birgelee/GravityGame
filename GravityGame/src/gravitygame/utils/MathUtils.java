/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.utils;

/**
 *
 * @author Henry
 */
public class MathUtils {

    public static double getAngle(double x, double y) {
        if (x > 0) {
            return Math.atan(y / x);
        } else if (x < 0) {
            return Math.atan(y / x) + Math.PI;
        } else {
            return y > 0 ? Math.PI / 2 : -Math.PI / 2;
        }
    }

    public static double[] getCordinates(double angle) {
        return new double[]{Math.cos(angle), Math.sin(angle)};
    }

    public static double getDistance(double x, double y) {

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static double dotProduct(double x1, double y1, double x2, double y2) {
        double distance1 = getDistance(x1, y1);
        double distance2 = getDistance(x2, y2);
        double angle1 = getAngle(x1, y1);
        double angle2 = getAngle(x2, y2);
        double angle = angle1 - angle2;
        return Math.cos(angle) * distance1 * distance2;
    }

    public static double[] getUnitVector(double x, double y) {
        double distance = getDistance(x, y);
        return new double[]{x / distance, y / distance};
    }
}
