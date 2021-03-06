/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gravitygame.container;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import gravitygame.MainClass;
import gravitygame.Updateable;
import gravitygame.render.Paintable;
import gravitygame.settings.Settings;

/**
 *
 * @author Henry
 */
public class LevelContainer implements GameContainer {
    int levelNumber;
    public LevelContainer(int levelNumber) {this.levelNumber = levelNumber;}
	    @Override
	    public void paint(Graphics g) {
		BufferedImage level = new BufferedImage((int)(Settings.SCREEN_WIDTH * Math.sqrt(2)), (int) (Settings.SCREEN_HTIGHT * Math.sqrt(2)), BufferedImage.TYPE_INT_BGR);
		Graphics levelgr = level.createGraphics();
		int transformFactorX = (int) ((Math.sqrt(2) - 1) * Settings.SCREEN_WIDTH / 2);
		int transformFactorY =  (int) ((Math.sqrt(2) - 1) * Settings.SCREEN_HTIGHT / 2);
		levelgr.translate(transformFactorX, transformFactorY);

		BufferedImage viewport = new BufferedImage(Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT, BufferedImage.TYPE_INT_BGR);
		Graphics2D viewportgr = viewport.createGraphics();
                
		for (List<Paintable> paintables : MainClass.getLevelManager().getPaintables()) {
		    for (int i = 0; i < paintables.size(); i++) {//Uses a standard for loop to pervent concurrency errors.
                        try {
			paintables.get(i).paint(levelgr);
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            break;
                        }
		    }
		}
		levelgr.translate(-transformFactorX, -transformFactorY);
	        viewportgr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		viewportgr.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);   //code works but slows down game a lot
		AffineTransform transform = new AffineTransform();
		transform.rotate(MainClass.getLevelManager().getScreen().getTilt(), Settings.SCREEN_WIDTH / 2, Settings.SCREEN_HTIGHT / 2);
		
		viewportgr.setTransform(transform);
                viewportgr.drawImage(level, -transformFactorX, -transformFactorY, null);
		viewportgr.setTransform(new AffineTransform());
		viewportgr.setPaint(null);
		viewportgr.drawImage(MainClass.getLevelManager().getGUI().getUIOverlay(), 0, 0, null);
		MainClass.setLastImage(viewport);
		g.drawImage(viewport, 0, 0, null);
	    }

	    @Override
	    public void update() {
                List<Updateable> frozenUpdateables = new ArrayList<Updateable>(MainClass.getLevelManager().getUpdateables());
		for (Updateable updateable : frozenUpdateables) {
		    updateable.update();
		}
	    }

	    @Override
	    public String getName() {
		return "Game:Level:" + levelNumber;
	    }

	    @Override
	    public void init() {
	    }

	    @Override
	    public void dispose() {
		MainClass.getLevelManager().deactivateListeners();
	    }
	}
