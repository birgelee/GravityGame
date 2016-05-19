/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.sprite;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import gravitygame.MainClass;
import gravitygame.settings.Settings;
import gravitygame.level.TileMap;
import gravitygame.block.Block;
import gravitygame.input.PlayerKeyListener;
import gravitygame.settings.GameplaySettings;
import gravitygame.transform.CordinateTransform;
import gravitygame.utils.Convenience;
import gravitygame.utils.MathUtils;

/**
 *
 * @author lausd_user
 */
public class Player extends Sprite implements Shootable {

    private double matter = 1;
    private double mass = 1;
    private double xAcceleration = 0;
    private double yAcceleration = 0;
    private double vOfJump = .4;
    private double addedXVolocity = 0;
    private double addedYVolocity = 0;
    private PlayerKeyListener keyListener;

    public Player(int x, int y) {
	super(x, y, 20, 20);
	keyListener = new PlayerKeyListener();
	spriteColor = Color.white;
    }
    boolean rightArrow = false;
    boolean leftArrow = false;
    boolean[] fandd = new boolean[2];

    @Override
    public void update() {
	super.update();
	//Block 1: update volocity and acceleration
	processKeyInput();
	double[] gravitationalFeild = MainClass.getLevelManager().getGravity(x, y);
	xAcceleration = (matter * gravitationalFeild[0]) / mass;
	yAcceleration = (matter * gravitationalFeild[1]) / mass;

	xVolocity += xAcceleration * Settings.SLEEPTIME;
        yVolocity += yAcceleration * Settings.SLEEPTIME;

	

	double xEffectiveVolocity = xVolocity + addedXVolocity + singleFrameXVolocity;
	double yEffectiveVolocity = yVolocity + addedYVolocity + singleFrameYVolocity;
	singleFrameXVolocity = 0;
	singleFrameYVolocity = 0;
	//Variable def

	int xTilePosition = TileMap.getTileLocation(getX());
	int xTilePosition2;
	if (!TileMap.isOnTile(x + width)) {
	    xTilePosition2 = TileMap.getTileLocation(getX() + getWidth());
	} else {
	    xTilePosition2 = xTilePosition;
	}
	int yTilePosition = TileMap.getTileLocation(getY());
	int yTilePosition2;
	if (!TileMap.isOnTile(y + height)) {
	    yTilePosition2 = TileMap.getTileLocation(getY() + getHeight());
	} else {
	    yTilePosition2 = yTilePosition;
	}

	int xNumberOfBlocksToCheck = TileMap.getTileLocation(xEffectiveVolocity) + 1;
	int yNumberOfBlocksToCheck = TileMap.getTileLocation(yEffectiveVolocity) + 1;

	int xMax = 999999999; //These four values are in pixles
	int xMin = -1;
	int yMax = 999999999;
	int yMin = -1;

	//Block 2: calculate x and y max

	if (xEffectiveVolocity > 0) {
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

	if (xEffectiveVolocity < 0) {
	    for (int i = -1; i >= -xNumberOfBlocksToCheck; i--) {
		if (xTilePosition + i < 0) {
		    xMin = 0;
		    break;
		}
		Block b = MainClass.getLevelManager().getTileMap().getBlock(xTilePosition + i, yTilePosition);
		Block b2 = MainClass.getLevelManager().getTileMap().getBlock(xTilePosition + i, yTilePosition2);
		if ((b != null && !b.getPassable()) || (b2 != null && !b2.getPassable())) {
		    if (b == null || b.getPassable()) {
			xMin = b2.getX() + Settings.TILE_SIZE;
			break;
		    }
		    xMin = b.getX() + Settings.TILE_SIZE;
		    break;
		}
	    }
	}

	if (yEffectiveVolocity > 0) {
	    for (int i = -1; i >= -yNumberOfBlocksToCheck; i--) {
		if (yTilePosition + i < 0) {
		    yMin = 0;
		    break;
		}
		Block b = MainClass.getLevelManager().getTileMap().getBlock(xTilePosition, yTilePosition + i);
		Block b2 = MainClass.getLevelManager().getTileMap().getBlock(xTilePosition2, yTilePosition + i);
		if ((b != null && !b.getPassable()) || (b2 != null && !b2.getPassable())) {
		    if (b == null || b.getPassable()) {
			yMin = b2.getY() + Settings.TILE_SIZE;
			break;
		    }
		    yMin = b.getY() + Settings.TILE_SIZE;
		    break;
		}
	    }
	}

	if (yEffectiveVolocity < 0) {
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
	if (getX() + width + xEffectiveVolocity * Settings.SLEEPTIME < xMax) {
	    if (getX() + xEffectiveVolocity * Settings.SLEEPTIME > xMin + 1) {//Note +1 added to fix phazing glitch due to boarder values
		setX(getX() + xEffectiveVolocity * Settings.SLEEPTIME);
		setX(getX() + xEffectiveVolocity * Settings.SLEEPTIME);

	    } else {
		setX(xMin);
		xVolocity = 0;
	    }
	} else {
	    setX(xMax - getWidth());
	    xVolocity = 0;
	}

	if (getY() + height - yEffectiveVolocity * Settings.SLEEPTIME < yMax) {
	    if (getY() - yEffectiveVolocity * Settings.SLEEPTIME > yMin + 1) {
		setY(getY() - yEffectiveVolocity * Settings.SLEEPTIME);
	    } else {
		setY(yMin);
		yVolocity = 0;
	    }
	} else {
	    setY(yMax - getHeight());
	    yVolocity = 0;
	}

	moveScreen();

    }

    private void moveScreen() {
	Point screenPosition = MainClass.getLevelManager().getScreen().getScreenPosition();
	if (getX() - screenPosition.x < Settings.SCREEN_WIDTH / 4) {
	    if (screenPosition.x != 0) {
		screenPosition.x = (int) (getX() - Settings.SCREEN_WIDTH / 4);
	    }
	} else if (getX() - screenPosition.x > Settings.SCREEN_WIDTH * 3 / 4) {
	    if (screenPosition.x + Settings.SCREEN_WIDTH != TileMap.getPixleLocation(MainClass.getLevelManager().getTileMap().xDimention)) {
		screenPosition.x = (int) (getX() + Settings.SCREEN_WIDTH / 4 - Settings.SCREEN_WIDTH);
	    }
	}

	if (getY() - screenPosition.y < Settings.SCREEN_HTIGHT / 4) {
	    if (screenPosition.y != 0) {
		screenPosition.y = (int) (getY() - Settings.SCREEN_HTIGHT / 4);
	    }
	} else if (getY() - screenPosition.y > Settings.SCREEN_HTIGHT * 3 / 4) {
	    if (screenPosition.y + Settings.SCREEN_HTIGHT != TileMap.getPixleLocation(MainClass.getLevelManager().getTileMap().yDimention)) {
		screenPosition.y = (int) (getY() + Settings.SCREEN_HTIGHT / 4 - Settings.SCREEN_HTIGHT);
	    }
	}
    }

    private void processKeyInput() {
	double[] gravity = MainClass.getLevelManager().getGravity(x, y);
	double angle = MathUtils.getAngle(-gravity[0], -gravity[1]);//       /_
	addedXVolocity = 0;
	addedYVolocity = 0;
	double transformedXVolocity, transformedYVolocity;
	CordinateTransform ct = new CordinateTransform(gravity[0], gravity[1]);
	transformedXVolocity = ct.getTransformedCordinates(xVolocity, yVolocity)[0];
	transformedYVolocity = ct.getTransformedCordinates(xVolocity, yVolocity)[1];
	if (Math.abs(transformedXVolocity) > GameplaySettings.RUN_SPEED) {
	    return;
	}
	if (getKeyListener().getArrowKeys()[1] == true) {
	    if (Math.abs(transformedXVolocity) <= GameplaySettings.RUN_SPEED) {
		xVolocity = ct.getOriginalCordinates(0, transformedYVolocity)[0];
		yVolocity = ct.getOriginalCordinates(0, transformedYVolocity)[1];
	    }
	    addedXVolocity += GameplaySettings.RUN_SPEED * Math.cos(angle - Math.PI / 2);
	    addedYVolocity += GameplaySettings.RUN_SPEED * Math.sin(angle - Math.PI / 2);
	}

	if (getKeyListener().getArrowKeys()[0] == true) {
	    if (Math.abs(transformedXVolocity) <= GameplaySettings.RUN_SPEED) {
		xVolocity = ct.getOriginalCordinates(0, transformedYVolocity)[0];
		yVolocity = ct.getOriginalCordinates(0, transformedYVolocity)[1];
	    }
	    addedXVolocity += GameplaySettings.RUN_SPEED * Math.cos(angle + Math.PI / 2);
	    addedYVolocity += GameplaySettings.RUN_SPEED * Math.sin(angle + Math.PI / 2);
	}
	angle = (angle + Math.PI * 2)% (Math.PI * 2);
	List<JumpDirection> possiblejds = new ArrayList<JumpDirection>();
	if (in60Of(angle, Math.PI / 2))
	    possiblejds.add(JumpDirection.UP);
	if (in60Of(angle, Math.PI))
	    possiblejds.add(JumpDirection.LEFT);
	if (in60Of(angle, Math.PI * 3 / 2))
	    possiblejds.add(JumpDirection.DOWN);
	if (in60Of((angle + Math.PI / 2) % (Math.PI * 2), Math.PI / 2))
	    possiblejds.add(JumpDirection.RIGHT);
	if (getKeyListener().isSpace() && containsAny(possiblejds, canJump())) {
	    
	    transformedYVolocity = vOfJump;
	    xVolocity = ct.getOriginalCordinates(transformedXVolocity, transformedYVolocity)[0];
	    yVolocity = ct.getOriginalCordinates(transformedXVolocity, transformedYVolocity)[1];
	}
    }
    
    private boolean in60Of(double angle, double targetAngle) {
	return Math.abs(angle - targetAngle) <= Math.PI / 3;
    }
    
    private boolean containsAny(Collection c1, Collection c2) {
	for (Object obj : c1) {
	    if (c2.contains(obj)) {
		return true;
	    }
	}
	return false;
    }

    private List<JumpDirection> canJump() {
	ArrayList returnVal = new ArrayList();
	if (TileMap.getTileLocation(getY() + height) == MainClass.getLevelManager().getTileMap().yDimention) {
	    returnVal.add(JumpDirection.UP);
	}
	if (TileMap.isOnTile(getY() + getHeight())
		&& (!MainClass.getLevelManager().getTileMap().getPassable(TileMap.getTileLocation(x), TileMap.getTileLocation(y + height)) || !MainClass.getLevelManager().getTileMap().getPassable(TileMap.getTileLocation(x + width), TileMap.getTileLocation(y + height)))) {
	    returnVal.add(JumpDirection.UP);
	}
	if (TileMap.isOnTile(getY())
		&& (!MainClass.getLevelManager().getTileMap().getPassable(TileMap.getTileLocation(x), TileMap.getTileLocation(y)) || !MainClass.getLevelManager().getTileMap().getPassable(TileMap.getTileLocation(x + width), TileMap.getTileLocation(y)))) {
	    returnVal.add(JumpDirection.DOWN);
	}
	if (TileMap.isOnTile(getX())
		&& (!MainClass.getLevelManager().getTileMap().getPassable(TileMap.getTileLocation(x), TileMap.getTileLocation(y + height)) || !MainClass.getLevelManager().getTileMap().getPassable(TileMap.getTileLocation(x), TileMap.getTileLocation(y)))) {
	    returnVal.add(JumpDirection.RIGHT);
	}
	if (TileMap.isOnTile(getX() + width)
		&& (!MainClass.getLevelManager().getTileMap().getPassable(TileMap.getTileLocation(x + width), TileMap.getTileLocation(y + height)) || !MainClass.getLevelManager().getTileMap().getPassable(TileMap.getTileLocation(x+ width), TileMap.getTileLocation(y)))) {
	    returnVal.add(JumpDirection.LEFT);
	}
	return returnVal;
    }

    public void processFPress() {
	if (fandd[0] == false) {
	    mass += GameplaySettings.MASS_INCREASE_CONSTANT;
	    fandd[0] = true;
	}

    }

    public void processFRelease() {
	if (fandd[0] == true) {
	    mass -= GameplaySettings.MASS_INCREASE_CONSTANT;
	    fandd[0] = false;
	}

    }

    public void processDPress() {
	if (fandd[1] == false) {
	    mass -= GameplaySettings.MASS_DECREASE_CONSTANT;
	    fandd[1] = true;
	}
    }

    public void processDRelease() {
	if (fandd[1] == true) {
	    mass += GameplaySettings.MASS_DECREASE_CONSTANT;
	    fandd[1] = false;
	}
    }

    public void accel() {
	xVolocity += .1;
    }

    public void kill() {
        //Need to remove sprite
	Convenience.initDeathMenu();
    }

    /*
     * public void arrowKeyPressed(int arrowKey) { switch (arrowKey) { case 0:
     * xEffectiveVolocity -= GameplaySettings.RUN_SPEED; break; case 1:
     * xEffectiveVolocity += GameplaySettings.RUN_SPEED; break; } }
     */
    /*
     * public void arrowKeyReleased(int arrowKey) { switch (arrowKey) { case 0:
     * xEffectiveVolocity += GameplaySettings.RUN_SPEED; break; case 1:
     * xEffectiveVolocity -= GameplaySettings.RUN_SPEED; break; } }
     */
    /**
     * @return the keyListener
     */
    public PlayerKeyListener getKeyListener() {
	return keyListener;
    }

    @Override
    public void shot(Object shooter) {
        kill();
    }
}
