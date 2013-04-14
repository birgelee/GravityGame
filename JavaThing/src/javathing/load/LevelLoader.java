/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.load;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javathing.level.LevelManager;
import javathing.settings.Settings;
import javathing.level.TileMap;
import javathing.block.Block;
import javathing.utils.Convenience;

/**
 *
 * @author lausd_user
 */
public class LevelLoader {
    private LevelManager levelManager;
    public LevelLoader(String filePath) throws FileNotFoundException {
        String fileString = fileReader(filePath);
        int yDimention = fileString.replaceAll("[^\n]", "").length();
        //int yDimention = fileString.split("\n").length;
        int xDimention = fileString.indexOf("\n");
        //int startingChar = fileString.indexOf("#");
        levelManager = new LevelManager(new TileMap(xDimention, yDimention), new ArrayList(), new ArrayList(), new ArrayList(), new Point(0, 0));
        char[] chars = fileString.toLowerCase().toCharArray();
        int x =0;
        int y =0;
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case ' ':
                    x++;
                    break;
                case 'x':
                    Convenience.addDirtBlock(levelManager, x, y);
                    x++;
                    break;
                case '\n':
                    y++;
                    x = 0;
                    break;
                case '#':
                    levelManager.setStartingPosition(new Point(x * Settings.TileSize, y * Settings.TileSize));
                    x++;
                    
            }
            
        }
        
        
    }
    
    private String fileReader(String filePath) throws FileNotFoundException {
        InputStream fsStream = new FileInputStream(filePath);
        Scanner in = new Scanner(fsStream);
        String output = "";
        while (in.hasNext()) {
            output = output + in.nextLine() + "\n";
        }
        
        
        return output;
    }
    
    public LevelManager getLevelManager() {
        return levelManager;
    }
}
