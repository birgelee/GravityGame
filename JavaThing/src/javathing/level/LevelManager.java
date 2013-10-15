/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.level;

import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javathing.sprite.Sprite;
import java.util.List;
import javathing.GameObject;
import javathing.MainClass;
import javathing.Screen;
import javathing.render.Paintable;
import javathing.Updateable;
import javathing.block.Block;
import javathing.level.gravity.GravitationalFeild;
import javathing.load.Population;
import javathing.sprite.Player;
import javathing.ui.GUI;
import javathing.utils.Convenience;

public class LevelManager {

    private Point startingPosition;
    private TileMap tileMap;
    private Player player;
    private Screen screen;
    private GUI gui;
    private Population population;

    public LevelManager(TileMap tileMap, Population population, Point startingPosition, Player player) {
        this.population = population;
        this.startingPosition = startingPosition;
        this.tileMap = tileMap;
        this.player = player;
        if (player != null) {
            addSprite(player);
            addKeyListener(player.getKeyListener());
        }
        this.screen = new Screen(startingPosition.x - 20, startingPosition.y - 20);
        this.addKeyListener(screen.getKeyListener());
        this.addUpdateable(screen);
        population.getUpdateables().add(new SpriteInteractionManager());
        gui = new GUI();
    }
    
    public double[] getGravity(double x, double y) {

        return getGravitationalFeild().getGravity(x, y);
    }

    public void setGravitationalFeild(GravitationalFeild feild) {
        population.setGravitationalFeild(feild);
    }


    public static List<Paintable>[] getEmptyPaintables() {
        List<Paintable>[] paintablesReturn = new List[5];
        for (int i = 0; i < paintablesReturn.length; i++) {
            paintablesReturn[i] = new ArrayList();
        }
        return paintablesReturn;

    }

    public List<Updateable> getUpdateables() {
        return population.getUpdateables();
    }

    /**
     * @return the paintables
     */
    public List<Paintable>[] getPaintables() {
        return population.getPaintables();
    }

    public TileMap getTileMap() {
        return tileMap;
    }

    public Screen getScreen() {
        return screen;
    }

    public GUI getGUI() {
        return gui;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    public void addGameObject(GameObject gameObject) {
        getUpdateables().add(gameObject);
        getPaintables()[2].add(gameObject);
    }

    public void addSprite(Sprite sprite) {
        population.addSprite(sprite);
    }
    
    public void removeSprite(Sprite sprite) {
        population.removeSprite(sprite);
    }

    public void addBlock(Block block) {
        addGameObject(block);
        getTileMap().addBlock(block);
    }
    public List<Sprite> getSprites() {
        return  population.getSprites();
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
        if (listenersAreActive) {
            MainClass.addKeyListener(keyListener);
        }
    }

    public void removeKeyListener(KeyListener keyListener) {
        keyListeners.remove(keyListener);
        if (listenersAreActive) {

            MainClass.removeKeyListener(keyListener);
        }
    }
    private List<MouseListener> mouseListeners = new LinkedList<MouseListener>();

    public void addMouseListener(MouseListener mouseListener) {
        mouseListeners.add(mouseListener);
        if (listenersAreActive) {
            MainClass.addMouseListener(mouseListener);
        }
    }

    public void removeMouseListener(MouseListener mouseListener) {
        mouseListeners.remove(mouseListener);
        if (listenersAreActive) {
            MainClass.removeMouseListener(mouseListener);
        }
    }

    public void activateListeners() {
        if (listenersAreActive == false) {
            listenersAreActive = true;
            for (KeyListener kl : keyListeners) {
                MainClass.addKeyListener(kl);
            }
            for (MouseListener ml : mouseListeners) {
                MainClass.addMouseListener(ml);
            }
        }
    }

    public void deactivateListeners() {
        if (listenersAreActive) {
            listenersAreActive = false;
            for (KeyListener kl : keyListeners) {
                MainClass.removeKeyListener(kl);
            }
            for (MouseListener ml : mouseListeners) {
                MainClass.removeMouseListener(ml);
            }
        }
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addUpdateable(Updateable updateable) {
        getUpdateables().add(updateable);
    }

    public void addPaintable(Paintable paintable, int zlevel) {
        getPaintables()[zlevel].add(paintable);
    }

    /**
     * @return the gravitationalFeild
     */
    public GravitationalFeild getGravitationalFeild() {
        return population.getGravitationalFeild();
    }

    public void levelUp() {
        Convenience.initTransitionMenu();
    }

    /**
     * @return the population
     */
    public Population getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(Population population) {
        this.population = population;
    }
}
