/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.container;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import javathing.MainClass;
import javathing.settings.Settings;

/**
 *
 * @author Henry
 */
public class FadeInContainer implements GameContainer {

    private GameContainer container;
    private double duration, currentFrame;

    public FadeInContainer(GameContainer container, double duration) {
        this.container = container;
        this.duration = duration;

    }

    @Override
    public void init() {
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage buf = new BufferedImage(Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics gr = buf.createGraphics();
        container.paint(gr);
        float[] scales = {1f, 1f, 1f, (float) (currentFrame / duration)};
        float[] offsets = new float[4];
        RescaleOp rop = new RescaleOp(scales, offsets, null);
        ((Graphics2D)g).drawImage(buf, rop, 0, 0);
    }

    @Override
    public void update() {
        container.update();
        currentFrame++;
        if (currentFrame > duration) {
            MainClass.setContainer(container);
        }
    }

    @Override
    public String getName() {
        return container.getName().concat(":Fade");
    }

    @Override
    public void dispose() {
        if (MainClass.getContainer() != container) {
            container.dispose();
        }
    }

    public GameContainer getBaseContainer() {
        return container;
    }
}
