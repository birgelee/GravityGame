/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.load;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javathing.sprite.Vilin;

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
            int openIndex = line.indexOf("(");
            if (openIndex == -1) {
                throw new Exception("Mal formated metadata");
            }
            String term = line.substring(0, openIndex);
            String args = line.substring(openIndex + 1, line.lastIndexOf(")"));
            
            
            if (term.equals("Vilin")) {
                population.addSprite(Vilin.getFromArgs(args, null));
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
    
    public static Object[] getObjectsFromParams(String params, TokenResolver tokenResolver) throws Exception {
        String[] objectReps = params.split(",");
        Object[] result = new Object[objectReps.length];
        for (int i = 0; i < result.length; i++) {
            try {
                result[i] = Integer.parseInt(objectReps[i]);
            } catch (RuntimeException rex) {
                try {
                    result[i] = Double.parseDouble(objectReps[i]);
                } catch (Exception e1) {
                    try {
                        result[i] = tokenResolver.resolveToken(objectReps[i]);
                    } catch (Exception e2) {
                        try {
                            result[i] = getObjectsFromParams(objectReps[i].substring(objectReps[i].indexOf("{") + 1, objectReps[i].lastIndexOf("}")), tokenResolver);
                        } catch (Exception e3) {
                            throw new Exception("Could not parse object rep: " + objectReps[i] + " as an int, double, token, or obj array.");
                        }
                        
                    }
                }
                
            }
        }
        return objectReps;
    }
}
