/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing;

import javathing.container.GameContainer;
import java.awt.Color;
import javathing.settings.Settings;
import javathing.level.LevelManager;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javathing.container.MenuContainer;
import javathing.load.MetadataLoader;
import javathing.menu.ButtonEvent;
import javathing.menu.MenuButton;
import javathing.menu.MenuManager;
import javathing.menu.RectangleMenuButton;
import javathing.statics.Statics;
import javathing.utils.Convenience;
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
	MetadataLoader mdl = new MetadataLoader("C:\\users\\henry\\desktop\\level\\levelmd.txt");
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
    
    public static void initMenu() {
	List<MenuButton> buttons = new ArrayList<MenuButton>();
	buttons.add(new RectangleMenuButton(20, 20, 100, 50, Color.red, "Start Game", new ButtonEvent() {

	    @Override
	    public void pressed() {
		Statics.levelVariables.setLevelNumber(1);
		Convenience.initLevel(1, "C:\\users\\henry\\desktop\\level\\level1.txt");
		setMenuManager(null);
	    }
	    
	}));
	setMenuManager(new MenuManager(buttons, Color.BLACK));
	//menuManager.addMouseListener(new MenuMouseListener());
	setContainer(new MenuContainer("Game:Menu:Main"));
        
	
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
