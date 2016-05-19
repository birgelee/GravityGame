/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.animation;

/**
 *
 * @author Henry
 */
public class OneWayAnimation implements Animator {

    private int currentFrame;
    private int maxFrame;
    private boolean started;

    public OneWayAnimation(int frameCount) {
	maxFrame = frameCount;
    }

    @Override
    public float getPercent() {
	return (float) currentFrame / (float) maxFrame;
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
	    if (currentFrame != maxFrame) {
		currentFrame++;
	    } else {
		currentFrame = 0;
	    }
	}
    }

    @Override
    public void reset() {
	currentFrame = 0;
    }
}
