/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javathing.animation;

/**
 *
 * @author Henry
 */
public class SinAnimator implements Animator {
    int currentFrame;
    int maxFrame;
    boolean started;
    
    float minPercent = 0;
    float maxPercent = 1F;
    public SinAnimator(int frameCount) {
	this.maxFrame = frameCount;
    }
    
    public SinAnimator(int frameCount, float maxPercent, float minPercent) {
	this.maxFrame = frameCount;
	this.maxPercent = maxPercent;
	this.minPercent = minPercent;
    }

    @Override
    public float getPercent() {
	float rawPercent = (float) currentFrame / (float) maxFrame;
	float animationPercent = (float) ((-Math.cos(rawPercent * Math.PI * 2) + 1) / 2);
	return minPercent + animationPercent * (maxPercent - minPercent);
    }

    @Override
    public void start() {
	started = true;
    }

    @Override
    public void stop() {
	started = false;
    }

    @Override
    public void update() {
	if (started) {
	    currentFrame++;
	    currentFrame = currentFrame % maxFrame;
	}
    }

    @Override
    public void reset() {
	currentFrame = 0;
    }
}
