/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.level;

import java.awt.Point;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javathing.sprite.Sprite;
import java.util.List;
import javathing.GameObject;
import javathing.MainClass;
import javathing.render.Paintable;
import javathing.Updateable;
import javathing.block.Block;

/**
 *
 * @author lausd_user
 */
public class LevelManager {

    private List<Updateable> updateables;
    private List<Paintable> paintables;
    private List<Sprite> sprites;
    private Point startingPosition;
    private TileMap tileMap;

    public LevelManager(TileMap tileMap, List<Sprite> sprites, List<Updateable> updateables, List<Paintable> paintales, Point startingPosition) {
        this.updateables = updateables;
        this.paintables = paintales;
        this.sprites = sprites;
        this.startingPosition = startingPosition;
        this.tileMap = tileMap;
    }

    public List<Updateable> getUpdateables() {
        return updateables;
    }

    /**
     * @return the paintables
     */
    public List<Paintable> getPaintables() {
        return paintables;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

    public void addGameObject(GameObject gameObject) {
        getUpdateables().add(gameObject);
        getPaintables().add(gameObject);
    }

    public void addSprite(Sprite sprite) {
        getSprites().add(sprite);
        addGameObject(sprite);
    }

    public void addBlock(Block block) {
        addGameObject(block);
        getTileMap().addBlock(block);
    }

    /**
     * @return the sprites
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * @return the startingPosition
     */
    public Point getStartingPosition() {
        return startingPosition;
    }

    /**
     * @param startingPosition the startingPosition to set
     */
    public void setStartingPosition(Point startingPosition) {
        this.startingPosition = startingPosition;
    }
    private boolean listenersAreActive = false;
    private List<KeyListener> keyListeners = new LinkedList<KeyListener>();

    public void addKeyListener(KeyListener keyListener) {
        keyListeners.add(keyListener);
        if (listenersAreActive) {System.err.println("method 1");
            MainClass.addKeyListener(keyListener);
        }
    }

    public void removeKeyListener(KeyListener keyListener) {
        keyListeners.remove(keyListener);
        if (listenersAreActive) {
            
            MainClass.removeKeyListener(keyListener);
        }
    }

    public void activateListeners() {
        System.err.println("method 1");
        if (listenersAreActive == false) {
            listenersAreActive = true;
            for (KeyListener kl : keyListeners) {
                System.err.println("method 1");
                MainClass.addKeyListener(kl);
            }
        }
    }

    public void deactivateListeners() {
        listenersAreActive = false;
        for (KeyListener kl : keyListeners) {
            MainClass.removeKeyListener(kl);
        }
    }
}
