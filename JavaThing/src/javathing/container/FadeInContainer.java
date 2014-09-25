/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.container;

import java.awt.Color;
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
    private float max, min;

    public FadeInContainer(GameContainer container, double duration, float min, float max) {
        this.container = container;
        this.duration = duration;
        this.max = max;
        this.min = min;
        currentFrame = 0;

    }
    
    public FadeInContainer(GameContainer container, double duration) {
        this(container, duration, 0, .2F);
    }
    public FadeInContainer(GameContainer container) {
        this(container, 750);
    }

    @Override
    public void init() {
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage buf = new BufferedImage(Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics gr = buf.createGraphics();
        container.paint(gr);
        
        float[] scales = {1f, 1f, 1f, (float) ((currentFrame / duration) * (max - min) + min)};
        float[] offsets = new float[4];
        RescaleOp rop = new RescaleOp(scales, offsets, null);
        g.setColor(Color.black);
        g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT);
        
        ((Graphics2D)g).drawImage(buf, rop, 0, 0);
    }

    @Override
    public void update() {
        currentFrame++;
        container.update();
        if (currentFrame >= duration) {
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
