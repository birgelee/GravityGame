/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.load;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javathing.MainClass;
import javathing.block.Accelerator;
import javathing.level.LevelManager;
import javathing.settings.Settings;
import javathing.level.TileMap;
import javathing.block.LevelEnd;
import javathing.block.PointMassBlock;
import javathing.level.gravity.GravitationalFeild;
import javathing.level.gravity.GravitySource;
import javathing.level.gravity.PointMass;
import javathing.settings.GameplaySettings;
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
        int yDimention = fileString.replaceAll("[^\n]", "").length()+1;
        //int yDimention = fileString.split("\n").length;
        int xDimention = fileString.indexOf("\n");
        //int startingChar = fileString.indexOf("#");
	List<GravitySource> gs = new ArrayList();
	GravitationalFeild gf = new GravitationalFeild(gs, GameplaySettings.ACCELERATION_DUE_TO_GRAVITY);
        levelManager = new LevelManager(new TileMap(xDimention, yDimention), new ArrayList(), new ArrayList(), new Point(0, 0), new Player(0, 0), gf);
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
		    levelManager.getPlayer().setX(x * Settings.TILE_SIZE);
		    levelManager.getPlayer().setY(y * Settings.TILE_SIZE);
                    levelManager.setStartingPosition(new Point(x * Settings.TILE_SIZE, y * Settings.TILE_SIZE));
                    Convenience.addAirBlock(levelManager, x, y);
                    x++;
		    break;
		case '*':
		    levelManager.addBlock(new LevelEnd(x, y));
		    x++;
		    break;
		case 'o':
		    levelManager.getGravitationalFeild().getGravitySources().add(new PointMass(x * Settings.TILE_SIZE, y * Settings.TILE_SIZE, GameplaySettings.POINT_MASS_STRENGTH));
		    levelManager.addBlock(new PointMassBlock(x, y));
		    x++;
		    break;
		case '>':
		    levelManager.addBlock(new Accelerator(x, y));
		    x++;
		    break;
		    case '<':
		    levelManager.addBlock(new Accelerator(x, y, -1));
		    x++;
		    break;
            }
            
        }
	
	
        
        
    }
    
    
    
    public LevelManager getLevelManager() {
        return levelManager;
    }
}
