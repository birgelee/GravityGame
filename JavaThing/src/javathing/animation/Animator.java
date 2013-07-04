/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javathing.animation;

/**
 *
 * @author Henry
 */
public interface Animator {
    
    public float getPercent();
    public void start();
    public void stop();
    public void update();
    public void reset();

}
