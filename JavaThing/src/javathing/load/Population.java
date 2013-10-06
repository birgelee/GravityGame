/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.load;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javathing.Updateable;
import javathing.level.LevelManager;
import javathing.level.gravity.GravitySource;
import javathing.render.Paintable;
import javathing.sprite.Sprite;

/**
 *
 * @author Henry
 */
public class Population {
    private List<Updateable> updateables = new ArrayList<Updateable>();
    private List<Paintable>[] paintables = LevelManager.getEmptyPaintables();
    private List<GravitySource> gravitySources = new ArrayList<GravitySource>();
    private List<Sprite> sprites = new ArrayList<Sprite>();
    
    /**
     * @return the sprites
     */
    public void addSprite(Sprite sprite) {
        getUpdateables().add(sprite);
        getPaintables()[2].add(sprite);
        getSprites().add(sprite);
    }

    /**
     * @param sprites the sprites to set
     */
    public void setSprites(List<Sprite> sprites) {
        updateables.clear();
        paintables[2].clear();
        for (Sprite sprite : sprites) {
            addSprite(sprite);
        }
        sprites.clear();
        sprites.addAll(sprites);
    }

    /**
     * @return the gravitySources
     */
    public List<GravitySource> getGravitySources() {
        return gravitySources;
    }

    /**
     * @param gravitySources the gravitySources to set
     */
    public void setGravitySources(List<GravitySource> gravitySources) {
        this.gravitySources = gravitySources;
    }

    /**
     * @return the seperateUpdateables
     */
    public List<Updateable> getUpdateables() {
        return updateables;
    }

    /**
     * @param seperateUpdateables the seperateUpdateables to set
     */
    public void setUpdateables(List<Updateable> seperateUpdateables) {
        this.updateables = seperateUpdateables;
    }

    /**
     * @return the seperatePaintables
     */
    public List<Paintable>[] getPaintables() {
        return paintables;
    }

    /**
     * @param seperatePaintables the seperatePaintables to set
     */
    public void setPaintables(List<Paintable>[] seperatePaintables) {
        this.paintables = seperatePaintables;
    }

    /**
     * @return the sprites
     */
    public List<Sprite> getSprites() {
        return sprites;
    }
}
