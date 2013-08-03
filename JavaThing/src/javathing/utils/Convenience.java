/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javathing.level.LevelManager;
import javathing.MainClass;
import static javathing.MainClass.setContainer;
import static javathing.MainClass.setMenuManager;
import javathing.animation.SinAnimator;
import javathing.animation.StaticAnimator;
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
	if (MainClass.getLevelManager() != null) {
	    MainClass.getLevelManager().deactivateListeners();
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
	buttons.add(new RectangleMenuButton((Settings.SCREEN_WIDTH - 100) / 2, 200, 100, 50, buttonBackground, new SinAnimator(400, .6F, .8F), "New Game", Color.white, new ButtonEvent() {
	    @Override
	    public void pressed() {
		Statics.levelVariables.setLevelNumber(1);
		Convenience.initLevel(1, "C:\\users\\henry\\desktop\\level\\level1.txt");
		setMenuManager(null);
	    }
	}));
	buttons.add(new RectangleMenuButton((Settings.SCREEN_WIDTH - 100) / 2, 300, 100, 50, buttonBackground, new SinAnimator(400, .6F, .8F), "Resume Game", Color.white, new ButtonEvent() {
	    @Override
	    public void pressed() {
		Convenience.initLevel(Statics.levelVariables.getLevelNumber(), "C:\\users\\henry\\desktop\\level\\level" + Statics.levelVariables.getLevelNumber() + ".txt");
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
	MainClass.getMenuManager().activateListeners(200);


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
	MainClass.getMenuManager().activateListeners(200);
    }
    
    public static void initDeathMenu() {
	if (MainClass.getLevelManager() != null) {
	    MainClass.getLevelManager().deactivateListeners();
	}
	BufferedImage buttonBackground = null;
	List<MenuButton> buttons = new ArrayList<MenuButton>();
	try {
	    buttonBackground = ImageIO.read(
			Convenience.class.getClassLoader().getResourceAsStream(
			"javathing/resources/graphics/menu/pausemenubutton.png"));
	} catch (IOException ex) {
	    Logger.getLogger(Convenience.class.getName()).log(Level.SEVERE, null, ex);
	}
	buttons.add(new RectangleMenuButton((Settings.SCREEN_WIDTH - 100) / 2, 100, 100, 50, new Color(0, 0, 0, 1F), "You did your best, but sometimes your best isn't quite good enough.", Color.white, new ButtonEvent() {
	    @Override
	    public void pressed() {
	    }
	}));
	buttons.add(new RectangleMenuButton((Settings.SCREEN_WIDTH - 100) / 2, 200, 100, 50, buttonBackground, new StaticAnimator(1), "Retry Level", Color.white, new ButtonEvent() {
	    @Override
	    public void pressed() {
                MainClass.getMenuManager().deactivateListeners();
		MainClass.getLevelManager().activateListeners();
		
		initLevel(Statics.levelVariables.getLevelNumber(), "C:\\users\\henry\\desktop\\level\\level" + Statics.levelVariables.getLevelNumber() + ".txt");
		setMenuManager(null);
	    }
	}));
	buttons.add(new RectangleMenuButton((Settings.SCREEN_WIDTH - 100) / 2, 300, 100, 50, buttonBackground, new SinAnimator(500), "Main Menu", Color.white, new ButtonEvent() {
	    @Override
	    public void pressed() {
		Convenience.initMainMenu();
	    }
	}));
	setMenuManager(new MenuManager(buttons, Color.BLACK));
	setContainer(new MenuContainer("Game:Menu:Death"));
	MainClass.getMenuManager().activateListeners(200);
    }
}
