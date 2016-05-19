/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.load;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gravitygame.level.gravity.GravityZone;
import gravitygame.sprite.Missle;
import gravitygame.sprite.Turret;
import gravitygame.sprite.Vilin;

/**
 *
 * @author Henry
 */
public class PopulationLoader {

    private Population population;
    private List<String> lines;
    public PopulationLoader(String path) throws IOException {
        String fileString = null;
        fileString = LoadingUtils.fileReader(path);
        fileString = fileString.trim();
        lines = new ArrayList<String>();
        lines.addAll(Arrays.asList(fileString.split("\n")));
        if (lines.get(0).isEmpty()) {
            lines.remove(lines.size() - 1);
        }
        

    }

    /**
     * @return the population
     */
    public Population getPopulation(TokenResolver tokenResolver) throws Exception {
        population = new Population();
        for (String line : lines) {
            if (line.isEmpty())
                continue;
            int openIndex = line.indexOf("(");
            if (openIndex == -1) {
                throw new Exception("Malformated metadata");
            }
            String term = line.substring(0, openIndex);
            String args = line.substring(openIndex + 1, line.lastIndexOf(")"));
            
            
            if (term.equals("Vilin")) {
                population.addSprite(Vilin.getFromArgs(args, tokenResolver));
            } else if (term.equals("GravityZone")) {
                population.getGravitationalFeild().getGravitySources().add(GravityZone.getFromArgs(args, tokenResolver));
            } else if (term.equals("Turret")) {
                population.addSprite(Turret.getFromArgs(args, tokenResolver));
            } else if (term.equals("Missle")) {
                population.addSprite(Missle.getFromArgs(args, tokenResolver));
            }
        }
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(Population population) {
        this.population = population;
    }
    
    public static List<Point2D.Double> doubleArrayToPointList(Object doubles) {
        List<Point2D.Double> result = new ArrayList<Point2D.Double>();
        Object[] fristArray = (Object[]) doubles;
        try {
        for (int i1 = 0; i1 < fristArray.length; i1++) {
            Object[] secondArray = ((Object[]) fristArray[i1]);
            result.add(new Point2D.Double((Double) secondArray[0], (Double) secondArray[1]));
        }
        } catch (Exception ex) {
            
        }
        return result;
    }
    
     public static double[] getPrimativeDoubleArryFromObjArry(Object[] objects) throws Exception {
         double[] result = new double[objects.length];
         for (int i = 0; i < objects.length; i++) {
             result[i] = extrapolateToDouble(objects[i]);
             
         }
         return result;
     }
     public static double extrapolateToDouble(Object object) throws Exception {
         return extrapolateToDouble(object, 0);
    }
    public static double extrapolateToDouble(Object object, int index) throws Exception {
        if (object instanceof Integer) {
            return (Integer) object;
        }
        if (object instanceof Double) {
            return (Double) object;
        }
        if (object.getClass().isArray()) {
            return extrapolateToDouble(((Object[]) object)[index]);
        }
        throw new Exception("No double could be extrapalated from the data.");
    }
    public static Object[] getObjectsFromParams(String params, TokenResolver tokenResolver) throws Exception {
        ObjectParser op = new ObjectParser(params, tokenResolver);
        Object[] result = op.parse();
        return result;
    }
}
