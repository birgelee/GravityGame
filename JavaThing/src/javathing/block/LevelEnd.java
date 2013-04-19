/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.block;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javathing.MainClass;
import javathing.container.MenuContainer;
import javathing.menu.ButtonEvent;
import javathing.menu.MenuButton;
import javathing.menu.MenuManager;
import javathing.menu.RectangleMenuButton;
import javathing.render.PlatformerGraphicsUtil;
import javathing.settings.Settings;
import javathing.sprite.Player;
import javathing.sprite.Sprite;

/**
 *
 * @author lausd_user
 */
public class LevelEnd extends Block {

    
    public LevelEnd(int x, int y) {
        super(x, y);
    }
    
    @Override
    public boolean getPassable() {
        return true;
    }

    @Override
    public void paint(Graphics g) {
       PlatformerGraphicsUtil.translateGraphics(g);
       g.setColor(Color.white);
       g.fillRect(getX(), getY(), Settings.TileSize, Settings.TileSize);
       PlatformerGraphicsUtil.unTranslateGraphics(g);
    }

    @Override
    public void update() {
    }
    
    @Override
    public void whenInside(Sprite interactor) {
        List<MenuButton> buttons = new ArrayList<MenuButton>();
	buttons.add(new RectangleMenuButton(20, 20, 100, 50, Color.red, "Next Level", new ButtonEvent() {

	    @Override
	    public void pressed() {
		MainClass.initLevel();
                //MainClass.setPlayer(Player.initNewPlayer());
                
		MainClass.setMenuManager(null);
	    }
	    
	}));
        MainClass.setMenuManager(new MenuManager(buttons, Color.blue));
        MainClass.getMenuManager().activateListeners();
        MainClass.setContainer(new MenuContainer("Game:Menu:Transition"));
    }
    @Override
    public void onContact(Sprite interactor) {
    }
    
}
