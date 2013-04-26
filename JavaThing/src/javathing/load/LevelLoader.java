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
import javathing.block.LevelEnd;
import javathing.sprite.Player;
import javathing.utils.Convenience;

/**
 *
 * @author lausd_user
 */
public class LevelLoader {
    private LevelManager levelManager;
    public LevelLoader(String filePath) throws FileNotFoundException {
        String fileString = LoadingUtils.fileReader(filePath);
        int yDimention = fileString.replaceAll("[^\n]", "").length();
        //int yDimention = fileString.split("\n").length;
        int xDimention = fileString.indexOf("\n");
        //int startingChar = fileString.indexOf("#");
        levelManager = new LevelManager(new TileMap(xDimention, yDimention), new ArrayList(), new ArrayList(), new ArrayList(), new Point(0, 0), new Player(0, 0), null);
        char[] chars = fileString.toLowerCase().toCharArray();
        int x =0;
        int y =0;
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case ' ':
                    Convenience.addAirBlock(levelManager, x, y);
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
		    levelManager.getPlayer().setX(x * Settings.TileSize);
		    levelManager.getPlayer().setY(y * Settings.TileSize);
                    levelManager.setStartingPosition(new Point(x * Settings.TileSize, y * Settings.TileSize));
                    Convenience.addAirBlock(levelManager, x, y);
                    x++;
		    break;
		case '*':
		    levelManager.addBlock(new LevelEnd(x, y));
		    x++;
		    break;
                    
            }
            
        }
        
        
    }
    
    
    
    public LevelManager getLevelManager() {
        return levelManager;
    }
}
