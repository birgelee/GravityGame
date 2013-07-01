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
import javathing.block.AirBlock;
import javathing.block.Block;
import javathing.block.DirtBlock;
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
	final int finalLevelNumber = levelNumber;
	try {
	    LevelLoader loader = new LevelLoader(levelPath);
	    MainClass.setLevelManager(loader.getLevelManager());
	} catch (FileNotFoundException ex) {
	    ex.printStackTrace();
	}
	PauseButton pb = new PauseButton();
	MainClass.getLevelManager().getGUI().addComponenet(pb);
	//levelManager.activateListeners();
	setContainer(new GameContainer() {
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
		    for (Paintable paintable : paintables) {
			paintable.paint(levelgr);
		    }
		}
		levelgr.translate(-transformFactorX, -transformFactorY);
	        viewportgr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		viewportgr.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);   //code works but slows down game a lot
		//viewportgr.setPaint(new TexturePaint(level, new Rectangle2D.Float(0, 0, level.getWidth(), level.getHeight())));
		AffineTransform transform = new AffineTransform();
		transform.rotate(MainClass.getLevelManager().getScreen().getTilt(), Settings.SCREEN_WIDTH / 2, Settings.SCREEN_HTIGHT / 2);
		
		viewportgr.setTransform(transform);
		//viewportgr.translate((int) ((Math.sqrt(2) - 1) * Settings.SCREEN_WIDTH / 2), (int) ((Math.sqrt(2) - 1) * Settings.SCREEN_HTIGHT / 2));
		viewportgr.drawImage(level, -transformFactorX, -transformFactorY, null);
		//viewportgr.fillRect(0, 0, level.getWidth(), level.getHeight());
		viewportgr.setTransform(new AffineTransform());
		viewportgr.setPaint(null);
		viewportgr.drawImage(MainClass.getLevelManager().getGUI().getUIOverlay(), 0, 0, null);

		g.drawImage(viewport, 0, 0, null);
	    }

	    @Override
	    public void update() {
		for (Updateable updateable : MainClass.getLevelManager().getUpdateables()) {
		    updateable.update();
		}
	    }

	    @Override
	    public String getName() {
		return "Game:Level:" + finalLevelNumber;
	    }

	    @Override
	    public void init() {
	    }

	    @Override
	    public void dispose() {
		MainClass.getLevelManager().deactivateListeners();
	    }
	});
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
	buttons.add(new RectangleMenuButton((Settings.SCREEN_WIDTH - 100) / 2, 200, 100, 50, buttonBackground, .7F, "Start Game", Color.white, new ButtonEvent() {
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
}
