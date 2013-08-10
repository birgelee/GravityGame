/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.load;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Henry
 */
public class LoadingUtils {

    static String fileReader(String filePath) throws FileNotFoundException {
	InputStream fsStream = new FileInputStream(filePath);
	StringBuilder output = new StringBuilder();

	while (true) {
	    try {
		int value = fsStream.read();
		if (value == -1) {
		    break;
		}
		char ch;

		ch = (char) (value);
		output.append(ch);
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }

	}
	return output.toString();
    }
}
