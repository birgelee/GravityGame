/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.utils;

import javathing.level.LevelManager;
import javathing.MainClass;
import javathing.block.Block;
import javathing.block.DirtBlock;

/**
 *
 * @author Henry
 */
public class Convenience {
    public static void addDirtBlock(int x, int y) {
        Block block = new DirtBlock(x, y);
        MainClass.getLevelManager().addBlock(block);
    }
    
    public static void addDirtBlock(LevelManager lm, int x, int y) {
        Block block = new DirtBlock(x, y);
        lm.addBlock(block);
    }
}
