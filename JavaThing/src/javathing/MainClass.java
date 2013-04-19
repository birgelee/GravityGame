/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing;

import java.awt.Color;
import javathing.settings.Settings;
import javathing.level.LevelManager;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javathing.container.MenuContainer;
import javathing.menu.ButtonEvent;
import javathing.menu.MenuButton;
import javathing.menu.MenuManager;
import javathing.menu.RectangleMenuButton;
import javathing.sprite.Player;
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
    private static Screen screen;
    private static JFrame frame;
    private static MainApplet mainApplet;

    public static void main(String[] args) {
	/*init order:
	 * frame
	 * menu
	 * app
	 * event listeners
	 * start thread
	 * ...
	 * player
	 * level
	 * screen
	 * 
	 */
	frame = new JFrame();
	setSettings(frame);

	//initLevel();
	//screen = new Screen(levelManager.getStartingPosition().x - 20, levelManager.getStartingPosition().y - 20);
	initMenu();
	mainApplet = new MainApplet();
	menuManager.activateListeners();
	
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
		    mainApplet.repaint();
		    try {
			Thread.sleep(Settings.SLEEPTIME);
		    } catch (InterruptedException ex) {
			Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}

	    }
	});
	updateThread.start();
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
		Convenience.initLevel(0, "C:\\users\\henry\\desktop\\level.txt");
		setMenuManager(null);
	    }
	    
	}));
	setMenuManager(new MenuManager(buttons, Color.BLACK));
	//menuManager.addMouseListener(new MenuMouseListener());
	setContainer(new MenuContainer("Game:Menu:Main"));
        
	
    }
    
    private static Player player;

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

    /**
     * @return the screen
     */
    public static Screen getScreen() {
	return screen;
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
        containerChange = true;
	if (container != null) {
	    container.dispose();
	}
	container = aContainer;
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

    /**
     * @param aPlayer the player to set
     */
    public static void setPlayer(Player aPlayer) {
        player = aPlayer;
    }
}
