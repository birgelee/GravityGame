/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javathing.load;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Henry
 */
public class LoadingUtils {
    
    static String fileReader(String filePath) throws FileNotFoundException {
        InputStream fsStream = new FileInputStream(filePath);
        Scanner in = new Scanner(fsStream);
        String output = "";
        while (in.hasNext()) {
            output = output + in.nextLine() + "\n";
        }
        
        
        return output;
    }
}
