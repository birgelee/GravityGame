/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.load;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import gravitygame.block.Accelerator;
import gravitygame.level.LevelManager;
import gravitygame.settings.Settings;
import gravitygame.level.TileMap;
import gravitygame.block.LevelEnd;
import gravitygame.block.PointMassBlock;
import gravitygame.block.Spike;
import gravitygame.level.gravity.GravitationalFeild;
import gravitygame.level.gravity.GravitySource;
import gravitygame.level.gravity.PointMass;
import gravitygame.settings.GameplaySettings;
import gravitygame.sprite.Player;
import gravitygame.utils.Convenience;

/**
 *
 * @author lausd_user
 */
public class LevelLoader {

    private LevelManager levelManager;

    public LevelLoader(String levelPath, String populationPath) throws FileNotFoundException, IOException {
	String fileString = LoadingUtils.fileReader(levelPath);
	int yDimention = fileString.replaceAll("[^\n]", "").length() + 1;
	//int yDimention = fileString.split("\n").length;
	int xDimention = fileString.indexOf("\n");
	//int startingChar = fileString.indexOf("#");
	levelManager = new LevelManager(new TileMap(xDimention, yDimention), new Population(), new Point(0, 0), new Player(0, 0));
	char[] chars = fileString.toLowerCase().toCharArray();
        TokenResolver tokenResolver = new TokenResolver();
	int x = 0;
	int y = 0;
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
                case '\r':
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
		    levelManager.addBlock(new Accelerator(x, y, -GameplaySettings.ACCELERATOR_STRENGTH));
		    x++;
		    break;
		case 'v':
		    levelManager.addBlock(new Spike(x, y));
		    x++;
                default:
                    Convenience.addAirBlock(levelManager, x, y);
                    tokenResolver.addToken(Character.toString(chars[i]), x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
                    x++;
	    }

	}
        PopulationLoader poploader = new PopulationLoader(populationPath);
        try {
        levelManager.getPopulation().merge(poploader.getPopulation(tokenResolver));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    public LevelManager getLevelManager() {
	return levelManager;
    }
}
