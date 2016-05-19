/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.level;

import java.util.LinkedList;
import gravitygame.MainClass;
import gravitygame.Updateable;
import gravitygame.sprite.Sprite;

/**
 *
 * @author Henry
 */
public class SpriteInteractionManager implements Updateable {

    @Override
    public void update() {
        if (MainClass.getLevelManager() != null) {
            LevelManager levelManager = MainClass.getLevelManager();
            LinkedList<Sprite> pendingSprites = new LinkedList<Sprite>(levelManager.getSprites());
            while (pendingSprites.size() > 0) {
                Sprite sprite = pendingSprites.pop();
                for (Sprite spriteToCheck : pendingSprites) {
                    if (sprite.getRectangle().intersects(spriteToCheck.getRectangle())) {
                        sprite.onContact(spriteToCheck);
                        spriteToCheck.onContact(sprite);
                    }
                }
            }
        }
    }
    
}
