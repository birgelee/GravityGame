/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import gravitygame.render.PlatformerGraphicsUtil;
import gravitygame.settings.Settings;
import gravitygame.utils.Convenience;
import javax.imageio.ImageIO;

/**
 *
 * @author Henry
 */
public abstract class StandardBlock extends Block {

    protected String resourceLocation;
    protected Color color = Color.white;
    private BufferedImage image;

    public StandardBlock(int x, int y) {
	super(x, y);
    }

    @Override
    public boolean getPassable() {
	return false;
    }

    @Override
    public void paint(Graphics g) {
	if (image != null) {
	    
	PlatformerGraphicsUtil.translateGraphics(g);
       g.drawImage( image,getX(), getY(), null);
       PlatformerGraphicsUtil.unTranslateGraphics(g);
	}else if (resourceLocation != null) {try {
		image = ImageIO.read(
			Convenience.class.getClassLoader().getResourceAsStream(
			resourceLocation));
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	PlatformerGraphicsUtil.translateGraphics(g);
       g.drawImage( image,getX(), getY(), null);
       PlatformerGraphicsUtil.unTranslateGraphics(g);
	}
	else {
	    PlatformerGraphicsUtil.translateGraphics(g);
       g.setColor(color);
       g.fillRect(getX(), getY(), Settings.TILE_SIZE, Settings.TILE_SIZE);
       PlatformerGraphicsUtil.unTranslateGraphics(g);
	}
       
    }

    @Override
    public void update() {
    }
}
