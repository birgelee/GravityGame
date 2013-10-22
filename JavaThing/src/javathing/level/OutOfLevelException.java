/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.level;

/**
 *
 * @author Henry
 */
public class OutOfLevelException extends RuntimeException {

    public OutOfLevelException() {
        super("Sprite position outside of level bounds.");
    }
    
}
