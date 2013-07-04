/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javathing.GameContainer;
import javathing.level.LevelManager;
import javathing.MainClass;
import static javathing.MainClass.setContainer;
import static javathing.MainClass.setMenuManager;
import javathing.Updateable;
import javathing.animation.SinAnimator;
import javathing.block.AirBlock;
import javathing.block.Block;
import javathing.block.DirtBlock;
import javathing.container.LevelContainer;
import javathing.container.MenuContainer;
import javathing.level.PauseButton;
import javathing.load.LevelLoader;
import javathing.menu.ButtonEvent;
import javathing.menu.MenuButton;
import javathing.menu.MenuManager;
import javathing.menu.RectangleMenuButton;
import javathing.render.Paintable;
import javathing.settings.Settings;
import javathing.statics.Statics;
import javax.imageio.ImageIO;

/**
 *
 * @author Henry
 */
public class Convenience {

    public static void addDirtBlock(int x, int y) {
	Block block = new DirtBlock(x, y);
	MainClass.getLevelManager().addBlock(block);
    }

    public static void addDirtBlock(LevelManager lm, int x, int y) {
	Block block = new DirtBlock(x, y);
	lm.addBlock(block);
    }

    public static void addAirBlock(int x, int y) {
	Block block = new AirBlock(x, y);
	MainClass.getLevelManager().addBlock(block);
    }

    public static void addAirBlock(LevelManager lm, int x, int y) {
	Block block = new AirBlock(x, y);
	lm.addBlock(block);
    }

    public static void initLevel(int levelNumber, String levelPath) {
	try {
	    LevelLoader loader = new LevelLoader(levelPath);
	    MainClass.setLevelManager(loader.getLevelManager());
	} catch (FileNotFoundException ex) {
	    ex.printStackTrace();
	}
	PauseButton pb = new PauseButton();
	MainClass.getLevelManager().getGUI().addComponenet(pb);
	//levelManager.activateListeners();
	setContainer(new LevelContainer(levelNumber));
	MainClass.getLevelManager().activateListeners();
	//levelManager.activateListeners();
    }

    public static void initMainMenu() {
	if (MainClass.getMenuManager() != null) {
	    MainClass.getMenuManager().deactivateListeners();
	}
	List<MenuButton> buttons = new ArrayList<MenuButton>();
	BufferedImage buttonBackground = null;
	try {
	    buttonBackground = ImageIO.read(
			Convenience.class.getClassLoader().getResourceAsStream(
			"javathing/resources/graphics/menu/menubutton.png"));
	} catch (IOException ex) {
	    Logger.getLogger(Convenience.class.getName()).log(Level.SEVERE, null, ex);
	}
	buttons.add(new RectangleMenuButton((Settings.SCREEN_WIDTH - 100) / 2, 200, 100, 50, buttonBackground, new SinAnimator(500, .5F, .8F), "Start Game", Color.white, new ButtonEvent() {
	    @Override
	    public void pressed() {
		Statics.levelVariables.setLevelNumber(1);
		Convenience.initLevel(1, "C:\\users\\henry\\desktop\\level\\level1.txt");
		setMenuManager(null);
	    }
	}));
	try {
	    setMenuManager(new MenuManager(buttons, ImageIO.read(
		    Convenience.class.getClassLoader().getResourceAsStream(
		    "javathing/resources/graphics/menu/menubackground.jpg"))));
	} catch (IOException ex) {
	    Logger.getLogger(Convenience.class.getName()).log(Level.SEVERE, null, ex);
	}
	//menuManager.addMouseListener(new MenuMouseListener());
	setContainer(new MenuContainer("Game:Menu:Main"));
	MainClass.getMenuManager().activateListeners();


    }
    public static void initPauseMenu() {
	if (MainClass.getLevelManager() != null) {
	    MainClass.getLevelManager().deactivateListeners();
	}
	List<MenuButton> buttons = new ArrayList<MenuButton>();
	BufferedImage buttonBackground = null;
	BufferedImage button2Background = null;
	try {
	    buttonBackground = ImageIO.read(
			Convenience.class.getClassLoader().getResourceAsStream(
			"javathing/resources/graphics/menu/pausemenubutton.png"));
	    button2Background = ImageIO.read(
			Convenience.class.getClassLoader().getResourceAsStream(
			"javathing/resources/graphics/menu/pausemenubutton.png"));
	} catch (IOException ex) {
	    Logger.getLogger(Convenience.class.getName()).log(Level.SEVERE, null, ex);
	}
	buttons.add(new RectangleMenuButton((Settings.SCREEN_WIDTH - 100) / 2, 200, 100, 50, buttonBackground, new SinAnimator(500), "Resume Game", Color.white, new ButtonEvent() {
	    @Override
	    public void pressed() {
		MainClass.getLevelManager().activateListeners();
		setContainer(new LevelContainer(Statics.levelVariables.getLevelNumber()));
		setMenuManager(null);
	    }
	}));
	buttons.add(new RectangleMenuButton((Settings.SCREEN_WIDTH - 100) / 2, 300, 100, 50, buttonBackground, new SinAnimator(500), "Main Menu", Color.white, new ButtonEvent() {
	    @Override
	    public void pressed() {
		Convenience.initMainMenu();
	    }
	}));
	 Graphics g = MainClass.getLastImage().createGraphics();
	 g.setColor(new Color(0, 0, 0, .6F));
	 g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT);
	    setMenuManager(new MenuManager(buttons, MainClass.getLastImage()));
	//menuManager.addMouseListener(new MenuMouseListener());
	setContainer(new MenuContainer("Game:Menu:Pause"));
	MainClass.getMenuManager().activateListeners();
    }
}
