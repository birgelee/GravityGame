/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.sprite;

import java.awt.Color;
import java.awt.Point;
import javathing.settings.GameplaySettings;
import javathing.MainClass;
import javathing.settings.Settings;
import javathing.level.TileMap;
import javathing.block.Block;
import javathing.input.PlayerKeyListener;

/**
 *
 * @author lausd_user
 */
public class Player extends Sprite {

    private double matter = 1;
    private double mass = 1;
    private double yVolocity = 0;
    private double xVolocity = 0;
    private double xAcceleration = 0;
    private double yAcceleration = 0;
    double runSpeed = .2;
    private double vOfJump = .4;

    public static Player initNewPlayer() {
        
        Player p = new Player(MainClass.getLevelManager().getStartingPosition().x, MainClass.getLevelManager().getStartingPosition().y);
        return p;
    }
    private PlayerKeyListener keyListener;

    public Player(int x, int y) {
        super(x, y, 20, 20);
        
        keyListener = new PlayerKeyListener();
        MainClass.getLevelManager().addKeyListener(keyListener);
        spriteColor = Color.white;
    }
    boolean rightArrow = false;
    boolean leftArrow = false;

    @Override
    public void update() {
        //Block 1: update volocity and acceleration
	
	yAcceleration = (matter * GameplaySettings.GRAVITATIONAL_FEILD) / mass;

        xVolocity += xAcceleration * Settings.SLEEPTIME;
        yVolocity += yAcceleration * Settings.SLEEPTIME;

        if (rightArrow == false && keyListener.getArrowKeys()[1] == true) {
            xVolocity += runSpeed;
            rightArrow = true;
        }

        if (rightArrow == true && keyListener.getArrowKeys()[1] == false) {
            xVolocity -= runSpeed;
            rightArrow = false;
        }

        if (leftArrow == false && keyListener.getArrowKeys()[0] == true) {
            xVolocity -= runSpeed;
            leftArrow = true;
        }

        if (leftArrow == true && keyListener.getArrowKeys()[0] == false) {
            xVolocity += runSpeed;
            leftArrow = false;
        }

        if (keyListener.isSpace() && canJump()) {
            yVolocity = vOfJump;
        }

        //Variable def

        int xTilePosition = TileMap.getTileLocation(x);
        int xTilePosition2;
        if (!TileMap.isOnTile(x + width)) {
            xTilePosition2 = TileMap.getTileLocation(x + width);
        } else {
            xTilePosition2  = xTilePosition;
        }
        int yTilePosition = TileMap.getTileLocation(y);
        int yTilePosition2;
        if (!TileMap.isOnTile(y + height)) {
            yTilePosition2 = TileMap.getTileLocation(y + height);
        } else {
            yTilePosition2 = yTilePosition;
        }

        int xNumberOfBlocksToCheck = TileMap.getTileLocation(xVolocity) + 1;
        int yNumberOfBlocksToCheck = TileMap.getTileLocation(yVolocity) + 1;

        int xMax = 999999999; //These four values are in pixles
        int xMin = -1;
        int yMax = 999999999;
        int yMin = -1;

        //Block 2: calculate x and y max

        if (xVolocity > 0) {
            for (int i = 1; i <= xNumberOfBlocksToCheck; i++) {
                if (xTilePosition + i >= MainClass.getLevelManager().getTileMap().xDimention) {
                    xMax = TileMap.getPixleLocation(MainClass.getLevelManager().getTileMap().xDimention);
                    break;
                }

                Block b = MainClass.getLevelManager().getTileMap().getBlock(xTilePosition + i, yTilePosition);
                Block b2 = MainClass.getLevelManager().getTileMap().getBlock(xTilePosition + i, yTilePosition2);
                if ((b != null && !b.getPassable()) || (b2 != null && !b2.getPassable())) {
                    if (b == null || b.getPassable()) {
                        xMax = b2.getX();
                        break;
                    } else {
                        xMax = b.getX();
                        break;
                    }
                }
            }
        }

        if (xVolocity < 0) {
            for (int i = -1; i >= -xNumberOfBlocksToCheck; i--) {
                if (xTilePosition + i < 0) {
                    xMin = 0;
                    break;
                }
                Block b = MainClass.getLevelManager().getTileMap().getBlock(xTilePosition + i, yTilePosition);
                Block b2 = MainClass.getLevelManager().getTileMap().getBlock(xTilePosition + i, yTilePosition2);
                if ((b != null && !b.getPassable()) || (b2 != null && !b2.getPassable())) {
                    if (b == null || b.getPassable()) {
                        xMin = b2.getX() + Settings.TileSize;
                        break;
                    }
                    xMin = b.getX() + Settings.TileSize;
                    break;
                }
            }
        }

        if (yVolocity > 0) {
            for (int i = -1; i >= -yNumberOfBlocksToCheck; i--) {
                if (yTilePosition + i < 0) {
                    yMin = 0;
                    break;
                }
                Block b = MainClass.getLevelManager().getTileMap().getBlock(xTilePosition, yTilePosition + i);
                Block b2 = MainClass.getLevelManager().getTileMap().getBlock(xTilePosition2, yTilePosition + i);
                if ((b != null && !b.getPassable()) || (b2 != null && !b2.getPassable())) {
                    if (b == null || b.getPassable()) {
                        yMin = b2.getY() + Settings.TileSize;
                        break;
                    }
                    yMin = b.getY() + Settings.TileSize;
                    break;
                }
            }
        }

        if (yVolocity < 0) {
            for (int i = 1; i <= yNumberOfBlocksToCheck; i++) {
                if (yTilePosition + i >= MainClass.getLevelManager().getTileMap().yDimention) {
                    yMax = TileMap.getPixleLocation(MainClass.getLevelManager().getTileMap().yDimention);
                    break;
                }

                Block b = MainClass.getLevelManager().getTileMap().getBlock(xTilePosition, yTilePosition + i);
                Block b2 = MainClass.getLevelManager().getTileMap().getBlock(xTilePosition2, yTilePosition + i);

                if ((b != null && !b.getPassable()) || (b2 != null && !b2.getPassable())) {
                    if (b == null || b.getPassable()) {
                        yMax = b2.getY();
                        break;
                    }
                    yMax = b.getY();
                    break;
                }
            }
        }

        //Block 3: update position

        if (x + width + xVolocity * Settings.SLEEPTIME < xMax) {
            if (x + xVolocity * Settings.SLEEPTIME > xMin) {
                if (keyListener.getArrowKeys()[0]) {
                    x += xVolocity * Settings.SLEEPTIME;
                }

                if (keyListener.getArrowKeys()[1]) {
                    x += xVolocity * Settings.SLEEPTIME;
                }

            } else {
                x = xMin;
                xVolocity = getXVolocityAfterWall();
            }
        } else {
            x = xMax - width;
            xVolocity = getXVolocityAfterWall();
        }

        if (y + height - yVolocity * Settings.SLEEPTIME < yMax) {
            if (y - yVolocity * Settings.SLEEPTIME > yMin) {
                y -= yVolocity * Settings.SLEEPTIME;
            } else {
                y = yMin;
                yVolocity = 0;
            }
        } else {
            y = yMax - height;
            yVolocity = 0;
        }
        
        moveScreen();

    }
    
    private void moveScreen() {
        Point screenPosition = MainClass.getScreen().getScreenPosition();
        if (x - screenPosition.x < Settings.SCREEN_WIDTH / 4) {
            if (screenPosition.x != 0) {
                screenPosition.x = (int) (x - Settings.SCREEN_WIDTH / 4);
            }
        } else if (x - screenPosition.x > Settings.SCREEN_WIDTH * 3 / 4) {
            if (screenPosition.x + Settings.SCREEN_WIDTH != TileMap.getPixleLocation(MainClass.getLevelManager().getTileMap().xDimention)) {
                screenPosition.x = (int) (x + Settings.SCREEN_WIDTH / 4 - Settings.SCREEN_WIDTH);
            }
        }
        
        if (y - screenPosition.y < Settings.SCREEN_HTIGHT / 4) {
            if (screenPosition.y != 0) {
                screenPosition.y = (int) (y - Settings.SCREEN_HTIGHT / 4);
            }
        } else if (y - screenPosition.y > Settings.SCREEN_HTIGHT * 3 / 4) {
            if (screenPosition.y + Settings.SCREEN_HTIGHT != TileMap.getPixleLocation(MainClass.getLevelManager().getTileMap().yDimention)) {
                screenPosition.y = (int) (y + Settings.SCREEN_HTIGHT / 4 - Settings.SCREEN_HTIGHT);
            }
        }
    }

    private double getXVolocityAfterWall() {
        if (rightArrow && !leftArrow) {
            return runSpeed;
        }
        if (!rightArrow && leftArrow) {
            return -runSpeed;
        }
        return 0;
    }

    private boolean canJump() {
        if (TileMap.getTileLocation(y + height) == MainClass.getLevelManager().getTileMap().yDimention)
            return true;
        if (TileMap.isOnTile(y + height) && 
                (!MainClass.getLevelManager().getTileMap().getPassable(TileMap.getTileLocation(x), TileMap.getTileLocation(y + height)) || !MainClass.getLevelManager().getTileMap().getPassable(TileMap.getTileLocation(x + width), TileMap.getTileLocation(y + height)))) {
            return true;
        }
        return false;
    }
    
    public void decreaseMass() {
	mass -= .1;
    }
    
    public void undoMassDecrease() {
	mass += .1;
    }
    public void increaseMass() {
	mass += .1;
    }
    public void undoMassIncrease() {
	mass -= .1;
    }

    /*
     * public void arrowKeyPressed(int arrowKey) { switch (arrowKey) { case 0:
     * xVolocity -= runSpeed; break; case 1: xVolocity += runSpeed; break; } }
     */
    /*
     * public void arrowKeyReleased(int arrowKey) { switch (arrowKey) { case 0:
     * xVolocity += runSpeed; break; case 1: xVolocity -= runSpeed; break; } }
     */
}
