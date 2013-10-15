/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.level;

import java.awt.Point;
import javathing.block.AirBlock;
import javathing.settings.Settings;
import javathing.block.Block;
import javathing.block.DirtBlock;

/**
 *
 * @author lausd_user
 */
public class TileMap {
    
    public final int xDimention;
    public final int yDimention;
    
     public static boolean isOnTile(double pixle) {
        return pixle % Settings.TILE_SIZE == 0;
    }
    
    public static int getTileLocation(double pixle) {
        return ((int) (pixle / Settings.TILE_SIZE));
    }
    
    public static Point getTileLocation(Point point) {
        return new Point(getTileLocation(point.x), getTileLocation(point.y));
    }
    
    public static int getPixleLocation(int tile) {
        return (tile) * Settings.TILE_SIZE;
    }
    
    public static Point getPixleLocation(Point point) {
        return new Point(getPixleLocation(point.x), getPixleLocation(point.y));
    }
    
    private Block[][] blocks;
    public TileMap(int width, int height) {
        xDimention = width;
        yDimention = height;
        blocks = new Block[width][height];
    }
    
    public boolean getPassable(int xTilePosition, int yTilePosition) {
        Block block = blocks[xTilePosition][yTilePosition];
        if (block != null)
        return block.getPassable();
        else
            return true;
    }
    
    public void addBlock(Block block) {
        blocks[block.getXTileLocation()][block.getYTileLocation()] = block;
    }
    
    public Block getBlock(int xTilePosition, int yTilePosition) {
	if (xTilePosition == blocks.length || yTilePosition == blocks[0].length || xTilePosition < 0 || yTilePosition < 0) {
	    return new AirBlock(xTilePosition, yTilePosition);
	}
        return blocks[xTilePosition][yTilePosition];
    }
    
    public boolean isInTileGrid(double x, double y) {
        if (x < 0 || y < 0)
            return false;
        int tileX = getTileLocation(x) + 1, tileY = getTileLocation(y) + 1;
        if (tileX >= xDimention || tileY >= yDimention) {
            return false;
        }
        return true;
    }
    
    
    
}
