/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame;

import gravitygame.container.GameContainer;
import java.awt.Color;
import gravitygame.settings.Settings;
import gravitygame.level.LevelManager;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import gravitygame.container.MenuContainer;
import gravitygame.load.MetadataLoader;
import gravitygame.menu.button.ButtonEvent;
import gravitygame.menu.button.MenuButton;
import gravitygame.menu.MenuManager;
import gravitygame.menu.button.RectangleMenuButton;
import gravitygame.statics.Statics;
import gravitygame.utils.Convenience;
import javax.swing.JFrame;

/**
 *
 * @author lausd_user
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    private static LevelManager levelManager;
    private static MenuManager menuManager;
    private static GameContainer container;
    private static JFrame frame;
    private static MainApplet mainApplet;

    public static void main(String[] args) {
	/*init order:
	 * load metadata
	 * frame
	 * app
	 * menu
	 * start thread
	 * ...
	 * player
	 * level
	 * screen
	 * 
	 */
	MetadataLoader mdl = new MetadataLoader("javathing/resources/level/levelmd.txt");
	Statics.levelVariables.setMaxLevelNumber(mdl.getAsInt("maxlevel"));
	
	frame = new JFrame();
	setSettings(frame);
	
	mainApplet = new MainApplet();
	Convenience.initMainMenu();
	
	frame.add(mainApplet);
	mainApplet.setVisible(true);
	mainApplet.setSize(Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT);



	Thread updateThread = new Thread(new Runnable() {
	    @Override
	    public void run() {
		while (true) {
                    if (containerChange) {
                        container.init();
                        containerChange = false;
                    }
		    container.update();
		    try {
			Thread.sleep(Settings.SLEEPTIME);
		    } catch (InterruptedException ex) {
			Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}

	    }
	});
	Thread paintThread = new Thread(new Runnable() {
	    @Override
	    public void run() {
		while (true) {
		    try {
		    mainApplet.repaint();
		    } catch (NullPointerException ex) {
			
		    }
		    try {
			Thread.sleep(Settings.SLEEPTIME);
		    } catch (InterruptedException ex) {
			Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}

	    }
	});
	updateThread.start();
	paintThread.start();
    }

    private static void setSettings(JFrame frame) {
	frame.setResizable(false);
	frame.setName("Window");
	frame.setTitle("Window");
	frame.setSize(Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);

    }
    

    /**
     * @return the levelManager
     */
    public static LevelManager getLevelManager() {
	return levelManager;
    }
    
    /**
     * @return the menuManager
     */
    public static MenuManager getMenuManager() {
	return menuManager;
    }


    public static void addKeyListener(KeyListener keyListener) {
        mainApplet.addKeyListener(keyListener);
	frame.addKeyListener(keyListener);
    }

    public static void removeKeyListener(KeyListener keyListener) {
        mainApplet.removeKeyListener(keyListener);
	frame.removeKeyListener(keyListener);
    }
    
    public static void addMouseListener(MouseListener mouseListener) {
	mainApplet.addMouseListener(mouseListener);
    }

    public static void removeMouseListener(MouseListener mouseListener) {
	mainApplet.removeMouseListener(mouseListener);
    }

    /**
     * @return the container
     */
    public static GameContainer getContainer() {
	return container;
    }
    
    private static boolean containerChange;
    /**
     * @param aContainer the container to set
     */
    public static void setContainer(GameContainer aContainer) {
        if (aContainer == null)
            return;
        GameContainer oldContainer = container;
	
	container = aContainer;
        if (oldContainer != null) {
	    oldContainer.dispose();
	}
        containerChange = true;
    }

    /**
     * @param aLevelManager the levelManager to set
     */
    public static void setLevelManager(LevelManager aLevelManager) {
     levelManager = aLevelManager;
    }

    /**
     * @param aMenuManager the menuManager to set
     */
    public static void setMenuManager(MenuManager aMenuManager) {
        menuManager = aMenuManager;
    }
    private static BufferedImage lastImage;
    public static BufferedImage getLastImage() {
	return lastImage;
    }
    
    public static void setLastImage(BufferedImage image) {
	lastImage = image;
    }
}
