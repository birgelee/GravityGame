/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
import javathing.load.LevelLoader;
import javathing.menu.ButtonEvent;
import javathing.menu.MenuButton;
import javathing.menu.MenuManager;
import javathing.menu.RectangleMenuButton;
import javathing.render.Paintable;
import javathing.settings.Settings;
import javathing.statics.Statics;

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

	//levelManager.activateListeners();
	setContainer(new GameContainer() {
	    @Override
	    public void paint(Graphics g) {
		BufferedImage buf = new BufferedImage(Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT, BufferedImage.TYPE_INT_BGR);
		Graphics gr = buf.createGraphics();

		for (Paintable paintable : MainClass.getLevelManager().getPaintables()) {
		    paintable.paint(gr);
		}

		g.drawImage(buf, 0, 0, null); //To change body of generated methods, choose Tools | Templates.
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
	    public void init() {}
	    
	    @Override
	    public void dispose() {
		MainClass.getLevelManager().deactivateListeners();
	    }
	});
        MainClass.getLevelManager().activateListeners();
        //levelManager.activateListeners();
    }
     
     public static void initMainMenu() {
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
	MainClass.getMenuManager().activateListeners();
        
	
    }
}
