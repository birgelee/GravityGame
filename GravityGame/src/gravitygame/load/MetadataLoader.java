/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.load;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 *
 * @author Henry
 */
public class MetadataLoader {

    private HashMap<String, String> values = new HashMap<String, String>();
    public MetadataLoader(String path) {
	String fileString = null;
	try {
	    fileString = LoadingUtils.fileReader(path);
	} catch (FileNotFoundException ex) {
	    ex.printStackTrace();
	}
	//fileString = fileString.trim();
	if (fileString.endsWith("\n")) {
	    fileString = fileString.substring(0, fileString.length() - 1);
	}
	String[] lines = fileString.split("\n");
	for (String line : lines) {
	    processLine(line);
	}
	
    }
    
    private void processLine(String line) {
	String[] keyAndValue = line.split(":");
	values.put(keyAndValue[0], keyAndValue[1]);
    }
    
    
    public int getAsInt(String key) {
	return Integer.parseInt(values.get(key));
    }
    
    public String getAsString(String key) {
	return values.get(key);
    }
    
    public double getAsDouble(String key) {
	return Double.parseDouble(values.get(key));
    }
}
