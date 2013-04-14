/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.render;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import javathing.MainClass;

/**
 *
 * @author lausd_user
 */
public class PlatformerGraphicsUtil {
    
    private Graphics graphics;
   
    
    public PlatformerGraphicsUtil(Graphics g) {
        graphics = g;

    }
    
    public Graphics getGraphics() {
        return graphics;
    }
    
    public void fillRectInLevel(int x, int y, int width, int height) {
        Point screenPosition = MainClass.getScreen().getScreenPosition();
        graphics.translate(-screenPosition.x, -screenPosition.y);
        graphics.fillRect(x, y, width, height);
        graphics.translate(screenPosition.x, screenPosition.y);
    }
    
    public void drawImageInLevel(Image img, int x, int y, ImageObserver imageObserver) {
        Point screenPosition = MainClass.getScreen().getScreenPosition();
        graphics.translate(-screenPosition.x, -screenPosition.y);
        graphics.drawImage(img, x, y, imageObserver);
        graphics.translate(screenPosition.x, screenPosition.y);
    }
    
    public static void translateGraphics(Graphics g) {
        Point screenPosition = MainClass.getScreen().getScreenPosition();
        g.translate(-screenPosition.x, -screenPosition.y);
    }
    
     public static void unTranslateGraphics(Graphics g) {
        Point screenPosition = MainClass.getScreen().getScreenPosition();
        g.translate(screenPosition.x, screenPosition.y);
    }
    
    
}
