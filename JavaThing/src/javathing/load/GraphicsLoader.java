/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.load;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javathing.utils.Convenience;
import javax.imageio.ImageIO;

/**
 *
 * @author Henry
 */
public class GraphicsLoader {
    public static BufferedImage getButtonBackground() {
        BufferedImage buttonBackground = null;
            try {
                buttonBackground = ImageIO.read(
                        Convenience.class.getClassLoader().getResourceAsStream(
                        "javathing/resources/graphics/menu/pausemenubutton.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return buttonBackground;
    }
    public static BufferedImage getNextLevelMenuBackground() {
        try {
            return ImageIO.read(
                            Convenience.class.getClassLoader().getResourceAsStream(
                            "javathing/resources/graphics/menu/nextlevelmenubackground.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GraphicsLoader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
