/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.level.gravity;

import java.awt.Rectangle;
import gravitygame.load.PopulationLoader;
import gravitygame.load.TokenResolver;

/**
 *
 * @author Henry
 */
public class GravityZone extends GravitySource {
    double x1, x2, y1, y2;
    double[] magnitude;
    public GravityZone(double x1, double y1, double x2, double y2, double[] magnitude) {
        this.magnitude = magnitude;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public double[] getGravity(double x, double y) {
        if ((new Rectangle.Double(x1, y1, x2, y2)).contains(x, y)) {
            return  magnitude;
        }
        return new double[] {0,0};
    }
    
    public static GravityZone getFromArgs(String args, TokenResolver tokenResolver) throws Exception {
        Object[] objArgs = PopulationLoader.getObjectsFromParams(args, tokenResolver);
        double x1 = PopulationLoader.extrapolateToDouble(((Object[]) objArgs[0])[0]);
        double y1 = PopulationLoader.extrapolateToDouble(((Object[]) objArgs[0])[1]);
        double x2 = PopulationLoader.extrapolateToDouble(((Object[]) objArgs[1])[0]);
        double y2 = PopulationLoader.extrapolateToDouble(((Object[]) objArgs[1])[1]);
        double[] magnitude = PopulationLoader.getPrimativeDoubleArryFromObjArry((Object[]) objArgs[2]);
        return new GravityZone(x1, y1, x2, y2, magnitude);
        
    }
    
}
