/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javathing.sprite.Sprite;

/**
 *
 * @author Henry
 */
public class Zone {
    private  List<Sprite> sprites = new ArrayList<Sprite>();
    private Point location;
    
    public Zone(Point location) {
        this.location = location;
    }
    public List<Sprite> getSprites() {
        return sprites.subList(0, sprites.size());
    }
    public void regesterSprite(Sprite sprite) {
        if (sprites.contains(sprite))
            return;
        sprites.add(sprite);
    }
    public void deregestarSprite(Sprite sprite) {
        if (!sprites.contains(sprite))
            return;
        sprites.remove(sprite);
    }

    /**
     * @return the location
     */
    public Point getLocation() {
        return location;
    }
    }
