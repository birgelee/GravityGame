/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.utils;

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
        } else
            return y > 0 ? Math.PI / 2 : -Math.PI / 2;
    }
}
