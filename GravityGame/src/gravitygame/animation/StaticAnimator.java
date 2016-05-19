/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gravitygame.animation;

/**
 *
 * @author Henry
 */
public class StaticAnimator implements Animator {
    
    float percent;
    public StaticAnimator(float percent) {
	this.percent = percent;
    }

    @Override
    public float getPercent() {
    return percent;
    }

    @Override
    public void start() {
  }

    @Override
    public void stop() {
    }

    @Override
    public void update() {
    }

    @Override
    public void reset() {
    }

}
